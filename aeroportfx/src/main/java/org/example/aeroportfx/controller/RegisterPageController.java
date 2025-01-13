package org.example.aeroportfx.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.fxml.FXMLLoader;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;


import com.fasterxml.jackson.databind.ObjectMapper;

import org.example.aeroportfx.model.RegisterDto;

public class RegisterPageController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField surnameField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label nameErrorLabel;

    @FXML
    private Label surnameErrorLabel;

    @FXML
    private Label emailErrorLabel;

    @FXML
    private Label passwordErrorLabel;

    @FXML
    public void initialize() {
        // Optionally add any initialization code here
    }

    @FXML
    private void navigateOkButton(ActionEvent event) throws IOException {
        boolean isAnyFieldEmpty = false;
        boolean areAllFieldsEmpty = true;

        nameErrorLabel.setText("");
        surnameErrorLabel.setText("");
        emailErrorLabel.setText("");
        passwordErrorLabel.setText("");

        if (nameField.getText().isEmpty()) {
            nameErrorLabel.setText("Незаполненное поле");
            isAnyFieldEmpty = true;
        } else {
            areAllFieldsEmpty = false;
        }
        if (surnameField.getText().isEmpty()) {
            surnameErrorLabel.setText("Незаполненное поле");
            isAnyFieldEmpty = true;
        } else {
            areAllFieldsEmpty = false;
        }
        if (emailField.getText().isEmpty()) {
            emailErrorLabel.setText("Незаполненное поле");
            isAnyFieldEmpty = true;
        } else {
            areAllFieldsEmpty = false;
        }
        if (passwordField.getText().isEmpty()) {
            passwordErrorLabel.setText("Незаполненное поле");
            isAnyFieldEmpty = true;
        } else {
            areAllFieldsEmpty = false;
        }

        if (isAnyFieldEmpty) {
            return;
        }

        if (areAllFieldsEmpty) {
            navigateToMain(event);
        } else {
            if (checkUserExists(emailField.getText())) {
                navigateToLoginPage(event);
            } else {
                handleRegister();
                navigateToRegister(event);
            }
        }
    }

    private boolean checkUserExists(String email) {
        try {
            URL url = new URL("http://localhost:8083/api/auth/check-user?email=" + email);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            int responseCode = conn.getResponseCode();
            return responseCode == HttpURLConnection.HTTP_OK;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @FXML
    private void handleRegister() {
        String name = nameField.getText();
        String surname = surnameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();

        RegisterDto registerDto = new RegisterDto(name, surname, email, password);
        sendRegisterRequest(registerDto);
        System.out.println("Registering user: " + name + " " + surname + " with email: " + email);
    }

    private void sendRegisterRequest(RegisterDto registerDto) {
        try {
            URL url = new URL("http://localhost:8083/api/auth/register");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);

            ObjectMapper mapper = new ObjectMapper();
            String jsonInputString = mapper.writeValueAsString(registerDto);

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_CREATED) {
                System.out.println("Registration successful!");
            } else {
                System.out.println("Failed to register. HTTP response code: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void navigateToRegister(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/aeroportfx/Register.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }

    @FXML
    private void navigateToMain(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/aeroportfx/Main.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }

    @FXML
    private void navigateToLoginPage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/aeroportfx/LoginPage.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }
}

