package Character.KindsOfPlants;

import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.io.Serializable;

public class PuffShroom extends PeaPlant implements Serializable {
    private static final String cardViewImageAddress = "/Images/resources/graphics/Cards/PuffShroom.png";
    private static final String puffShroomImageAddress = "/Images/resources/graphics/Plants/Peashooter/Peashooter.gif";
    public static boolean isReady = true;
    public static int cooldown = 5;
    public static Timeline cooldownTimeline;

    public PuffShroom() {
        super(0, 5, new Image(puffShroomImageAddress), new Image(cardViewImageAddress));
    }

    @Override
    public void shoot(Pane pane) {

    }

    @Override
    public void updateImageSituation(Pane pane) {

    }
}
