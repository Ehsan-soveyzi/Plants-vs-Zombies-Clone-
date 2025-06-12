package Character.KindsOfPlants;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class WallNut extends NutPlant {
    private static final String WallNutImageAddress = "/Character/WallNut.png";

    WallNut(int cost, int hp) {
        super(cost, hp, new Image(WallNutImageAddress));
    }

    @Override
    public void updateImageSituation(Pane pane) {

    }
}