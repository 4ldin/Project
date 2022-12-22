package ba.unsa.etf.rpr.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

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

    public void loginClick(ActionEvent actionEvent) {
        if(fieldUsername.getText().isEmpty()){
            fieldUsername.getStyleClass().add("wrongField");
            System.out.println("Empty username!");
            return;
        }
    }
}
