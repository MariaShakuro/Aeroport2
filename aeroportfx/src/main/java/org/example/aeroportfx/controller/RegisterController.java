package org.example.aeroportfx.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.control.ToggleGroup;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import org.example.aeroportfx.model.Flight;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;


public class RegisterController implements Initializable {

    @FXML
    private TextField fromField;

    @FXML
    private TextField toField;

    @FXML
    private TextField departureDateField;

    @FXML
    private TextField returnDateField;

    @FXML
    private TextField passengerCountField;

    @FXML
    private Label adultCountLabel;

    @FXML
    private Label childCountLabel;

    @FXML
    private Label infantCountLabel;

    @FXML
    private Button searchButton;

    @FXML
    private RadioButton economyClassRadio;

    @FXML
    private RadioButton businessClassRadio;

    @FXML
    private RadioButton firstClassRadio;

    @FXML
    private VBox profileMenu;
    @FXML
    private VBox menuPane;

    @FXML
    private VBox companyInfo;

    @FXML
    private Button menuButton;

    private int adultCount = 0;
    private int childCount = 0;
    private int infantCount = 0;
    @FXML
    private DatePicker datePickerLeft;
    @FXML
    private DatePicker datePickerRight;

    private ToggleGroup toggleGroup;

    @FXML
    public void initialize() {
        toggleGroup = new ToggleGroup();
        economyClassRadio.setToggleGroup(toggleGroup);
        businessClassRadio.setToggleGroup(toggleGroup);
        firstClassRadio.setToggleGroup(toggleGroup);

    }


    @FXML
    private void searchButtonAction(ActionEvent event) throws IOException {
        String from = fromField.getText();
        String to = toField.getText();
        List<String> dates = new ArrayList<>();
        if (datePickerLeft.getValue() != null) {
            dates.add(datePickerLeft.getValue().toString());

        }
        if (datePickerRight.getValue() != null) {
            dates.add(datePickerRight.getValue().toString());
        }
        if ((from == null || from.isEmpty()) && (to == null || to.isEmpty()) && dates.isEmpty()) {
            navigateToNoResults(event);
            return;
        }
        if (from == null || from.isEmpty() || to == null || to.isEmpty() || dates.isEmpty()) {
            if (from == null || from.isEmpty()) {
                fromField.setPromptText("Незаполненное поле");
            }
            if (to == null || to.isEmpty()) {
                toField.setPromptText("Незаполненное поле");
            }
            if (dates.isEmpty()) {
                datePickerLeft.setPromptText("Незаполненное поле");
                datePickerRight.setPromptText("Незаполненное поле");
            }
            return;
        }
        List<Flight> flights = searchFlights(from, to, dates);
        if (flights.isEmpty()) {
            navigateToNoResults(event);
        } else {
            navigateToTickets(event, flights);
        }
    }

    private List<Flight> searchFlights(String from, String to, List<String> dates) throws IOException {
        URL url = new URL("http://localhost:8080/api/flights");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");
        String basicAuth = getBasicAuthHeader("shakuro", "121212");
        conn.setRequestProperty("Authorization", basicAuth);

        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
        }
        BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream()), StandardCharsets.UTF_8));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            response.append(line);
        }
        br.close();
        ObjectMapper mapper = new ObjectMapper();
        JavaTimeModule module = new JavaTimeModule();
        mapper.registerModule(module);
        List<Flight> flights = mapper.readValue(response.toString(), new TypeReference<List<Flight>>() {
        });

        return flights.stream()
                .filter(flight -> (from == null || flight.getCityOfRegistration().equalsIgnoreCase(from))
                        && (to == null || flight.getCityOfDestination().equalsIgnoreCase(to))
                        && dates.stream().anyMatch(date -> flight.getDepartureTime().toLocalDate().equals(LocalDate.parse(date)))
                        || (to != null && flight.getCityOfRegistration().equalsIgnoreCase(to)
                        && from != null && flight.getCityOfDestination().equalsIgnoreCase(from)
                        && dates.stream().anyMatch(date -> flight.getDepartureTime().toLocalDate().equals(LocalDate.parse(date)))))
                .collect(Collectors.toList());
    }


    private String getBasicAuthHeader(String username, String password) {
        String auth = username + ":" + password;
        byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes());
        return "Basic " + new String(encodedAuth);
    }

    private void navigateToNoResults(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/aeroportfx/Tickets.fxml"));
        Parent ticketsPage = loader.load();
        TicketsController controller = loader.getController();
        controller.showNoResults();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(ticketsPage);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }

    private void navigateToTickets(ActionEvent event, List<Flight> flights) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/aeroportfx/Tickets.fxml"));
        Parent ticketsPage = loader.load();
        TicketsController controller = loader.getController();
        controller.setFlights(flights);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(ticketsPage);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }

    @FXML
    public void toggleProfileMenu() {
        profileMenu.setVisible(!profileMenu.isVisible());
    }

    @FXML
    private void register(ActionEvent event) throws IOException {
        navigateToPage(event, "/org/example/aeroportfx/RegisterPage.fxml");
    }

    @FXML
    private void login(ActionEvent event) throws IOException {
        navigateToPage(event, "/org/example/aeroportfx/LoginPage.fxml");
    }


    @FXML
    private void buyTicket(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/aeroportfx/view/Register.fxml"));
            Parent registerPage = loader.load();
            Scene registerScene = new Scene(registerPage);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(registerScene);
            appStage.setFullScreen(true);
            appStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void goHome(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/aeroportfx/view/Main.fxml"));
            Parent homePage = loader.load();
            Scene homeScene = new Scene(homePage);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(homeScene);
            appStage.setFullScreen(true);
            appStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialization code if needed
    }

    private void navigateToPage(ActionEvent event, String fxmlFile) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource(fxmlFile)));
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }
}
