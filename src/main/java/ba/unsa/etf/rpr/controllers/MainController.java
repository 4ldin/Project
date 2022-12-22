package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.dao.GuestDao;
import ba.unsa.etf.rpr.dao.GuestDaoSQLimpl;
import ba.unsa.etf.rpr.domain.Guests;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class MainController {

    public TextField fieldUsername;
    public PasswordField fieldPassword;

    @FXML
    public void initialize(){
        fieldUsername.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (fieldUsername.getText().trim().isEmpty()) {
                    fieldUsername.getStyleClass().removeAll("correctField");
                    fieldUsername.getStyleClass().add("wrongField");
                }else{
                    fieldUsername.getStyleClass().removeAll("wrongField");
                    fieldUsername.getStyleClass().add("correctField");
                }
            }
        }
        );
        fieldPassword.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (fieldPassword.getText().trim().isEmpty()) {
                    fieldPassword.getStyleClass().removeAll("correctField");
                    fieldPassword.getStyleClass().add("wrongField");
                } else {
                    fieldPassword.getStyleClass().removeAll("wrongField");
                    fieldPassword.getStyleClass().add("correctField");
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
        }else if(fieldPassword.getText().isEmpty()){
            fieldPassword.getStyleClass().add("wrongField");
            System.out.println("Empty password!");
            return;
        }else{
            GuestDao guestdao = new GuestDaoSQLimpl();
            Guests guest = guestdao.getByEmailPassword(fieldUsername.toString(), fieldPassword.toString());
            if(guest == null){

            }else{
                System.out.println("Login successful!");
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("/fxml/Guest.fxml"));
                stage.setTitle("Hotel Reservation System");
                stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
                stage.show();
            }
        }
    }
}
