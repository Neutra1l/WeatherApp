/* (C) 2025 */
package _2khuat.weatherapp.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Location {
    private String name;
    private String state;
    private String country;
    private double lat;
    private double lon;
}
