package Character.KindsOfPlants;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class Plantern extends Plant {
    private static final String planternImageAddress = "/Images/resources/graphics/Plants/Plantern/Plantern.gif";
    Plantern() {
        super(25, 5, new Image(planternImageAddress));
    }

    @Override
    public void updateImageSituation(Pane pane) {

    }
}
