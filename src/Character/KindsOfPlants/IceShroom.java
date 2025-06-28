package Character.KindsOfPlants;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.Serializable;

public class IceShroom extends Shroom implements Serializable {
    public static final int cooldown = 7;
    public static boolean isReady = true;
    private static final String IceShroomAddress = "/new_resources/images/Plants/IceShroom/IceShroom.gif";
    private static final String IceCardImageView = "IceShroom";

    IceShroom() {
        super(75, 0, new Image(IceShroomAddress), new Image(IceCardImageView));
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
    public void freeze(){}
}

