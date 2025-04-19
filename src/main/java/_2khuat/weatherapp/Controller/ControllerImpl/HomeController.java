/* (C) 2025 */
package _2khuat.weatherapp.Controller.ControllerImpl;

import _2khuat.weatherapp.Client.GeoCodingAPI;
import _2khuat.weatherapp.Client.OpenMeteoAPI;
import _2khuat.weatherapp.Client.OpenWeatherAPI;
import _2khuat.weatherapp.Client.RestCountriesAPI;
import _2khuat.weatherapp.Controller.IController;
import _2khuat.weatherapp.Model.BihourlyTemperatureData;
import _2khuat.weatherapp.Model.Helper.WeatherDescription;
import _2khuat.weatherapp.Model.Location;
import _2khuat.weatherapp.Model.WeatherDataCurrent;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Objects;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

/**
 * Controller class for home.fxml
 */
public class HomeController implements IController {

    @FXML Button okButton;
    @FXML Button btnSettings;
    @FXML Button btnAbout;
    @FXML Button btnPackages;
    @FXML TextField searchField;
    @FXML SplitPane weatherInfoDisplayPane;
    @FXML TextField cityName;
    @FXML TextField temp;
    @FXML TextField humidity;
    @FXML TextField pressure;
    @FXML TextField visibility;
    @FXML TextField feltTemp;
    @FXML TextField windSpeed;
    @FXML TextField localTime;
    @FXML TextField weatherDescription;
    @FXML LineChart hourlyTempChart;
    @FXML ImageView weatherIcon;

    GeoCodingAPI geoCoding = GeoCodingAPI.getInstance();
    OpenMeteoAPI openMeteo = OpenMeteoAPI.getInstance();
    OpenWeatherAPI openWeather = OpenWeatherAPI.getInstance();
    RestCountriesAPI restCountries = RestCountriesAPI.getInstance();

