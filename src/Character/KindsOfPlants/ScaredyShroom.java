package Character.KindsOfPlants;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.Serializable;

public class ScaredyShroom extends Shroom implements Serializable {
    public static final int cooldown = 7;
    public static boolean isReady = true;
    public boolean isClose = false;
    private static final String ScaredyAddress = "/new_resources/images/Plants/ScaredyShroom/ScaredyShroom.gif";
    private static final String ScaredyCardImageAddress = "/Images/resources/graphics/Cards/ScaredyShroom.png";
    ScaredyShroom() {
        super(25, 4, new Image(ScaredyAddress), new Image(ScaredyCardImageAddress));
    }

    @Override
    public void updateImageSituation(Pane pane) {

    }
    public static void startCooldown() {
        isReady = false;

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(cooldown), event -> {
            isReady = true;
        }));
        timeline.setCycleCount(1);
        timeline.play();
    }
    public void shoot(){

    }
}
