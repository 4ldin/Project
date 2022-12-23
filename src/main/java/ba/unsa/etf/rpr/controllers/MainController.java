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
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class MainController {

    public TextField fieldUsername;
    public PasswordField fieldPassword;
    public Button loginButton;

    private void wrongUsername(){
        fieldUsername.getStyleClass().removeAll("correctField");
        fieldUsername.getStyleClass().add("wrongField");
    }
    private void correctUsername(){
        fieldUsername.getStyleClass().removeAll("wrongField");
        fieldUsername.getStyleClass().add("correctField");
    }

    private void wrongPassword(){
        fieldPassword.getStyleClass().removeAll("correctField");
        fieldPassword.getStyleClass().add("wrongField");
    }
    private void correctPassword(){
        fieldPassword.getStyleClass().removeAll("wrongField");
        fieldPassword.getStyleClass().add("correctField");
    }
    @FXML
    public void initialize(){
        fieldUsername.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (fieldUsername.getText().trim().isEmpty()) {
                    wrongUsername();
                }else{
                    correctUsername();
                }
            }
        }
        );
        fieldPassword.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (fieldPassword.getText().trim().isEmpty()) {
                    wrongPassword();
                } else {
                    correctPassword();
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
            Guests guest = guestdao.getByEmailPassword(fieldUsername.getText(), fieldPassword.getText());
            if(!Objects.equals(guest.geteMail(), fieldUsername.getText()) || !Objects.equals(guest.getPassword(), fieldPassword.getText())){
                System.out.println("Incorrect email or password. Please try again.");
                wrongPassword();
                wrongUsername();
            }else{
                correctPassword();
                correctUsername();
                System.out.println("Login successful!");
                Stage stage = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Guest.fxml"));
                Parent root = loader.load();

                GuestController guestScene = loader.getController();
                guestScene.displayName(fieldUsername.getText());

                stage.setTitle("Guest");
                stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
                stage.setResizable(false);
                stage.show();
                Stage primaryStage = (Stage) loginButton.getScene().getWindow();
                primaryStage.hide();
            }
        }
    }
}
