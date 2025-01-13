package org.example.aeroportfx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class SuccessBookingVBoxController {
    @FXML
    private VBox successBookingVBox;
    @FXML
    private TextField emailTextField;
    @FXML
    private RadioButton TXT;
    @FXML
    private RadioButton JSON;
    @FXML
    private RadioButton XML;
    @FXML
    private RadioButton YAML;

    @FXML
    private ToggleGroup formatToggleGroup;

    @FXML
    public void initialize() {
        formatToggleGroup = new ToggleGroup();
        TXT.setToggleGroup(formatToggleGroup);
        JSON.setToggleGroup(formatToggleGroup);
        XML.setToggleGroup(formatToggleGroup);
        YAML.setToggleGroup(formatToggleGroup);
    }

    @FXML
    private void sendEmail(ActionEvent event) {
        RadioButton selectedFormat = (RadioButton) formatToggleGroup.getSelectedToggle();
        if (selectedFormat != null) {
            String format = selectedFormat.getText();
            String email = emailTextField.getText();
            System.out.println("Формат билета: " + format);
            System.out.println("Адрес электронной почты: " + email);
            successBookingVBox.setVisible(false);
            switchToMainPage();
        } else System.out.println("Пожалуйста, выберите формат билета.");
    }

    private void switchToMainPage() {
        try {
            Stage stage = (Stage) successBookingVBox.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/org/example/aeroportfx/Main.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
