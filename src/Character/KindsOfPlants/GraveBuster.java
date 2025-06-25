package Character.KindsOfPlants;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class GraveBuster extends Plant {
    private static final String graveBusterImageAddress = "/Images/resources/graphics/Plants/GraveBuster/GraveBuster.gif";
    GraveBuster() {
        super(75, 5, new Image(graveBusterImageAddress));
    }

    @Override
    public void updateImageSituation(Pane pane) {

    }
}
