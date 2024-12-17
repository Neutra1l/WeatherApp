package _2khuat.weatherapp;

import static javafx.application.Application.launch;

import _2khuat.weatherapp.GUI.WeatherAppGUI;
import _2khuat.weatherapp.Model.APIClient;
import com.google.gson.JsonObject;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.Set;

public class HelloApplication extends Application {
    private WeatherAppGUI _gui;

    @Override
    public void start(Stage stage)  {
        APIClient apiClient = new APIClient();
        String weatherData = apiClient.getWeatherData();
        JsonObject dataAsJson = apiClient.parseWeatherData(weatherData);
        for(var member : dataAsJson.keySet()){
            System.out.println(member + " = " + dataAsJson.get(member));
        }

    }
        /**
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
         **/
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
    }**/

    public static void main(String[] args) {
        launch();
    }
}