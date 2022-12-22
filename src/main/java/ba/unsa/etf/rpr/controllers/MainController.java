package ba.unsa.etf.rpr.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

public class MainController {

    public TextField fieldUsername;

    public void loginClick(ActionEvent actionEvent) {
        if(fieldUsername.getText().isEmpty()){
            fieldUsername.getStyleClass().add("wrongField");
            System.out.println("Empty username!");
            return;
        }
    }
}
