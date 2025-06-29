package _2khuat.weatherapp.Client;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {
    public static String getAPIOpenWeather() {
        Properties props = new Properties();
        try {
            FileInputStream fis = new FileInputStream(".properties");
            props.load(fis);
        }
        catch (IOException e){
            System.out.println("Error fetching relevant data.");
        }
        return props.getProperty("_apiKeyOpenWeather");
    }
}
