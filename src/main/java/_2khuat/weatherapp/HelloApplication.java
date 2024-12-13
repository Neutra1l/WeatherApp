package _2khuat.weatherapp;

import static javafx.application.Application.launch;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Map;

public class HelloApplication extends Application {
    private static final String apiUrl = "https://api.open-meteo.com/v1/forecast?";
    @Override
    public void start(Stage stage)  {
        Label weatherLabel = new Label("Loading weather data...");
        WeatherInfoParser infoParser = new WeatherInfoParser();
        new Thread(() -> {
            
            String weatherData = APIClient.getWeatherData(apiUrl);
            JsonObject dataAsJson = infoParser.parseWeatherData(weatherData);
            Map<String, JsonElement> dataAsMap = dataAsJson.asMap();
            javafx.application.Platform.runLater(() -> {
                weatherLabel.setText("Weather Data: " + dataAsJson.get("current"));
            });
        }).start();

        VBox root = new VBox(10, weatherLabel);
        Scene scene = new Scene(root, 300, 200);
        stage.setScene(scene);
        stage.setTitle("Weather App");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}