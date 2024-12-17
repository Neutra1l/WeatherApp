package _2khuat.weatherapp.Controller;

import _2khuat.weatherapp.Model.APIClient;
import _2khuat.weatherapp.Model.WeatherInfoParser;

public class WeatherAppController {
    private APIClient _apiClient;
    private WeatherInfoParser _parser;

    public WeatherAppController(){
        _apiClient = new APIClient();
        _parser = new WeatherInfoParser();
    }


}
