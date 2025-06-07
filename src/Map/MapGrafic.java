package Map;

import javafx.application.Application;
import javafx.stage.Stage;

public class MapGrafic extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Stage stage = new Stage();
        stage.setTitle("Map");
        stage.setFullScreen(true);
        stage.show();

    }
}
