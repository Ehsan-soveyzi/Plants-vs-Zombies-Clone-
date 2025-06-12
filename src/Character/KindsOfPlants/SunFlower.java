package Character.KindsOfPlants;
import Character.Sun;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.util.Objects;

public class SunFlower extends Plant{

    Timeline sunGenerationTime;

    private static final String sunImageAddress = "/Images/resources/graphics/Plants/SunFlower/SunFlower_";
    // or have a image field in parent class and
    // static because before making this field the super execute
    // میتونیم تعریف نکنیم صرفا این فیلد رو همون ادرس رو مستقیم بدیم

    public SunFlower(int cost, int hp, int row) {
        super(cost, hp, row, new Image(sunImageAddress + "0.png"));
        playAnimation(17, sunImageAddress);


//        sunGenerationTime = new Timeline(new KeyFrame(Duration.seconds(5), event -> {
//            if (!isDead()) {
//                Sun sun = new Sun(super.getX(), super.getY());
//            }
//            else {
//                sunGenerationTime.stop();
//            }
//        }));
//
//        sunGenerationTime.setCycleCount(Timeline.INDEFINITE);
//        sunGenerationTime.play();

    }

    @Override
    public void updateImageSituation(Pane pane) {

    }

}
