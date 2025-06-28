package Character.KindsOfPlants;

import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.io.Serializable;

public class HypnoShroom extends Plant implements Serializable {
    private static final String hypnoShroomImageAddress = "/Images/resources/graphics/Plants/Peashooter/Peashooter.gif";
    private static final String hypnoShroomCardImageAddress = "/Images/resources/graphics/Cards/HypnoShroom.png";
    public static int cooldown = 7;
    public static boolean isReady = true;
    public static Timeline cooldownTimeline;

    HypnoShroom(int cost, int hp, Image image, Image cardImage) {
        super(cost, hp, image, cardImage);
    }

    @Override
    public void updateImageSituation(Pane pane) {

    }
}
