package Character.KindsOfPlants;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.io.Serializable;

public class HypnoShroom extends Shroom implements Serializable {
    HypnoShroom(int cost, int hp, Image image, Image cardImage) {
        super(cost, hp, image, cardImage);
    }

    @Override
    public void updateImageSituation(Pane pane) {

    }
}
