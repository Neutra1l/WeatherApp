/* (C) 2025 */
package _2khuat.weatherapp.Model;

import _2khuat.weatherapp.Model.Helper.Cloudiness;
import _2khuat.weatherapp.Model.Helper.Daylight;
import _2khuat.weatherapp.Model.Helper.MainWeatherFeatures;
import _2khuat.weatherapp.Model.Helper.WeatherDescription;
import _2khuat.weatherapp.Model.Helper.Wind;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class WeatherDataCurrent {
    private WeatherDescription[] weather;
    private MainWeatherFeatures main;
    private int visibility;
    private Wind wind;
    private Cloudiness clouds;
    private Daylight sys;
    private int timezone;

    public String getFormattedTimezone(){
        int zone = timezone / 3600;
        if(zone >= 0){
            return "GMT+" + zone;
        }
        else return "GMT" + zone;
    }
}
