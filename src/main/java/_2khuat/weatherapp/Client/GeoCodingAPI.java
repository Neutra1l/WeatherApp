/* (C) 2025 */
package _2khuat.weatherapp.Client;

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

    public String[] getStateAndCountry(String query) {
        String apiCall = _urlGeoCoding + "q=" + query + "&limit=1&appid=" + _apiKeyOpenWeather;
        JsonElement geoCodingResponse = makeApiCall(apiCall);
        JsonArray locationInfo = geoCodingResponse.getAsJsonArray();
        JsonElement element = locationInfo.get(0);
        JsonObject object = element.getAsJsonObject();
        String state = (object.get("state") != null) ? object.get("state").getAsString() : "";
        String country = object.get("country").getAsString();
        return new String[] {state, country};
    }
}
