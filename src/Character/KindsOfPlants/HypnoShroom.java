package Character.KindsOfPlants;

import Character.KindsOfZombie.Zombie;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class HypnoShroom extends Shroom{
    public static final int cooldown = 7;
    public static boolean isReady = true;
    private static final String HypnoShroomAddress = "/new_resources/images/Plants/HypnoShroom/HypnoShroom.gif";

    HypnoShroom() {
        super(75, 1, new Image(HypnoShroomAddress));
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
    public void hypnotizedZombie(Zombie zombie) {

    }
}
