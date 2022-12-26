package ba.unsa.etf.rpr.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class RegistrationController {
    public TextField firstName;
    public Label lastName;
    public TextField eMail;
    public TextField password;

    @FXML
    void initialize(){
        firstName.textProperty().addListener((observableValue, oldVal, newVal) -> {});
        lastName.textProperty().addListener((observableValue, oldVal, newVal) -> {});
        eMail.textProperty().addListener((observableValue, oldVal, newVal) -> {});
        password.textProperty().addListener((observableValue, oldVal, newVal) -> {});
    }


}
