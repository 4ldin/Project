package ba.unsa.etf.rpr.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class RegistrationController {
    public TextField firstName;
    
    public TextField eMail;
    public TextField password;
    public TextField lastName;

    @FXML
    void initialize(){
        firstName.textProperty().addListener((observableValue, oldVal, newVal) -> {
            if(firstName.getText().trim().isEmpty()){
                firstName.getStyleClass().removeAll("correctField");
                firstName.getStyleClass().add("wrongField");
            }else{
                firstName.getStyleClass().removeAll("wrongField");
                firstName.getStyleClass().add("correctField");
            }
        });
        lastName.textProperty().addListener((observableValue, oldVal, newVal) -> {
            if(lastName.getText().trim().isEmpty()){
                lastName.getStyleClass().removeAll("correctField");
                lastName.getStyleClass().add("wrongField");
            }else{
                lastName.getStyleClass().removeAll("wrongField");
                lastName.getStyleClass().add("correctField");
            }
        });
        eMail.textProperty().addListener((observableValue, oldVal, newVal) -> {

        });
        password.textProperty().addListener((observableValue, oldVal, newVal) -> {});
    }


}
