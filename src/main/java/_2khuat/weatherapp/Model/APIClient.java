package _2khuat.weatherapp.Model;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class APIClient {
    private String _urlOpenWeather = "https://api.openweathermap.org/data/2.5/weather?";
    private String _urlGeoCoding = "http://api.openweathermap.org/geo/1.0/direct?";
    private String _urlRestCountries = "https://restcountries.com/v3.1/alpha/";
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

    public String getCountryName(String countryCode){
        String apiCall = _urlRestCountries + countryCode;
        JsonElement restCountriesResponse = makeApiCall(apiCall);
        JsonArray countryInfo = restCountriesResponse.getAsJsonArray();
        JsonObject countryName = countryInfo.get(0).getAsJsonObject();
        return countryName.get("name").getAsJsonObject().get("common").getAsString();
    }

    public double[] getCoordinates(String query){
        String apiCall = _urlGeoCoding + "q=" + query + "&limit=1&appid=" + _apiKey;
        double[] coord = new double[2];
        JsonElement geoCodingResponse = makeApiCall(apiCall);
        JsonArray locationInfo = geoCodingResponse.getAsJsonArray();
        JsonElement element = locationInfo.get(0);
        JsonObject object = element.getAsJsonObject();
        coord[0] = object.get("lat").getAsDouble();
        coord[1] = object.get("lon").getAsDouble();
        return coord;
    }

    public String[] getStateAndCountry(String query){
        String apiCall = _urlGeoCoding + "q=" + query + "&limit=1&appid=" + _apiKey;
        JsonElement geoCodingResponse = makeApiCall(apiCall);
        JsonArray locationInfo = geoCodingResponse.getAsJsonArray();
        JsonElement element = locationInfo.get(0);
        JsonObject object = element.getAsJsonObject();
        String state = (object.get("state") != null) ? object.get("state").getAsString() : "";
        String country = object.get("country").getAsString();
        return new String[]{state, country};
    }


    public JsonObject getWeatherData(double[] coord) {
        String apiCall = _urlOpenWeather + "lat=" + coord[0] + "&" + "lon=" + coord[1] + "&appid=" + _apiKey;
        JsonElement weatherData = makeApiCall(apiCall);
        return weatherData.getAsJsonObject();
    }

    private JsonElement makeApiCall(String apiCall){
        JsonElement jsonElement = new JsonElement() {
            @Override
            public JsonElement deepCopy() {
                return null;
            }
        };
        try {
            URL url = new URL(apiCall);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            InputStream inputStream = conn.getInputStream();
            Reader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            jsonElement = JsonParser.parseReader(reader);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return jsonElement;
    }
}
