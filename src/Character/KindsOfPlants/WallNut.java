package Character.KindsOfPlants;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class WallNut extends NutPlant {
    private static final String WallNutImageAddress = "/Images/resources/graphics/Plants/WallNut/WallNut/WallNut_";
//    private static final String WallNutImageAddress = "C:\\Users\\user\\Downloads\\wallNut.";

    public WallNut(int cost, int hp) {
        super(cost, hp, new Image(WallNutImageAddress + "0.png"));

    }

    @Override
    public void updateImageSituation(Pane pane) {
        playAnimation(15, WallNutImageAddress);
    }
}