package _2khuat.weatherapp.GUI;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class WeatherAppGUI {
    private Button _weatherInfoDisplayButton;
    private Scene _weatherInfoDisplayScene;
    private StackPane _weatherInfoDisplayStackPane;
    private Stage _stage;
    private EventHandler<MouseEvent> mouseClickEventHandler;

    public WeatherAppGUI(Stage stage) {
        _weatherInfoDisplayButton = new Button("Display weather information");
        _weatherInfoDisplayButton.setOnMouseClicked(e -> System.out.println("Mouse clicked") );
        _weatherInfoDisplayStackPane = new StackPane();
        _weatherInfoDisplayStackPane.getChildren().add(_weatherInfoDisplayButton);
        _weatherInfoDisplayScene = new Scene(_weatherInfoDisplayStackPane, 300,300);
        this._stage = stage;
        _stage.setScene(_weatherInfoDisplayScene);
    }

    public void show(){
        _stage.show();
    }





}
