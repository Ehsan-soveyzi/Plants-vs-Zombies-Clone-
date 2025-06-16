package Character.KindsOfPlants;

import javafx.scene.image.Image;

public abstract class NutPlant extends Plant {
    // میتونیم با خود کلاس نوشت
    NutPlant(int cost, int hp, Image image) {
        super(cost, hp, image);
    }

    public abstract void changeImage();

}

