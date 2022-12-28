package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.dao.GuestDao;
import ba.unsa.etf.rpr.dao.GuestDaoSQLimpl;
import ba.unsa.etf.rpr.domain.Guests;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class RegistrationController {
    public TextField firstName;
    
    public TextField eMail;
    public TextField password;
    public TextField lastName;
    public Label passwordError;
    public Label eMailError;
    public Button returnButton;
    public Button createAccountBtn;
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
            if(eMail.getText().trim().isEmpty()){
                eMail.getStyleClass().removeAll("correctField");
                eMail.getStyleClass().add("wrongField");
            }else{
                eMail.getStyleClass().removeAll("wrongField");
                eMail.getStyleClass().add("correctField");
            }
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

    public void createAccountBtnClick(ActionEvent actionEvent) {
        if(firstName.getText().isEmpty()){
            firstName.getStyleClass().add("wrongField");
            System.out.println("Enter first name!");
            return;
        }else if(lastName.getText().isEmpty()){
           lastName.getStyleClass().add("wrongField");
            System.out.println("Enter last name!");
            return;
        }else if(eMail.getText().isEmpty()){
            eMail.getStyleClass().add("wrongField");
            System.out.println("Enter email!");
            return;
        }else if(password.getText().length() < 5){
            password.getStyleClass().add("wrongField");
            System.out.println("Password must contain at least 5 characters!");
        }else{
            Guests guest = new Guests();
            eMailError.setText("");
            try {
                GuestDao dao = new GuestDaoSQLimpl();
                listOfGuests = dao.getAll();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            for(Guests x : listOfGuests){
                if(x.geteMail().equals(eMail.getText())){
                    eMail.getStyleClass().removeAll("correctField");
                    eMail.getStyleClass().add("wrongField");
                    eMailError.setText("Email already in use!");
                    System.out.println("Email already in use. Please try again.");
                    return;
                }
            }
            System.out.println("Successful registration!");
        }
    }

    public void returnClick(ActionEvent actionEvent) throws IOException {
        Stage primaryStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/main.fxml"));
        Parent root =  fxmlLoader.load();
        Scene scene = new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        primaryStage.setTitle("Hotel Reservation System");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
        Stage guestStage = (Stage) returnButton.getScene().getWindow();
        guestStage.close();
        System.out.println("Returning to login screen.");
    }

}
