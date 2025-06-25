package Character.KindsOfPlants;

import javafx.scene.image.Image;

import java.io.Serializable;

public abstract class NutPlant extends Plant implements Serializable {
    // میتونیم با خود کلاس نوشت
    NutPlant(int cost, int hp, Image image,Image cardImage) {
        super(cost, hp, image,cardImage);
    }

    public abstract void changeImage();

}