    /**
     * Handles mouse click event
     * @param mouseEvent
     */
    @Override
    public void handleClick(MouseEvent mouseEvent) {

        if (mouseEvent.getSource() == btnSettings) {

        } else if (mouseEvent.getSource() == btnAbout) {

        } else if (mouseEvent.getSource() == btnPackages) {

        } else if (mouseEvent.getSource() == okButton) {
            // makes the necessary API calls
            String query = searchField.textProperty().getValue();
            Location location = geoCoding.getLocation(query);
            WeatherDataCurrent weatherDataCurrent =
                    openWeather.getWeatherDataCurrent(location.getLat(), location.getLon());
            BihourlyTemperatureData bihourlyTemperatureData = openMeteo.getBihourlyTemp(query);
            String countryName = restCountries.getCountryName(location.getCountry());

            // prepares the necessary variables
            String formattedTimezone = weatherDataCurrent.getFormattedTimezone();
            LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of(formattedTimezone));
            int hourValue = localDateTime.getHour();
            int minuteValue = localDateTime.getMinute();
            int tempValue = (int) (weatherDataCurrent.getMain().getTemp() - 273.15);
            int humidityValue = weatherDataCurrent.getMain().getHumidity();
            int pressureValue = weatherDataCurrent.getMain().getPressure();
            int visibilityValue = weatherDataCurrent.getVisibility();
            int feltTempValue = (int) (weatherDataCurrent.getMain().getFeels_like() - 273.15);
            int windSpeedValue = (int) weatherDataCurrent.getWind().getSpeed();
            WeatherDescription[] weatherDescArray = weatherDataCurrent.getWeather();
            String weatherDesc = weatherDescArray[0].getDescription();
            double[] bihourlyTempsValue = bihourlyTemperatureData.getHourly().getEveryOtherTemp();
            String[] bihourlyTimesValue = bihourlyTemperatureData.getFormattedBihourlyTime();

            // Set information for display
            setTexts(
                    location,
                    countryName,
                    hourValue,
                    minuteValue,
                    tempValue,
                    humidityValue,
                    pressureValue,
                    visibilityValue,
                    feltTempValue,
                    windSpeedValue,
                    weatherDesc);
            plotOnLineChart(bihourlyTempsValue, bihourlyTimesValue);
            setWeatherIcon(weatherDescArray[0].getId());
            weatherInfoDisplayPane.setVisible(true);
        }
    }

    private void setWeatherIcon(int weatherId) {
        if (weatherId >= 200 && weatherId < 300) {
            weatherIcon.setImage(
                    new Image(
                            Objects.requireNonNull(
                                            getClass()
                                                    .getResource("WeatherImages/thunderstorm.png"))
                                    .toString()));
        } else if (weatherId >= 300 && weatherId < 400) {
            weatherIcon.setImage(
                    new Image(
                            Objects.requireNonNull(
                                            getClass().getResource("WeatherImages/drizzle.png"))
                                    .toString()));
        } else if (weatherId >= 500 && weatherId < 600) {
            weatherIcon.setImage(
                    new Image(
                            Objects.requireNonNull(
                                            getClass().getResource("WeatherImages/heavy-rain.png"))
                                    .toString()));
        } else if (weatherId >= 600 && weatherId < 700) {
            weatherIcon.setImage(
                    new Image(
                            Objects.requireNonNull(getClass().getResource("WeatherImages/snow.png"))
                                    .toString()));
        } else if (weatherId >= 700 && weatherId < 800) {
            weatherIcon.setImage(
                    new Image(
                            Objects.requireNonNull(getClass().getResource("WeatherImages/mist.png"))
                                    .toString()));
        } else if (weatherId == 800) {
            weatherIcon.setImage(
                    new Image(
                            Objects.requireNonNull(
                                            getClass().getResource("WeatherImages/clear_sky.png"))
                                    .toString()));
        } else if (weatherId > 800) {
            weatherIcon.setImage(
                    new Image(
                            Objects.requireNonNull(
                                            getClass().getResource("WeatherImages/cloudy.png"))
                                    .toString()));
        }
    }

    private void setLocalTimeAndUpdate(int hourValue, int minuteValue) {
        if (minuteValue >= 60) {
            minuteValue = 0;
            hourValue++;
        }
        if (hourValue >= 24) {
            hourValue = 0;
        }

        String hourString = (hourValue < 10) ? "0" + hourValue : "" + hourValue;
        String minuteString = (minuteValue < 10) ? "0" + minuteValue : "" + minuteValue;

        localTime.setText(hourString + ":" + minuteString);

        int finalHourValue = hourValue;
        int finalMinuteValue = minuteValue;
        Timeline timeline =
                new Timeline(
                        new KeyFrame(
                                Duration.seconds(60),
                                event ->
                                        setLocalTimeAndUpdate(
                                                finalHourValue, finalMinuteValue + 1)));
        timeline.setCycleCount(
                1); // No need to set INDEFINITE here because it will be recursively called anyway
        timeline.play();
    }

    private void setTexts(
            Location location,
            String countryName,
            int hourValue,
            int minuteValue,
            int tempValue,
            int humidityValue,
            int pressureValue,
            int visibilityValue,
            int feltTempValue,
            double windSpeedValue,
            String weatherDesc) {
        if (location.getState() != null) {
            cityName.setText(location.getName() + ", " + location.getState() + ", " + countryName);
        } else cityName.setText(location.getName() + ", " + countryName);
        setLocalTimeAndUpdate(hourValue, minuteValue);
        temp.setText(tempValue + " °C");
        humidity.setText("Humidity level: " + humidityValue + "%");
        pressure.setText("Pressure level: " + pressureValue + " hPa");
        visibility.setText("Visibility: " + visibilityValue + " m");
        feltTemp.setText("Feels like: " + feltTempValue + " °C");
        windSpeed.setText("Wind speed: " + windSpeedValue + " m/s");
        weatherDescription.setText(toTitle(weatherDesc));
    }

    private void plotOnLineChart(double[] bihourlyTempsValue, String[] bihourlyTimesValue) {
        XYChart.Series<String, Number> XYData = new XYChart.Series<>();
        for (int i = 0; i < bihourlyTempsValue.length; i++) {
            XYChart.Data<String, Number> dataPoint =
                    new XYChart.Data<>(bihourlyTimesValue[i], bihourlyTempsValue[i]);
            XYData.getData().add(dataPoint);
        }
        hourlyTempChart.getData().clear();
        hourlyTempChart.getData().add(XYData);
    }

    public void handleKeyPressed(KeyEvent e) {
        searchField.setOnKeyPressed(
                event -> {
                    if (event.getCode() == KeyCode.ENTER) {
                        okButton.fire();
                    }
                });
    }

    // Helper method to capitalize first letter of every word in a String.
    private String toTitle(String str) {
        String[] words = str.split(" ");
        StringBuilder capitalized = new StringBuilder();

        for (String word : words) {
            if (!word.isEmpty()) {
                capitalized
                        .append(Character.toUpperCase(word.charAt(0)))
                        .append(word.substring(1).toLowerCase())
                        .append(" ");
            }
        }
        return capitalized.toString().trim();
    }

    public void handleInputChange(InputMethodEvent inputMethodEvent) {}
}
