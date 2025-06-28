package Character.KindsOfPlants;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import Character.Bullet;
import Character.ShroomBullet;


import java.io.Serializable;

public class PuffShroom extends Shroom implements Serializable {
    public static final int cooldown = 7;
    public static boolean isReady = true;
    private static final String puffAddress = "/new_resources/images/Plants/PuffShroom/PuffShroom.gif";
    private static final String puffCardImageAddress = "/Images/resources/graphics/Cards/PuffShroom.png";
    PuffShroom() {
        super(0, 2, new Image(puffAddress), new Image(puffCardImageAddress));
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
    public void shoot(Pane pane) {
        if(!isDead()) {
            Bullet normalBullet = new ShroomBullet(getX() + 10, getY(), getRow());
            normalBullet.addToPane(pane);

        }
    }

}
