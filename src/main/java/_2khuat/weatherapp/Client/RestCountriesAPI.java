package _2khuat.weatherapp.Client;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class RestCountriesAPI extends AbstractAPIClient {
    private final String _urlRestCountries = "https://restcountries.com/v3.1/alpha/";
    private static RestCountriesAPI restCountriesAPI;

    private RestCountriesAPI(){}

    public static RestCountriesAPI getInstance(){
        if (restCountriesAPI == null){
            restCountriesAPI = new RestCountriesAPI();
        }
        return restCountriesAPI;
    }

    public String getCountryName(String countryCode){
        String apiCall = _urlRestCountries + countryCode;
        JsonElement restCountriesResponse = makeApiCall(apiCall);
        JsonArray countryInfo = restCountriesResponse.getAsJsonArray();
        JsonObject countryName = countryInfo.get(0).getAsJsonObject();
        return countryName.get("name").getAsJsonObject().get("common").getAsString();
    }
}
