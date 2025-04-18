/* (C) 2025 */
package _2khuat.weatherapp.Client;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class OpenWeatherAPI extends AbstractAPIClient {
    private final String _urlOpenWeather = "https://api.openweathermap.org/data/2.5/weather?";
    private static OpenWeatherAPI openWeatherAPI;

    private OpenWeatherAPI() {}

    public static OpenWeatherAPI getInstance() {
        if (openWeatherAPI == null) {
            return new OpenWeatherAPI();
        }
        return openWeatherAPI;
    }

    public JsonObject getWeatherData(double[] coord) {
        String apiCall =
                _urlOpenWeather
                        + "lat="
                        + coord[0]
                        + "&"
                        + "lon="
                        + coord[1]
                        + "&appid="
                        + _apiKeyOpenWeather;
        JsonElement weatherData = makeApiCall(apiCall);
        return weatherData.getAsJsonObject();
    }
}
