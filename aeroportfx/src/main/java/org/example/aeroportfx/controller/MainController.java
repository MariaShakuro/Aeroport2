package org.example.aeroportfx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private Label arrivalLabel;


    @FXML
    private VBox menuPane;

    @FXML
    private VBox companyInfo;

    @FXML
    private Button menuButton;

    @FXML
    private MediaView mediaView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        String videoPath = getClass().getResource("/org/example/aeroportfx/29804-377520739.mp4").toExternalForm();
        Media media = new Media(videoPath);
        MediaPlayer mediaPlayer = new MediaPlayer(media);

        mediaPlayer.setVolume(0);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);

        mediaView.setMediaPlayer(mediaPlayer);
        mediaPlayer.play();
    }

    @FXML
    private void showArrivals(ActionEvent event) throws IOException {
        navigateToPage(event, "/org/example/aeroportfx/TablePlane.fxml");
    }


    @FXML
    private void toggleMenu() {
        boolean isVisible = menuPane.isVisible();
        menuPane.setVisible(!isVisible);
        menuButton.setText(isVisible ? "Меню" : "Закрыть меню");
    }

    @FXML
    private void buyTicket(ActionEvent event) throws IOException {
        navigateToPage(event, "/org/example/aeroportfx/RegisterPage.fxml");
    }

    @FXML
    private void goHome(ActionEvent event) {
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/org/example/aeroportfx/Main.fxml"));
            stage.setScene(new Scene(root));
            stage.setFullScreen(true);
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

    @FXML
    private void navigateToPage(ActionEvent event, String fxmlPath) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }
}
