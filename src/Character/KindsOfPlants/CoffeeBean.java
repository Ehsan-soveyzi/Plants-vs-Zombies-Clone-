package Character.KindsOfPlants;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class CoffeeBean extends Plant{
    private static final String coffeeBeanImageAddress = "/Images/resources/graphics/Plants/CoffeeBean/CoffeeBean.gif";

    CoffeeBean() {
        super(75, 0, new Image(coffeeBeanImageAddress));
    }

    @Override
    public void updateImageSituation(Pane pane) {

    }
}
