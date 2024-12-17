package _2khuat.weatherapp.Model;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class WeatherInfoParser {
    public JsonObject parseWeatherData(String jsonData) {
        JsonObject jsonObject = JsonParser.parseString(jsonData).getAsJsonObject();
        return jsonObject;
    }


}
