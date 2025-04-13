package _2khuat.weatherapp.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class HomeController {

    @FXML
    Button btnSettings;
    @FXML
    Button btnAbout;
    @FXML
    Button btnPackages;
    @FXML
    TextField searchField;

    public void handleClick(ActionEvent actionEvent) {
        if(actionEvent.getSource() == btnSettings){

        }
        else if(actionEvent.getSource() == btnAbout){

        }
        else if(actionEvent.getSource() == btnPackages){

        }
        else if(actionEvent.getSource() == searchField){
            String query = searchField.getText();

        }
    }
}
