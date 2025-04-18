package _2khuat.weatherapp.Client;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class OpenMeteoAPI extends AbstractAPIClient{
    private final String _urlOpenMeteo = "https://api.open-meteo.com/v1/forecast?";
    private final GeoCodingAPI geoCodingAPI = GeoCodingAPI.getInstance();
    private static OpenMeteoAPI openMeteoAPI;

    private OpenMeteoAPI(){}

    public static OpenMeteoAPI getInstance(){
        if(openMeteoAPI == null){
            return new OpenMeteoAPI();
        }
        else return openMeteoAPI;
    }

    public JsonObject getHourlyTemp(String query){
        double[] coord = geoCodingAPI.getCoordinates(query);
        String apiCall = _urlOpenMeteo + "latitude=" + coord[0] + "&longitude=" + coord[1] + "&hourly=temperature_2m&forecast_days=1";
        JsonElement openMeteoResponse = makeApiCall(apiCall);
        JsonObject hourlyTempInfo = openMeteoResponse.getAsJsonObject();
        return hourlyTempInfo;
    }
}
