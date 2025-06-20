package ApplyGraphics;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class GameMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public  void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        MainMenuController.stage = primaryStage;
        primaryStage.setScene(scene);
        primaryStage.setTitle("Map");
        primaryStage.setFullScreenExitHint("");
        primaryStage.setFullScreen(true);
        setFaceTransition(root);
        primaryStage.show();
    }
    public static void setFaceTransition(Parent root){
        //apply fadeTransition!
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.5), root);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();
    }
}
