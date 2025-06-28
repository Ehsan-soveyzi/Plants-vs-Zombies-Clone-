package Character.KindsOfPlants;

import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.io.Serializable;

public class ScaredyShroom extends PeaPlant implements Serializable {

    public static final String cardViewImageAddress = "/Images/resources/graphics/Cards/ScaredyShroom.png";
    private static final String scaredyShroomImageAddress = "/Images/resources/graphics/Plants/Peashooter/Peashooter.gif";
    public static int cooldown = 7;
    public static boolean isReady = true;
    public static Timeline cooldownTimeline;

    public ScaredyShroom() {
        super(25, 5, new Image(scaredyShroomImageAddress), new Image(cardViewImageAddress));
    }

    @Override
    public void updateImageSituation(Pane pane) {

    }

    @Override
    public void shoot(Pane pane) {

    }

}
