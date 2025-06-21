package Character.KindsOfPlants;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class PuffShroom extends Shroom{
    public static final int cooldown = 7;
    public static boolean isReady = true;
    private static final String puffAddress = "/new_resources/images/Plants/PuffShroom/PuffShroom.gif";
    PuffShroom() {
        super(0, 2, new Image(puffAddress));
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
}
