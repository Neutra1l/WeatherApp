module _2khuat.weatherapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;

    opens _2khuat.weatherapp.GUI to javafx.fxml;
    opens _2khuat.weatherapp.Controller to javafx.fxml;
    opens _2khuat.weatherapp.Model to javafx.fxml;

    exports _2khuat.weatherapp.Controller;
    exports _2khuat.weatherapp.GUI;
    exports _2khuat.weatherapp.Model;

}