package ApplyGraphics;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;

public class GameMain extends Application {
    //all the scene must set to this stage!
    public static Stage mainStage;
    public static String runner = "GameMain";
    public static boolean winner = false;
    public static boolean loser = false;

    public static void main(String[] args) {
        System.out.println(runner);
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        mainStage = primaryStage;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        mainStage = primaryStage;
        primaryStage.setScene(scene);
        primaryStage.setTitle(runner);
        primaryStage.setFullScreenExitHint("");
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }
    //use this effect for set fade effect to transferring cross the stages
    public static void setFadeTransition(Parent root){
        //apply fadeTransition!
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.5), root);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();
    }
}
