/* (C) 2025 */
package _2khuat.weatherapp.Client;

import _2khuat.weatherapp.Model.WeatherDataCurrent;
import com.google.gson.Gson;
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

    /**
     * Gets an instance of the WeatherDataCurrent model based on the query.
     *
     * @param lat the latitude of the query
     * @param lon the longitude of the query
     * @return a WeatherDataCurrent model of the query
     */
    public WeatherDataCurrent getWeatherDataCurrent(double lat, double lon) {
        String apiCall =
                _urlOpenWeather
                        + "lat="
                        + lat
                        + "&"
                        + "lon="
                        + lon
                        + "&appid="
                        + _apiKeyOpenWeather;
        JsonObject weatherDataCurrentJson = makeApiCall(apiCall).getAsJsonObject();
        String weatherDataCurrentString = weatherDataCurrentJson.toString();
        Gson gson = new Gson();
        WeatherDataCurrent weatherDataCurrent =
                gson.fromJson(weatherDataCurrentString, WeatherDataCurrent.class);
        return weatherDataCurrent;
    }
}
