/* (C) 2025 */
package _2khuat.weatherapp.Model.Helper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Container for weather descriptions. To be used within WeatherDataCurrent container.
 */
@AllArgsConstructor
@Setter
@Getter
public class WeatherDescription {
    private int id;
    private String main;
    private String description;
    private String icon;
}
