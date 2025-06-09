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
class WallNut extends NutPlant {
    private static final String WallNutImageAddress = "/Character/WallNut.png";
    WallNut(int cost, int hp, double x, double y, int row) {
        super(cost, hp, x, y, row, new Image(WallNutImageAddress));
    }

}
class TallNut extends NutPlant {
    private static final String TallNutImageAddress = "/Character/TallNut.png";
    TallNut(int cost, int hp, double x, double y, int row) {
        super(cost, hp, x, y, row, new Image(TallNutImageAddress));
    }

}