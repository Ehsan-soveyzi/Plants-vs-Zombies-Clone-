package Character.KindsOfPlants;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.Serializable;

public class DoomShroom extends BombPlant implements Serializable {
    private static final String doomShroomImageAddress = "/Images/resources/graphics/Plants/doomShroom/BeginBoom.gif";
    private static final String doomShroomCardImageAddress = "/Images/resources/graphics/Cards/doomshroom.jpg";
    public static int cooldown = 7;
    public static boolean isReady = true;
    public static Timeline cooldownTimeline;

    public DoomShroom() {
        super(125, 1000, new Image(doomShroomImageAddress), new Image(doomShroomCardImageAddress));
    }

    @Override
    public void burnZombies() {
            
    }

    public static void startCooldown() {
        isReady = false;

        cooldownTimeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            cooldown--;
            if(cooldown == 0) {
                cooldown = 5;
                isReady = true;
                cooldownTimeline.stop();
            }
        }));
        cooldownTimeline.setCycleCount(5);
        cooldownTimeline.play();
    }

    @Override
    public void updateImageSituation(Pane pane) {
        startCooldown();
    }
}
