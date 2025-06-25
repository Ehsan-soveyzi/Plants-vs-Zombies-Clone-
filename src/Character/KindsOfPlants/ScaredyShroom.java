package Character.KindsOfPlants;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class ScaredyShroom extends Shroom{
    public static final int cooldown = 7;
    public static boolean isReady = true;
    public boolean isClose = false;
    private static final String ScaredyAddress = "/new_resources/images/Plants/ScaredyShroom/ScaredyShroom.gif";

    ScaredyShroom() {
        super(25, 4, new Image(ScaredyAddress));
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
