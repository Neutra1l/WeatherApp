/* (C) 2025 */
package _2khuat.weatherapp.Model;

import _2khuat.weatherapp.Model.Helper.HourlyTemperature;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor
@Getter
@Setter
public class BihourlyTemperatureData {
    HourlyTemperature hourly;

    public String[] getFormattedBihourlyTime(){
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

