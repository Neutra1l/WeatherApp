module _2khuat.weatherapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires java.sql;
    requires static lombok;

    opens _2khuat.weatherapp.Controller.ControllerImpl to
            javafx.fxml;
    opens _2khuat.weatherapp.Model to
            javafx.fxml;
    opens _2khuat.weatherapp to
            javafx.fxml;

    exports _2khuat.weatherapp;

    opens _2khuat.weatherapp.Client to
            javafx.fxml;
}
