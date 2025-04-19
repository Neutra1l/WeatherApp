/* (C) 2025 */
package _2khuat.weatherapp.Model.Helper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Container for wind parameters. To be used within WeatherDataCurrent container.
 */
@Getter
@Setter
@AllArgsConstructor
public class Wind {
    private float speed;
    private int deg;
    private float gust;
}
