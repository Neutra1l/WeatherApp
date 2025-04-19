/* (C) 2025 */
package _2khuat.weatherapp.Client;

import _2khuat.weatherapp.Model.Location;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class GeoCodingAPI extends AbstractAPIClient {
    private final String _urlGeoCoding = "https://api.openweathermap.org/geo/1.0/direct?";
    private static GeoCodingAPI geoCodingAPI;

    private GeoCodingAPI() {}

    public static GeoCodingAPI getInstance() {
        if (geoCodingAPI == null) {
            return new GeoCodingAPI();
        }
        return geoCodingAPI;
    }

    /**
     * Gets the coordinates of the query in the parameter.
     * @param query the name of the city the user inputs.
     * @return coordinates of the city
     */
    public double[] getCoordinates(String query) {
        String apiCall = _urlGeoCoding + "q=" + query + "&limit=1&appid=" + _apiKeyOpenWeather;
        double[] coord = new double[2];
        JsonElement geoCodingResponse = makeApiCall(apiCall);
        JsonArray locationInfo = geoCodingResponse.getAsJsonArray();
        JsonElement element = locationInfo.get(0);
        JsonObject object = element.getAsJsonObject();
        coord[0] = object.get("lat").getAsDouble();
        coord[1] = object.get("lon").getAsDouble();
        return coord;
    }

    /**
     * Gets an instance of the Location model based on the query.
     *
     * @param query the name of the city the user inputs
     * @return location model of the query
     */
    public Location getLocation(String query) {
        String apiCall = _urlGeoCoding + "q=" + query + "&limit=1&appid=" + _apiKeyOpenWeather;
        JsonElement geoCodingResponse = makeApiCall(apiCall);
        JsonObject responseJson = geoCodingResponse.getAsJsonArray().get(0).getAsJsonObject();
        String responseString = responseJson.toString();
        Gson gson = new Gson();
        Location location = gson.fromJson(responseString, Location.class);
        return location;
    }
}
