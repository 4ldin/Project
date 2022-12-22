package ba.unsa.etf.rpr.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class GuestController {

    public Button logoutButton;

    public void logoutClick(ActionEvent actionEvent) throws IOException {
        Stage primaryStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/main.fxml"));
        Parent root =  fxmlLoader.load();
        Scene scene = new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        primaryStage.setTitle("Hotel Reservation System");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
        Stage guestStage = (Stage) logoutButton.getScene().getWindow();
        guestStage.close();
        System.out.println("Returning to login screen.");
    }
}
