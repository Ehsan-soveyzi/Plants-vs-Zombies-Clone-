package Character.KindsOfPlants;

import javafx.scene.image.Image;

public class TallNut extends NutPlant {
    private static final String TallNutImageAddress = "/Character/TallNut.png";
    TallNut(int cost, int hp, double x, double y, int row) {
        super(cost, hp, x, y, row, new Image(TallNutImageAddress));
    }

}