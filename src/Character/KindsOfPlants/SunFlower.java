package Character.KindsOfPlants;
import Character.Sun;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.Random;

public class SunFlower extends Plant{


    public static final int cooldown = 7;
    public static boolean isReady = true;
    private static final String sunImageAddress = "/Images/resources/graphics/Plants/SunFlower/SunFlower.gif";
    public static Timeline cooldownTimeline;
    // static because before making this field the super execute


    public SunFlower() {
        super(50, 5, new Image(sunImageAddress));
    }

    @Override
    public void updateImageSituation(Pane pane) {
        Random rand = new Random();
        timeline = new Timeline(new KeyFrame(Duration.seconds(10), event -> {
            if (!isDead()) {
                int difX = rand.nextInt(40) - 20;
                int difY = rand.nextInt(40) - 20;
                Sun sun = new Sun(super.getX() + difX, super.getY() + difY);
                pane.getChildren().add(sun.getImageView());
            }
            else {
                timeline.stop();
            }
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        startCooldown();
    }

    public static void startCooldown() {
        isReady = false;

        cooldownTimeline = new Timeline(new KeyFrame(Duration.seconds(cooldown),event -> {
            isReady = true;
        }));
        cooldownTimeline.setCycleCount(1);
        cooldownTimeline.play();
    }

}
