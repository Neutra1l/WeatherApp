package _2khuat.weatherapp.Model.Helper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class HourlyTemperature {
    private String[] time;
    private float[] temperature_2m;

    public String[] getEveryOtherHour(){
        String[] timeStamps = new String[12];
        for (int i = 0, j = 0; i < time.length; i += 2, j++) {
            timeStamps[j] = time[i];
        }
        return timeStamps;
    }

    public double[] getEveryOtherTemp(){
        double[] temps = new double[12];
        for (int i = 0, j = 0; i < time.length; i += 2, j++) {
            temps[j] = temperature_2m[i];
        }
        return temps;
    }

}
