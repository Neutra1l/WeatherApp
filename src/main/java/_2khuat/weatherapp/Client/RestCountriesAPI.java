/* (C) 2025 */
package _2khuat.weatherapp.Client;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class RestCountriesAPI extends AbstractAPIClient {
    private final String _urlRestCountries = "https://restcountries.com/v3.1/alpha/";
    private static RestCountriesAPI restCountriesAPI;

    private RestCountriesAPI() {}

    public static RestCountriesAPI getInstance() {
        if (restCountriesAPI == null) {
            restCountriesAPI = new RestCountriesAPI();
        }
        return restCountriesAPI;
    }

    /**
     * Gets the name of the country based on its ISO 3166-1 alpha-2 country code.
     *
     * @param countryCode the country code to be decoded
     * @return the country name
     */
    public String getCountryName(String countryCode) {
        JsonElement response = makeApiCall(_urlRestCountries + countryCode);
        JsonObject firstEntry = response.getAsJsonArray().get(0).getAsJsonObject();
        String name = firstEntry.get("name").getAsJsonObject().get("common").getAsString();
        return name;
    }
}
