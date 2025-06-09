package Character.KindsOfPlants;

import javafx.scene.image.Image;

public class WallNut extends NutPlant {
    private static final String WallNutImageAddress = "/Character/WallNut.png";

    WallNut(int cost, int hp, int row) {
        super(cost, hp,row, new Image(WallNutImageAddress));
    }

    @Override
    public void updateImageSituation() {

    }
}