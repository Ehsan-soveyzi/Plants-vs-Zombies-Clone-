package Character.KindsOfPlants;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class TallNut extends NutPlant {
    private static final String TallNutImageAddress = "/Character/TallNut.png";
    TallNut(int cost, int hp) {
        super(cost, hp, new Image(TallNutImageAddress));
    }

    @Override
    public void updateImageSituation(Pane pane) {

    }
}