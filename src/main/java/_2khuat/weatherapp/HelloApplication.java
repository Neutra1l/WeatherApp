package _2khuat.weatherapp;

import static javafx.application.Application.launch;

import _2khuat.weatherapp.GUI.WeatherAppGUI;
import _2khuat.weatherapp.Model.APIClient;
import _2khuat.weatherapp.Model.WeatherInfoParser;
import com.google.gson.JsonObject;
import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class HelloApplication extends Application {
    private String _apiUrl = "https://api.open-meteo.com/v1/forecast?latitude=53.5507&longitude=9.993&daily=sunrise,sunset";
    private WeatherAppGUI _gui;
    @Override
    public void start(Stage stage)  {
        _gui = new WeatherAppGUI(stage);
        WeatherInfoParser infoParser = new WeatherInfoParser();
        Label weatherLabel = new Label();
        new Thread(() -> {
            //apiUrl.concat("latitude=53.5507&longitude=9.993&hourly=temperature_2m,relative_humidity_2m");
            String weatherData = APIClient.getWeatherData(_apiUrl);
            JsonObject dataAsJson = infoParser.parseWeatherData(weatherData);
            List<String> keyList = new ArrayList<>(dataAsJson.keySet());
            for (String key : keyList) {
                dataAsJson.get(key);
            }
            javafx.application.Platform.runLater(() -> {
                for(String data : keyList) {
                    System.out.println(data + " = " + dataAsJson.get(data));
                    weatherLabel.setText("Weather Data: \n" + data + " = " + dataAsJson.get(data));
                }
            });
        }).start();
        _gui.show();
    }
        /**
        Label weatherLabel = new Label("Loading weather data...");
        WeatherInfoParser infoParser = new WeatherInfoParser();
        new Thread(() -> {
            //apiUrl.concat("latitude=53.5507&longitude=9.993&hourly=temperature_2m,relative_humidity_2m");
            String weatherData = APIClient.getWeatherData(apiUrl);
            JsonObject dataAsJson = infoParser.parseWeatherData(weatherData);
            List<String> keyList = new ArrayList<>(dataAsJson.keySet());
            for (String key : keyList) {
                dataAsJson.get(key);
            }
            javafx.application.Platform.runLater(() -> {
                for(String data : keyList) {
                    System.out.println(data + " = " + dataAsJson.get(data));
                    weatherLabel.setText("Weather Data: \n" + data + " = " + dataAsJson.get(data));
                }
            });
        }).start();

        VBox root = new VBox(10, weatherLabel);
        Scene scene = new Scene(root, 300, 200);
        stage.setScene(scene);
        stage.setTitle("Weather App");
        stage.show();
    }
         **/

    public static void main(String[] args) {
        launch();
    }
}