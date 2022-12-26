package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.dao.GuestDao;
import ba.unsa.etf.rpr.dao.GuestDaoSQLimpl;
import ba.unsa.etf.rpr.domain.Guests;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.List;

public class RegistrationController {
    public TextField firstName;
    
    public TextField eMail;
    public TextField password;
    public TextField lastName;
    public Label passwordError;
    public Label eMailError;
    private List<Guests> listOfGuests;

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
        password.textProperty().addListener((observableValue, oldVal, newVal) -> {
            if(newVal.trim().length() < 5){
                passwordError.setText("Password must contain at least 5 characters!");
                password.getStyleClass().removeAll("correctField");
                password.getStyleClass().add("wrongField");
            }else{
                password.getStyleClass().removeAll("wrongField");
                password.getStyleClass().add("correctField");
                passwordError.setText("");
            }
        });
    }


}
