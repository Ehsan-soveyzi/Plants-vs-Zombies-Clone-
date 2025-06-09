package Character;

import javafx.scene.image.Image;

abstract class NutPlant extends Plant {
    // میتونیم با خود کلاس نوشت
    NutPlant(int cost, int hp, double x, double y, int row, Image image) {
        super(cost, hp, x, y,row, image);
    }
    void changeImage() {

    }

}

