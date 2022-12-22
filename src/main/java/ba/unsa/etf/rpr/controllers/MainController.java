package ba.unsa.etf.rpr.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class MainController {

    public TextField fieldUsername;

    @FXML
    public void initialize(){
        fieldUsername.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (fieldUsername.getText().trim().isEmpty()) {
                    fieldUsername.getStyleClass().removeAll("correctField");
                    fieldUsername.getStyleClass().add("wrongField");
                } else {
                    fieldUsername.getStyleClass().removeAll("wrongField");
                    fieldUsername.getStyleClass().add("correctField");
                }
            }
        }
        );
    }

    public void loginClick(ActionEvent actionEvent) throws IOException {
        if(fieldUsername.getText().isEmpty()){
            fieldUsername.getStyleClass().add("wrongField");
            System.out.println("Empty username!");
            return;
        }
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Guest.fxml"));
        stage.setTitle("Hotel Reservation System");
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.show();
    }
}
