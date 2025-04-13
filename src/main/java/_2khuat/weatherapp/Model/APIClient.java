package _2khuat.weatherapp.Model;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class APIClient {
    private String _urlOpenWeather = "https://api.openweathermap.org/data/2.5/weather?";
    private String _urlGeoCoding = "http://api.openweathermap.org/geo/1.0/direct?";
    private String weatherData;
    private final String _apiKey = "c7c6feca22d0f169d76feacae1e95ecd";

    public APIClient(){
        weatherData = getWeatherData(_urlOpenWeather);
    }

    public String getWeatherData(){
        return this.weatherData;
    }

    public String getCoordinates(String query){
        String apiCall = _urlGeoCoding + "q=" + query + "&limit=2&appid=" + _apiKey;
        StringBuilder result = new StringBuilder();
        try {
            URL url = new URL(apiCall);
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

    private String getWeatherData(String apiUrl) {
        StringBuilder result = new StringBuilder();
        try {
            URL url = new URL(apiUrl);
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
