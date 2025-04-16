package _2khuat.weatherapp.Controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;

public class ViewController {
    @FXML
    Button okButton;
    @FXML
    PasswordField longitudeUserInput;
    @FXML
    PasswordField latitudeUserInput;

    public void okButtonClickEvent(MouseEvent event){
        System.out.println("Button clicked");
    }

    public void handleClicks(ActionEvent actionEvent) {
    }
}
