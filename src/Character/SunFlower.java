package Character;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class SunFlower extends Plant{

    Timeline sunGenerationTime;

    SunFlower(int cost, int hp, double x, double y) {
        super(cost, hp, x, y);
        super.setX(x);
        super.setY(y);

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

}
