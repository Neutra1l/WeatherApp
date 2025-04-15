package _2khuat.weatherapp.Model;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

public class APIClient {
    private String _urlOpenWeather = "https://api.openweathermap.org/data/2.5/weather?";
    private String _urlGeoCoding = "http://api.openweathermap.org/geo/1.0/direct?";
    private String weatherData;
    private final String _apiKey = "c7c6feca22d0f169d76feacae1e95ecd";
    private static APIClient singletonApiClient;

    private APIClient(){

    }

    public static APIClient getApiClient(){
        if (singletonApiClient == null){
            singletonApiClient = new APIClient();
        }
        return singletonApiClient;
    }

    public double[] getCoordinates(String query){
        String apiCall = _urlGeoCoding + "q=" + query + "&limit=1&appid=" + _apiKey;
        double[] coord = new double[2];
        try {
            URL url = new URL(apiCall);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            InputStream inputStream = conn.getInputStream();
            Reader reader = new InputStreamReader(inputStream);
            JsonElement jsonElement = JsonParser.parseReader(reader);
            JsonArray arr = jsonElement.getAsJsonArray();
            JsonElement element = arr.get(0);
            JsonObject object = element.getAsJsonObject();
            coord[0] = object.get("lat").getAsDouble();
            coord[1] = object.get("lon").getAsDouble();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return coord;
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
