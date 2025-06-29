package Character.KindsOfPlants;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class GraveBuster extends Plant {
    private static final String graveBusterImageAddress = "/new_resources/images/Plants/GraveBuster/GraveBuster.gif";
    private static final String graveBusterCardImageAddress = "/new_resources/images/Card/Plants/GraveBuster.png";
    public static int cooldown = 7;
    public static boolean isReady = true;
    public static Timeline cooldownTimeline;
    GraveBuster() {
        super(75, 5, new Image(graveBusterImageAddress), new Image(graveBusterCardImageAddress));
    }

    @Override
    public void updateImageSituation(Pane pane) {
        startCooldown();
    }
    public static void startCooldown() {
        isReady = false;

        cooldownTimeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            cooldown--;
            if(cooldown == 0) {
                cooldown = 7;
                isReady = true;
                cooldownTimeline.stop();
            }
        }));
        cooldownTimeline.setCycleCount(7);
        cooldownTimeline.play();
    }
}
