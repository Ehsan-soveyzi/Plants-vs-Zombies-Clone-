package Character.KindsOfPlants;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class Jalapeno extends BombPlant {
    private static final String jalapenoImageAddress = "/Character/Jalapeno.png";
    Jalapeno(int cost, int hp, Image image) {
        super(cost, hp, image);
    }

    public void burnZombies(){

    }

    @Override
    public void updateImageSituation(Pane pane) {

    }
}