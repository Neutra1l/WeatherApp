/* (C) 2025 */
package _2khuat.weatherapp.Model;

import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
/**
 * Container for necessary parameters of locations.
 */
public class Location {
    private String name;
    private JsonObject local_names;
    private String state;
    private String country;
    private double lat;
    private double lon;
}
