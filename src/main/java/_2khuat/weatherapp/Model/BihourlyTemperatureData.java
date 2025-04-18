/* (C) 2025 */
package _2khuat.weatherapp.Model;

import com.google.gson.JsonArray;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BihourlyTemperatureData {

    private double[] temps;
    private String[] timeStamps;

    public BihourlyTemperatureData(JsonArray timesOfDay, JsonArray tempsOfDay) {
        temps = new double[12];
        timeStamps = new String[12];

        for (int i = 0, j = 0; i < tempsOfDay.size(); i += 2, j++) {
            temps[j] = tempsOfDay.get(i).getAsDouble();
            timeStamps[j] = timesOfDay.get(i).getAsString();
        }
    }

    public double[] getTemps() {
        return temps;
    }

    public String[] getTimeStamps() {
        return timeStamps;
    }

    public String[] getFormattedTimeStamps() {
        String[] formattedTimeStamps = new String[timeStamps.length];
        int i = 0;
        for (String time : timeStamps) {
            LocalDateTime dateTime = LocalDateTime.parse(time);
            String hourlyTimeValue = dateTime.format(DateTimeFormatter.ofPattern("HH:mm"));
            formattedTimeStamps[i] = hourlyTimeValue;
            i++;
        }
        return formattedTimeStamps;
    }
}
