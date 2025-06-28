package Character.KindsOfPlants;

import javafx.scene.image.Image;

import java.io.Serializable;

public abstract class Shroom extends Plant implements Serializable {
    Shroom(int cost, int hp, Image image, Image cardImage) {
        super(cost, hp, image, cardImage);
    }
}
