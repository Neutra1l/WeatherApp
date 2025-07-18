/* (C) 2025 */
package _2khuat.weatherapp;

import java.io.IOException;

import _2khuat.weatherapp.Client.ConfigLoader;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main entry point of the application.
 */
public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        stage.setScene(new Scene(root, 1200, 700));
        stage.show();
    }

    public static void main(String[] args) {;
        launch();
    }
}
