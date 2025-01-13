package org.example.aeroportfx.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.aeroportfx.model.Flight;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class TicketsController implements Initializable {

    @FXML
    private ListView<VBox> ticketListView;

    private List<Flight> flights = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ticketListView.setItems(FXCollections.observableArrayList());
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
        updateTicketListView();
    }

    private void updateTicketListView() {
        ticketListView.getItems().clear();

        if (flights.isEmpty()) {
            showNoResults();
        } else {
            Map<LocalDate, List<Flight>> flightsByDate = flights.stream()
                    .collect(Collectors.groupingBy(flight -> flight.getDepartureTime().toLocalDate()));

            for (Map.Entry<LocalDate, List<Flight>> entry : flightsByDate.entrySet()) {
                ticketListView.getItems().add(createTicketItem(entry.getValue()));
            }
        }
    }

    @FXML
    private void navigateToBooking(ActionEvent event, Flight selectedFlight) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/aeroportfx/BookingTicket.fxml"));
        Parent root = loader.load();
        BookingTicketController bookingTicketController = loader.getController();
        bookingTicketController.setFlight(selectedFlight);
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }

    private VBox createTicketItem(List<Flight> flights) {
        VBox vbox = new VBox();
        vbox.setSpacing(10);
        vbox.setStyle("-fx-background-color: white; -fx-background-radius: 20; -fx-border-color: grey; -fx-border-radius: 20; -fx-border-width: 1;");
        vbox.setPrefHeight(150);
        vbox.setPrefWidth(700);

        for (Flight flight : flights) {
            HBox hbox = new HBox();
            hbox.setSpacing(10);

            VBox leftVBox = new VBox();
            leftVBox.setSpacing(10);
            leftVBox.setPrefWidth(150);

            Label priceLabel = new Label("Цена:150 ");
            leftVBox.getChildren().add(priceLabel);

            Button baggageButton = new Button("Багаж");
            baggageButton.setStyle("-fx-background-color: white; -fx-background-radius: 20; -fx-text-fill: black;");
            baggageButton.setOnAction(event -> {
                baggageButton.setStyle("-fx-background-color: grey; -fx-background-radius: 20; -fx-text-fill: white;");
                baggageButton.setDisable(true);
            });

            leftVBox.getChildren().add(baggageButton);

            VBox separator = new VBox();
            separator.setStyle("-fx-background-color: grey;");
            separator.setPrefWidth(5);

            VBox rightVBox = new VBox();
            rightVBox.setSpacing(5);

            Label flightNumberLabel = new Label("Номер рейса: " + flight.getFlightNumber());
            Label fromToLabel = new Label("Откуда: " + flight.getCityOfRegistration() + " - Куда: " + flight.getCityOfDestination());
            Label departureTimeLabel = new Label("Время вылета: " + flight.getDepartureTime().toString());
            Label arrivalTimeLabel = new Label("Время прибытия: " + flight.getArrivalTime().toString());
            Label statusLabel = new Label("Статус: " + flight.getStatus());

            rightVBox.getChildren().addAll(flightNumberLabel, fromToLabel, departureTimeLabel, arrivalTimeLabel, statusLabel);

            Button buyButton = new Button("Купить");
            buyButton.setStyle("-fx-background-color: orange; -fx-background-radius: 20; -fx-text-fill: white;");
            buyButton.setOnAction(event -> {
                try {
                    navigateToBooking(event, flight);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            HBox actionBox = new HBox();
            actionBox.setSpacing(10);
            actionBox.getChildren().addAll(buyButton);

            hbox.getChildren().addAll(leftVBox, separator, rightVBox, actionBox);
            vbox.getChildren().add(hbox);
        }

        return vbox;
    }

    public void showNoResults() {
        VBox vbox = new VBox();
        vbox.setSpacing(10);
        vbox.setStyle("-fx-background-color: lightgrey; -fx-background-radius: 20; -fx-border-color: grey; -fx-border-radius: 20; -fx-border-width: 1;");
        vbox.setPrefHeight(150);
        vbox.setPrefWidth(700);
        vbox.setAlignment(Pos.CENTER);

        Label noResultsLabel = new Label("Ничего не найдено");
        noResultsLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        vbox.getChildren().add(noResultsLabel);
        ticketListView.getItems().add(vbox);
    }
}



