package _2khuat.weatherapp.Model;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Set;

public class APIClient {
    private String _apiUrl = "https://api.open-meteo.com/v1/forecast?";
    private String weatherData;
    private final String _coordinatesOfHamburg = "latitude=53.5507&longitude=9.993";

    public APIClient(){
        weatherData = getWeatherData(_apiUrl);
    }

    public String getWeatherData(){
        return this.weatherData;
    }

    private String getWeatherData(String apiUrl) {
        StringBuilder result = new StringBuilder();
        try {
            URL url = new URL(apiUrl + _coordinatesOfHamburg);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    public JsonObject parseWeatherData(String jsonData) {
        return JsonParser.parseString(jsonData).getAsJsonObject();
    }
}
