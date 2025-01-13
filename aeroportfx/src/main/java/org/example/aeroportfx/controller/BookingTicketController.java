package org.example.aeroportfx.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.aeroportfx.model.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import javafx.scene.control.Label;
import org.example.aeroportfx.model.Ticket;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class BookingTicketController implements Initializable {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        JavaTimeModule module = new JavaTimeModule();
        module.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DATE_TIME_FORMATTER));
        objectMapper.registerModule(module);
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @FXML
    private Label flightNumberLabel;
    @FXML
    private Label fromToLabel;
    @FXML
    private Label departureTimeLabel;
    @FXML
    private Label arrivalTimeLabel;
    @FXML
    private Label statusLabel;

    @FXML
    private Label cityOfRegistrationLabel;
    @FXML
    private Label cityOfDestinationLabel;

    @FXML
    private Label baggageCodeLabel;
    @FXML
    private Label priceLabel;

    @FXML
    private Label seatLabel;
    @FXML
    private Label gateLabel;
    @FXML
    private Label terminalLabel;
    @FXML
    private Label bookingCodeLabel;

    private Flight selectedFlight;
    @FXML
    private TextField requiredField1;
    @FXML
    private TextField requiredField2;

    @FXML
    private Label errorLabel1;
    @FXML
    private Label errorLabel2;
    @FXML
    private Label errorLabel3;
    @FXML
    private Label errorLabel4;
    @FXML
    private Label errorLabel5;
    @FXML
    private Label errorLabel6;
    @FXML
    private VBox bookingDetailsVBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialization logic if needed
    }


    @FXML
    private void showSuccessBooking() {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/aeroportfx/SuccessBookingVBox.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, 600, 800);
            stage.setScene(scene);
            stage.setTitle("Booking Confirmation");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void closeBookingDetails(ActionEvent event) {
        bookingDetailsVBox.setVisible(false);
    }

    @FXML
    private void submit(ActionEvent event) throws IOException {
        boolean valid = true;

        if (requiredField1.getText().isEmpty()) {
            errorLabel1.setVisible(true);
            valid = false;
        } else {
            errorLabel1.setVisible(false);
        }

        if (requiredField2.getText().isEmpty()) {
            errorLabel2.setVisible(true);
            valid = false;
        } else {
            errorLabel2.setVisible(false);
        }

        if (seatLabel.getText().isEmpty()) {
            errorLabel3.setVisible(true);
            valid = false;
        } else {
            errorLabel3.setVisible(false);
        }
        if (gateLabel.getText().isEmpty()) {
            errorLabel4.setVisible(true);
            valid = false;
        } else {
            errorLabel4.setVisible(false);
        }
        if (terminalLabel.getText().isEmpty()) {
            errorLabel5.setVisible(true);
            valid = false;
        } else {
            errorLabel5.setVisible(false);
        }
        if (bookingCodeLabel.getText().isEmpty()) {
            errorLabel6.setVisible(true);
            valid = false;
        } else {
            errorLabel6.setVisible(false);
        }
        if (valid) {
            postTicketData();
            showSuccessBooking();
        }
    }

    public void setFlight(Flight flight) {
        this.selectedFlight = flight;
        flightNumberLabel.setText("Номер рейса: " + flight.getFlightNumber());
        fromToLabel.setText("Откуда: " + flight.getCityOfRegistration() + " - Куда: " + flight.getCityOfDestination());
        departureTimeLabel.setText("Время вылета: " + flight.getDepartureTime().format(DATE_TIME_FORMATTER));
        arrivalTimeLabel.setText("Время прибытия: " + flight.getArrivalTime().format(DATE_TIME_FORMATTER));
        statusLabel.setText("Статус: " + flight.getStatus());
        cityOfRegistrationLabel.setText(flight.getCityOfRegistration());
        cityOfDestinationLabel.setText(flight.getCityOfDestination());

        baggageCodeLabel.setText("Код багажа: " + generateRandomBookingCode());
        priceLabel.setText("Цена: 150");


        seatLabel.setText("Место: " + generateRandomSeat());
        gateLabel.setText("Выход: " + generateRandomGate());
        terminalLabel.setText("Терминал: " + generateRandomInteger());


        bookingCodeLabel.setText("Код бронирования: " + generateRandomString(6));
    }

    private Long generateRandomBookingCode() {
        Random random = new Random();
        return random.nextLong(20) + 1;
    }

    private String generateRandomGate() {
        Random random = new Random();
        char letter = (char) ('A' + random.nextInt(5));
        int number = random.nextInt(30) + 1;
        return letter + String.valueOf(number);
    }

    private String generateRandomSeat() {
        Random random = new Random();
        int number = random.nextInt(30) + 1;
        char letter = (char) ('A' + random.nextInt(26));
        return number + String.valueOf(letter);
    }

    private int generateRandomInteger() {
        Random random = new Random();
        return random.nextInt(10) + 1;
    }

    @FXML
    private void confirmBooking(ActionEvent event) {
        bookingDetailsVBox.setVisible(true);
    }


    private String postTicket(Ticket ticket) throws IOException {
        URL url = new URL("http://localhost:8082/api/tickets/create");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);


        String jsonInputString = objectMapper.writeValueAsString(ticket);
        System.out.println("JSON Payload: " + jsonInputString);

        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        int responseCode = conn.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_UNAUTHORIZED) {
            throw new IOException("HTTP 401 Unauthorized: Неверный токен доступа");
        } else if (responseCode != HttpURLConnection.HTTP_OK && responseCode != HttpURLConnection.HTTP_CREATED) {
            throw new IOException("HTTP " + responseCode + " " + conn.getResponseMessage());
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }

    private void postTicketData() throws IOException {
        Ticket ticket = new Ticket();
        ticket.setTicketNumber(null);
        ticket.setCityOfRegistration(cityOfRegistrationLabel.getText());
        ticket.setCityOfDestination(cityOfDestinationLabel.getText());
        ticket.setCarrierFlight(flightNumberLabel.getText().replace("Номер рейса: ", ""));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        ticket.setDepartureTime(LocalDateTime.parse(departureTimeLabel.getText().replace("Время вылета: ", ""), formatter));
        ticket.setBoardingTime(LocalDateTime.parse(arrivalTimeLabel.getText().replace("Время прибытия: ", ""), formatter));

        ticket.setPassengerAge(Integer.parseInt(requiredField1.getText()));
        ticket.setClassOfSeat(requiredField2.getText().charAt(0));
        ticket.setBaggageIdNumber(Long.parseLong(baggageCodeLabel.getText().replace("Код багажа: ", "")));
        ticket.setTicketPrice(Double.parseDouble(priceLabel.getText().replace("Цена: ", "")));
        ticket.setSeat(seatLabel.getText().replace("Место: ", ""));
        ticket.setGate(gateLabel.getText().replace("Выход: ", ""));
        ticket.setTerminal(Integer.parseInt(terminalLabel.getText().replace("Терминал: ", "")));
        ticket.setBookingCode(bookingCodeLabel.getText().replace("Код бронирования: ", ""));
        ticket.setPassengerName("Misa");
        ticket.setPassengerSurname("Shaku");

        try {
            String response = postTicket(ticket);
            System.out.println(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder result = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            result.append(characters.charAt(random.nextInt(characters.length())));
        }
        return result.toString();
    }
}




