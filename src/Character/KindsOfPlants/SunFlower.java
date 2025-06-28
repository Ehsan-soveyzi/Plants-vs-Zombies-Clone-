package Character.KindsOfPlants;

import Character.Sun;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import java.io.Serializable;
import java.util.Random;

public class SunFlower extends Plant implements Serializable {
    private static final String sunImageAddress = "/Images/resources/graphics/Plants/SunFlower/SunFlower.gif";
    private static final String sunFlowerCardImageAddress = "/Images/resources/graphics/Cards/SunFlower.png";
    public static final ImageView cardView = new ImageView(new Image(sunFlowerCardImageAddress));
    public static int cooldown = 5;
    public static boolean isReady = true;
    public static Timeline cooldownTimeline;

    public SunFlower() {
        super(50, 5, new Image(sunImageAddress),new Image(sunFlowerCardImageAddress));
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

        cooldownTimeline = new Timeline(new KeyFrame(Duration.seconds(1),event -> {
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

}
