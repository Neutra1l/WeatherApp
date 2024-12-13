module _2khuat.weatherapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;

    opens _2khuat.weatherapp to javafx.fxml;
    exports _2khuat.weatherapp;
}