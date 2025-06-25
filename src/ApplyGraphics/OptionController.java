package ApplyGraphics;

import Save_Logic.SaveGame;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class OptionController {

    @FXML
    private ImageView BackButton;
    @FXML
    private ImageView loadButton;
    @FXML
    private Label infoLabel;

    public static int clicked = -1;

    @FXML
    public void initialize() {
        MainMenuController.animateImage(BackButton);
        MainMenuController.animateImage(loadButton);
        BackButton.setOnMouseClicked(event -> {
            backButton();
        });
        loadButton.setOnMouseClicked(event -> {
            if(SaveGame.load)loadButton();
            else infoLabel.setText("no saved game!");
        });
    }

    public void backButton(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            GameMain.setFadeTransition(root);
            GameMain.mainStage.setScene(scene);
            GameMain.mainStage.setFullScreen(true);
            GameMain.mainStage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void loadButton(){
        try{
            clicked = 1;
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Map.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            GameMain.mainStage.setScene(scene);
            GameMain.mainStage.setFullScreen(true);
            GameMain.mainStage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
