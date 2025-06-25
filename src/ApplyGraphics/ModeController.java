package ApplyGraphics;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import java.io.IOException;
import java.io.Serializable;

//set serialize for using the enum
public class ModeController implements Serializable {

    //we need call this enum from this class to apply the changes between day and night!
    public enum Mode{
        DAY,NIGHT
    }

    private static Mode selectedMode;


    @FXML
    private ImageView dayMode;
    @FXML
    private ImageView nightMode;
    @FXML
    private ImageView BackButton;

    @FXML
    public void initialize() {
        MainMenuController.animateImage(dayMode);
        MainMenuController.animateImage(nightMode);
        MainMenuController.animateImage(BackButton);

        dayMode.setOnMouseClicked(event -> {
            modeSelected();
            selectedMode = Mode.DAY;
        });

        nightMode.setOnMouseClicked(event -> {
            modeSelected();
            selectedMode = Mode.NIGHT;
        });

        BackButton.setOnMouseClicked(event -> {
            backButton();
        });
    }

    public void modeSelected(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ChooseCard.fxml"));
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

    public static Mode getSelectedMode(){
        return selectedMode;
    }
    public static void setSelectedMode(Mode mode){
        selectedMode = mode;
    }
}