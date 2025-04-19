/* (C) 2025 */
package _2khuat.weatherapp.Model.Helper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Container for predicted sunrise and sunset time. To be used within WeatherDataCurrent container.
 */
@Getter
@Setter
@AllArgsConstructor
public class Daylight {
    private long sunrise;
    private long sunset;
    private String country;
    private int id;
    private byte type;
}
