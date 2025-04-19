/* (C) 2025 */
package _2khuat.weatherapp.Model.Helper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Container for the most relevant weather parameters. To be used within WeatherDataCurrent container.
 */
@Getter
@Setter
@AllArgsConstructor
public class MainWeatherFeatures {
    private float temp;
    private float feels_like;
    private float temp_min;
    private float temp_max;
    private int pressure;
    private int humidity;
    private int sea_level;
    private int grnd_level;
}
