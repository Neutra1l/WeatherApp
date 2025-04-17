package _2khuat.weatherapp;

import static javafx.application.Application.launch;

import _2khuat.weatherapp.Model.APIClient;
import _2khuat.weatherapp.Model.City;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.Reader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLOutput;
import java.util.Set;

public class HelloApplication extends Application {

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    @Override
    public void start(Stage stage) throws IOException {
        String _urlGeoCoding = "http://api.openweathermap.org/geo/1.0/direct?";
        String query = "Hamburg";
        String _apiKey = "c7c6feca22d0f169d76feacae1e95ecd";
        String apiCall = _urlGeoCoding + "q=" + query + "&limit=2&appid=" + _apiKey;
        StringBuilder result = new StringBuilder();
        URL url = new URL(apiCall);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        InputStream inputStream = conn.getInputStream();
        Reader reader = new InputStreamReader(inputStream);
        JsonElement jsonElement = JsonParser.parseReader(reader);
        JsonArray arr = jsonElement.getAsJsonArray();
        for(JsonElement element : arr){
            JsonObject object = element.getAsJsonObject();
            System.out.println(object.get("name"));
        }
        System.out.println(Math.round(27.210505 * 10.0) / 10.0);

        Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        stage.setScene(new Scene(root, 1000,600));
        stage.show();


        /**
        _gui = new WeatherAppGUI(stage);
        _gui.show();
         **/

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