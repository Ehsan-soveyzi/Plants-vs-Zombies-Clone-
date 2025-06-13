package Character.KindsOfPlants;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class WallNut extends NutPlant {
    private static final String WallNutImageAddress = "/Character/WallNut.png";

    public WallNut(int cost, int hp) {
        super(50, 10, new Image(WallNutImageAddress));
    }

    @Override
    public void updateImageSituation(Pane pane) {

    }
}