package Character.KindsOfPlants;
import Character.Sun;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class SunFlower extends Plant{

    Timeline sunGenerationTime;

    public static final int cooldown = 7;
    public static boolean isReady = true;
    private static final String sunImageAddress = "/Images/resources/graphics/Plants/SunFlower/SunFlower.gif";
    // static because before making this field the super execute


    public SunFlower() {
        super(50, 5, new Image(sunImageAddress));
    }

    @Override
    public void updateImageSituation(Pane pane) {
//        playAnimation(17, sunImageAddress);
        sunGenerationTime = new Timeline(new KeyFrame(Duration.seconds(5), event -> {
            if (!isDead()) {
                Sun sun = new Sun(super.getX(), super.getY());
                pane.getChildren().add(sun.getImageView());
            }
            else {
                sunGenerationTime.stop();
            }
        }));

        sunGenerationTime.setCycleCount(Timeline.INDEFINITE);
        sunGenerationTime.play();

        startCooldown();
    }

    public static void startCooldown() {
        isReady = false;

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(cooldown),event -> {
            isReady = true;
        }));
        timeline.setCycleCount(1);
        timeline.play();
    }

}
