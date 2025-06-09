package Character.KindsOfPlants;
import Character.Sun;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class SunFlower extends Plant{

    Timeline sunGenerationTime;

    private static final String sunImageAddress = "/Character/sun.png";
    // or have a image field in parent class and
    // static because before making this field the super execute
    // میتونیم تعریف نکنیم صرفا این فیلد رو همون ادرس رو مستقیم بدیم

    SunFlower(int cost, int hp, double x, double y, int row) {
        super(cost, hp, x, y, row, new Image(sunImageAddress));

        sunGenerationTime = new Timeline(new KeyFrame(Duration.seconds(5), event -> {
            if (!isDead()) {
                Sun sun = new Sun(super.getX(), super.getY());
            }
            else {
                sunGenerationTime.stop();
            }
        }));

        sunGenerationTime.setCycleCount(Timeline.INDEFINITE);
        sunGenerationTime.play();

    }

    @Override
    public void updateImageSituation() {

    }
}
