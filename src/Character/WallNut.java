package Character;

import javafx.scene.image.Image;

public class WallNut extends NutPlant {
    private static final String WallNutImageAddress = "/Character/WallNut.png";

    WallNut(int cost, int hp, double x, double y, int row) {
        super(cost, hp, x, y, row, new Image(WallNutImageAddress));
    }

}