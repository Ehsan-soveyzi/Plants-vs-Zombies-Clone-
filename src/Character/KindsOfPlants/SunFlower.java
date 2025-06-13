package Character.KindsOfPlants;
import Character.Sun;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class SunFlower extends Plant{

    Timeline sunGenerationTime;

    private static final String sunImageAddress = "/Images/resources/graphics/Plants/SunFlower/SunFlower_";
    // static because before making this field the super execute


    public SunFlower(int cost, int hp) {
        super(cost, hp, new Image(sunImageAddress + "0.png"));




    }

    @Override
    public void updateImageSituation(Pane pane) {
        playAnimation(17, sunImageAddress);
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

    }
}
