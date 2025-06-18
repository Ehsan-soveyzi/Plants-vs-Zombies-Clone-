package ApplyGraphics;

import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class MainMenuController {

    public static Stage stage;

    @FXML
    private ImageView playButton;
    @FXML
    private ImageView optionButton;
    @FXML
    private ImageView quitButton;

    @FXML
    public void initialize() {
        playButton.setOnMouseClicked(this::playButton);
        optionButton.setOnMouseClicked(this::optionButton);
        quitButton.setOnMouseClicked(this::ExitButton);
        animateImage(playButton);
        animateImage(optionButton);
        animateImage(quitButton);
    }

    public void playButton(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Mode.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setFullScreen(true);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void optionButton(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("OptionScene.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setFullScreen(true);
            stage.setFullScreenExitHint("");
            stage.show();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void ExitButton(MouseEvent event){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Log out");
        alert.setHeaderText("Loging Out!");
        alert.setContentText("Are you sure you want to logout?");

        alert.initOwner(stage);

        if(alert.showAndWait().get() == ButtonType.OK){
            System.exit(0);
        }
    }

    public static void animateImage(ImageView imageView){
        DropShadow dropShadow = new DropShadow(100, Color.web("#4B0000"));
        dropShadow.setSpread(0.3);
        ScaleTransition scaleIn = new ScaleTransition(Duration.millis(200), imageView);

        scaleIn.setToX(1.2);
        scaleIn.setToY(1.2);

        ScaleTransition scaleOut = new ScaleTransition(Duration.millis(200), imageView);

        scaleOut.setToX(1.0);
        scaleOut.setToY(1.0);

        imageView.setOnMouseEntered(event ->
        {
            scaleIn.playFromStart();
            imageView.setEffect(dropShadow);
        });
        imageView.setOnMouseExited(event -> {
            scaleOut.playFromStart();
            imageView.setEffect(null);
        });
    }
}
