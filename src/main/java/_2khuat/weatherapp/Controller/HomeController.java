package _2khuat.weatherapp.Controller;

import _2khuat.weatherapp.Model.APIClient;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;

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
    TextField temp;
    @FXML
    TextField timezone;
    @FXML
    TextField humidity;
    @FXML
    TextField pressure;
    @FXML
    TextField visibility;
    @FXML
    TextField feltTemp;
    @FXML
    TextField windSpeed;
    @FXML
    TextField precipitation;
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
            String[] stateAndCountry = apiClient.getStateAndCountry(query.getValue());
            String countryName = apiClient.getCountryName(stateAndCountry[1]);
            JsonObject weatherData = apiClient.getWeatherData(coord);
            if(stateAndCountry[0].length() > 0) {
                cityName.setText(query.getValue() + ", " + stateAndCountry[0] + ", " + countryName);
            }
            else cityName.setText(query.getValue() + ", " + countryName);

            JsonObject mainInfo = (JsonObject)weatherData.get("main");

            double tempValue = Math.round(mainInfo.get("temp").getAsDouble() - 273.15) * 10.0 / 10.0;
            int humidityValue = mainInfo.get("humidity").getAsInt();
            int pressureValue = mainInfo.get("pressure").getAsInt();
            int visibilityValue = weatherData.get("visibility").getAsInt();
            double feltTempValue = Math.round(mainInfo.get("feels_like").getAsDouble() - 273.15) * 10.0 / 10.0;
            double windSpeedValue = Math.round(weatherData.get("wind").getAsJsonObject().get("speed").getAsDouble()) * 10.0 / 10.0;
            double precipitationValue;
            if(weatherData.get("rai1n") != null){
                precipitationValue = Math.round(weatherData.get("rain").getAsJsonObject().get("1h").getAsDouble()) * 10.0 / 10.0;
            }
            else precipitationValue = 0;

            temp.setText("Current temperature: " + tempValue + " °C");
            humidity.setText("Humidity level: " + humidityValue + "%");
            pressure.setText("Pressure level: " + pressureValue + " hPa");
            visibility.setText("Visibility: " + visibilityValue + " m");
            feltTemp.setText("Feels like: " + feltTempValue + " °C");
            windSpeed.setText("Wind speed: " + windSpeedValue + " m/s");
            precipitation.setText("Precipitation: " + precipitationValue + " mm/h");

            weatherInfoDisplayPane.setVisible(true);

        }

    }

    public void handleInputChange(InputMethodEvent inputMethodEvent) {
        StringProperty query = searchField.textProperty();
        double[] coord = apiClient.getCoordinates(query.toString());
        System.out.println(coord);
    }
}
