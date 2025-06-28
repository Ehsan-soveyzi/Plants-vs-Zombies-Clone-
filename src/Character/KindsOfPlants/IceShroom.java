package Character.KindsOfPlants;

import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.io.Serializable;

public class IceShroom extends BombPlant implements Serializable {
    private static final String iceShooterImageAddress = "/Images/resources/graphics/Plants/Peashooter/Peashooter.gif";
    private static final String iceShooterCardImageAddress = "/Images/resources/graphics/Cards/IceShroom.png";
    public static int cooldown = 7;
    public static boolean isReady = true;
    public static Timeline cooldownTimeline;

    public IceShroom(int cost, int hp, Image image, Image cardImage) {
        super(cost, hp, image, cardImage);
    }

    public IceShroom() {
        super(75,1000,new Image(iceShooterImageAddress),new Image(iceShooterCardImageAddress));
    }

    @Override
    public void burnZombies() {

    }

    @Override
    public void updateImageSituation(Pane pane) {

    }
}
