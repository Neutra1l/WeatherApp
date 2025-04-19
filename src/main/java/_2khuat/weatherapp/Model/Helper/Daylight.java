/* (C) 2025 */
package _2khuat.weatherapp.Model.Helper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

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
