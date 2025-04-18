package _2khuat.weatherapp.Controller;

import _2khuat.weatherapp.Model.APIClient;
import _2khuat.weatherapp.Model.BihourlyTemperatureData;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import javafx.application.Platform;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Controller class for home.fxml
 */
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
    @FXML
    LineChart hourlyTempChart;
    APIClient apiClient = APIClient.getApiClient();
    /**
     * Handles mouse click event
     * @param mouseEvent
     */
    public void handleClick(MouseEvent mouseEvent) {
        if(mouseEvent.getSource() == btnSettings){

        }
        else if(mouseEvent.getSource() == btnAbout){

        }
        else if(mouseEvent.getSource() == btnPackages){

        }
        else if(mouseEvent.getSource() == okButton){
            //Executes the API calls
            StringProperty query = searchField.textProperty();
            double[] coord = apiClient.getCoordinates(query.getValue());
            String[] stateAndCountry = apiClient.getStateAndCountry(query.getValue());
            String countryName = apiClient.getCountryName(stateAndCountry[1]);
            JsonObject weatherData = apiClient.getWeatherData(coord);
            JsonObject hourlyTempInfo = apiClient.getHourlyTemp(query.getValue());

            //Extract relevant information
            if(stateAndCountry[0].length() > 0) {
                cityName.setText(query.getValue() + ", " + stateAndCountry[0] + ", " + countryName);
            }
            else cityName.setText(query.getValue() + ", " + countryName);

            JsonObject mainInfo = (JsonObject)weatherData.get("main");
            int GMT = weatherData.get("timezone").getAsInt() / 3600;


            double tempValue = Math.round(mainInfo.get("temp").getAsDouble() - 273.15) * 10.0 / 10.0;
            int humidityValue = mainInfo.get("humidity").getAsInt();
            int pressureValue = mainInfo.get("pressure").getAsInt();
            int visibilityValue = weatherData.get("visibility").getAsInt();
            double feltTempValue = Math.round(mainInfo.get("feels_like").getAsDouble() - 273.15) * 10.0 / 10.0;
            double windSpeedValue = Math.round(weatherData.get("wind").getAsJsonObject().get("speed").getAsDouble()) * 10.0 / 10.0;
            double precipitationValue;
            if(weatherData.get("rain") != null){
                precipitationValue = Math.round(weatherData.get("rain").getAsJsonObject().get("1h").getAsDouble()) * 10.0 / 10.0;
            }
            else precipitationValue = 0;
            
            JsonObject hourAndCorrespondingTemp = hourlyTempInfo.get("hourly").getAsJsonObject();
            JsonArray timesOfDay = hourAndCorrespondingTemp.get("time").getAsJsonArray();
            JsonArray tempsOfDay = hourAndCorrespondingTemp.get("temperature_2m").getAsJsonArray();
            BihourlyTemperatureData bihourlyTemperatureData = new BihourlyTemperatureData(timesOfDay, tempsOfDay);
            String[] bihourlyTimesValue = bihourlyTemperatureData.getFormattedTimeStamps();
            double[] bihourlyTempsValue = bihourlyTemperatureData.getTemps();
            
            //Set information for display
            temp.setText(tempValue + " °C");
            humidity.setText("Humidity level: " + humidityValue + "%");
            pressure.setText("Pressure level: " + pressureValue + " hPa");
            visibility.setText("Visibility: " + visibilityValue + " m");
            feltTemp.setText("Feels like: " + feltTempValue + " °C");
            windSpeed.setText("Wind speed: " + windSpeedValue + " m/s");
            precipitation.setText("Precipitation: " + precipitationValue + " mm/h");
            if (GMT >= 0) {
                timezone.setText("GMT+" + GMT);
            } else {
                timezone.setText("GMT-" + GMT);
            }
            weatherInfoDisplayPane.setVisible(true);
            XYChart.Series<String, Number> XYData = new XYChart.Series<>();
            for(int i = 0; i < bihourlyTempsValue.length; i++){
                XYChart.Data<String, Number> dataPoint = new XYChart.Data<>(bihourlyTimesValue[i], bihourlyTempsValue[i]);
                XYData.getData().add(dataPoint);
            }
            hourlyTempChart.getData().clear();
            hourlyTempChart.getData().add(XYData);
        }
    }

    public void handleInputChange(InputMethodEvent inputMethodEvent) {
        StringProperty query = searchField.textProperty();
        double[] coord = apiClient.getCoordinates(query.toString());
        System.out.println(coord);
    }

    public void handleKeyPressed(KeyEvent e){
        searchField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                okButton.fire();
        }
        });
    }
}


