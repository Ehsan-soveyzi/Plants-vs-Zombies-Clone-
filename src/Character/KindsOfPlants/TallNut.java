package Character.KindsOfPlants;

import javafx.scene.image.Image;

public class TallNut extends NutPlant {
    private static final String TallNutImageAddress = "/Images/resources/graphics/Plants/WallNut/WallNut/WallNut_";
    TallNut(int cost, int hp, int row) {
        super(cost, hp, row, new Image(TallNutImageAddress));
    }

    @Override
    public void updateImageSituation() {

    }
}