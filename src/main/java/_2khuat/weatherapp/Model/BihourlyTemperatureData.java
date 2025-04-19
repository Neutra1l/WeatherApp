/* (C) 2025 */
package _2khuat.weatherapp.Model;

import _2khuat.weatherapp.Model.Helper.HourlyTemperature;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
/**
 * Container for temperature data, recorded every two hours on a given day.
 */
public class BihourlyTemperatureData {
    HourlyTemperature hourly;

    public String[] getFormattedBihourlyTime() {
        String[] bihourlyTime = hourly.getEveryOtherHour();
        String[] formattedTimeStamps = new String[bihourlyTime.length];
        int i = 0;
        for (String time : bihourlyTime) {
            LocalDateTime dateTime = LocalDateTime.parse(time);
            String hourlyTimeValue = dateTime.format(DateTimeFormatter.ofPattern("HH:mm"));
            formattedTimeStamps[i] = hourlyTimeValue;
            i++;
        }
        return formattedTimeStamps;
    }
}
