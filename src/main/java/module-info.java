module _2khuat.weatherapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires java.sql;

    opens _2khuat.weatherapp.Controller to javafx.fxml;
    opens _2khuat.weatherapp.Model to javafx.fxml;

    exports _2khuat.weatherapp.Controller;
    exports _2khuat.weatherapp.Model;
    exports _2khuat.weatherapp;
    opens _2khuat.weatherapp to javafx.fxml;

}