package org.example.aeroportfx.controller;

import com.fasterxml.jackson.databind.JsonNode;
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
import org.example.aeroportfx.model.AuthResponseDto;
import org.example.aeroportfx.model.LoginDto;

public class LoginPageController {

    @FXML
    private TextField surnameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label loginErrorLabel;

    @FXML
    public void initialize() {
        // Optionally add any initialization code here
    }

    @FXML
    private void handleLogin(ActionEvent event) throws IOException {
        String username = surnameField.getText();
        String password = passwordField.getText();

        LoginDto loginDto = new LoginDto(username, password);
        boolean loginSuccessful = sendLoginRequest(loginDto);

        if (loginSuccessful) {
            navigateToRegister(event);
        } else {
            loginErrorLabel.setText("Неправильное имя или пароль");
            navigateToRegisterPage(event);
        }

        System.out.println("Logging in user with surname: " + username);
    }

    private boolean sendLoginRequest(LoginDto loginDto) {
        try {
            URL url = new URL("http://localhost:8083/api/auth/login");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);

            ObjectMapper mapper = new ObjectMapper();
            String jsonInputString = mapper.writeValueAsString(loginDto);

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("Вход выполнен успешно!");
                return true;
            } else {
                System.out.println("Вход не удался. HTTP код ответа: " + responseCode);
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
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
    private void navigateToRegisterPage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/aeroportfx/RegisterPage.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }
}
