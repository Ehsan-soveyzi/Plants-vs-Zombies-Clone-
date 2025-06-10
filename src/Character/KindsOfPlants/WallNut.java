package Character.KindsOfPlants;

import javafx.scene.image.Image;

public class WallNut extends NutPlant {
    private static final String WallNutImageAddress = "/Images/resources/graphics/Plants/WallNut/WallNut/WallNut_0.png";

    public WallNut(int cost, int hp, int row) {
        super(cost, hp,row, new Image(WallNutImageAddress));
    }

    @Override
    public void updateImageSituation() {

    }
}