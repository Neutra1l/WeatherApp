/* (C) 2025 */
package _2khuat.weatherapp.Client;

import _2khuat.weatherapp.Model.BihourlyTemperatureData;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class OpenMeteoAPI extends AbstractAPIClient {
    private final String _urlOpenMeteo = "https://api.open-meteo.com/v1/forecast?";
    private final GeoCodingAPI geoCodingAPI = GeoCodingAPI.getInstance();
    private static OpenMeteoAPI openMeteoAPI;

    private OpenMeteoAPI() {}

    public static OpenMeteoAPI getInstance() {
        if (openMeteoAPI == null) {
            return new OpenMeteoAPI();
        } else return openMeteoAPI;
    }

    /**
     * Gets an instance of BihourlyTemperatureData model based on the query.
     *
     * @param query the name of the city the user inputs
     * @return BihourlyTemperatureData model of the query
     */
    public BihourlyTemperatureData getBihourlyTemp(String query) {
        double[] coord = geoCodingAPI.getCoordinates(query);
        String apiCall =
                _urlOpenMeteo
                        + "latitude="
                        + coord[0]
                        + "&longitude="
                        + coord[1]
                        + "&hourly=temperature_2m&forecast_days=1";
        JsonObject responseJson = makeApiCall(apiCall).getAsJsonObject();
        String responseString = responseJson.toString();
        Gson gson = new Gson();
        BihourlyTemperatureData bihourlyTemperatureData =
                gson.fromJson(responseString, BihourlyTemperatureData.class);
        return bihourlyTemperatureData;
    }
}
