package org.example.aeroportfx.controller;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.io.IOException;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.aeroportfx.model.Flight;


import java.net.HttpURLConnection;

import java.net.URL;
import java.util.List;


public class TablePlaneController {
    @FXML
    private AnchorPane mainPane;
    @FXML
    private Label arrivalLabel;

    @FXML
    private VBox menuPane;

    @FXML
    private VBox companyInfo;

    @FXML
    private Button menuButton;
    @FXML
    private TableView<Flight> flightTable;
    @FXML
    private TableColumn<Flight, String> flightNumberColumn;
    @FXML
    private TableColumn<Flight, String> cityOfRegistrationColumn;
    @FXML
    private TableColumn<Flight, String> cityOfDestinationColumn;
    @FXML
    private TableColumn<Flight, String> statusColumn;
    @FXML
    private TableColumn<Flight, String> departureTimeColumn;
    @FXML
    private TableColumn<Flight, String> arrivalTimeColumn;

    @FXML
    private void initialize() {
        flightTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        flightNumberColumn.setCellValueFactory(new PropertyValueFactory<>("flightNumber"));
        cityOfRegistrationColumn.setCellValueFactory(new PropertyValueFactory<>("cityOfRegistration"));
        cityOfDestinationColumn.setCellValueFactory(new PropertyValueFactory<>("cityOfDestination"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        departureTimeColumn.setCellValueFactory(new PropertyValueFactory<>("departureTime"));
        arrivalTimeColumn.setCellValueFactory(new PropertyValueFactory<>("arrivalTime"));
        loadFlightData();
    }

    private void loadFlightData() {
        try {
            URL url = new URL("http://localhost:8080/api/flights");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");


            String basicAuth = getBasicAuthHeader("shakuro", "121212");
            conn.setRequestProperty("Authorization", basicAuth);

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            StringBuilder sb = new StringBuilder();
            String output;
            while ((output = br.readLine()) != null) {
                sb.append(output);
            }

            String response = sb.toString();
            conn.disconnect();

            ObservableList<Flight> flightData = parseFlightData(response);
            flightTable.setItems(flightData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getBasicAuthHeader(String username, String password) {
        String auth = username + ":" + password;
        byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes());
        return "Basic " + new String(encodedAuth);
    }

    private ObservableList<Flight> parseFlightData(String responseData) {
        ObjectMapper mapper = new ObjectMapper();
        JavaTimeModule module = new JavaTimeModule();
        LocalDateTimeDeserializer localDateTimeDeserializer = new LocalDateTimeDeserializer(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        module.addDeserializer(LocalDateTime.class, localDateTimeDeserializer);
        mapper.registerModule(module);

        List<Flight> flights;
        try {
            flights = mapper.readValue(responseData, new TypeReference<List<Flight>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
            flights = new ArrayList<>();
        }
        return FXCollections.observableArrayList(flights);
    }


    @FXML
    private void showArrivals() {
        System.out.println("Showing arrivals");
    }

    @FXML
    private void switchLanguage() {
        System.out.println("Switching language");
    }

    @FXML
    private void toggleMenu() {
        boolean isVisible = menuPane.isVisible();
        menuPane.setVisible(!isVisible);
        menuButton.setText(isVisible ? "Меню" : "Закрыть меню");
    }

    @FXML
    private void buyTicket(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/aeroportfx/Register.fxml"));
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/aeroportfx/Main.fxml"));
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


    @FXML
    private void showServices() {
        System.out.println("Showing services...");
    }

    @FXML
    private void toggleCompanyInfo() {
        boolean isVisible = companyInfo.isVisible();
        companyInfo.setVisible(!isVisible);
    }
}
