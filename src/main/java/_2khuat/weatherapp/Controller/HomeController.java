package _2khuat.weatherapp.Controller;

import _2khuat.weatherapp.Model.APIClient;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class HomeController {

    @FXML
    Button okButton;
    @FXML
    Button btnSettings;
    @FXML
    Button btnAbout;
    @FXML
    Button btnPackages;
    @FXML
    TextField searchField;
    @FXML
    SplitPane weatherInfoDisplayPane;
    @FXML
    TextField cityName;
    @FXML
    TextArea tempDisplay;


    APIClient apiClient = APIClient.getApiClient();

    public void handleClick(MouseEvent mouseEvent) {
        if(mouseEvent.getSource() == btnSettings){

        }
        else if(mouseEvent.getSource() == btnAbout){

        }
        else if(mouseEvent.getSource() == btnPackages){

        }
        else if(mouseEvent.getSource() == okButton){
            StringProperty query = searchField.textProperty();
            double[] coord = apiClient.getCoordinates(query.getValue());
            JsonObject weatherData = apiClient.getWeatherData(coord);
            cityName.setText(query.getValue());
            JsonObject mainInfo = (JsonObject)weatherData.get("main");
            double currentTemp = mainInfo.get("temp").getAsDouble();
            tempDisplay.setText(currentTemp + " oC");
            weatherInfoDisplayPane.setVisible(true);
        }

    }

    public void handleInputChange(InputMethodEvent inputMethodEvent) {
        StringProperty query = searchField.textProperty();
        double[] coord = apiClient.getCoordinates(query.toString());
        System.out.println(coord);
    }
}
