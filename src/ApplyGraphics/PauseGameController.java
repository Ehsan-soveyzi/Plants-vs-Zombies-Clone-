package ApplyGraphics;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class PauseGameController {
    Stage stage;
    @FXML
    private ImageView home;
    @FXML
    private ImageView refresh;
    @FXML
    private ImageView resume;

    @FXML
    private void initialize(){
        MainMenuController.animateImage(home);
        MainMenuController.animateImage(refresh);
        MainMenuController.animateImage(resume);
        home.setOnMouseClicked(event -> {
            homeButtonClicked();
        });
        refresh.setOnMouseClicked(event -> {
            refreshButtonClicked();
        });
        resume.setOnMouseClicked(event -> {
           resumeButtonClicked();
        });
    }

    public void homeButtonClicked(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            MainMenuController.stage.setScene(scene);
            MainMenuController.stage.setFullScreen(true);
            MainMenuController.stage.show();
            MapController.menuStage.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void refreshButtonClicked(){
        try{
            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("Map.fxml"));
            Parent root = fxmlloader.load();
            Scene scene = new Scene(root);
            MainMenuController.stage.setScene(scene);
            MainMenuController.stage.setFullScreen(true);
            MainMenuController.stage.show();
            MapController.menuStage.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void resumeButtonClicked(){
        MapController.menuStage.close();
    }
}
