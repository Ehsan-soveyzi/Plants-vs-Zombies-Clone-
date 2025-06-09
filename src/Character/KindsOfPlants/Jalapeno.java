package Character.KindsOfPlants;

import javafx.scene.image.Image;

public class Jalapeno extends BombPlant {
    private static final String jalapenoImageAddress = "/Character/Jalapeno.png";
    Jalapeno(int cost, int hp, double x, double y, int row, Image image) {
        super(cost, hp, x, y, row, image);
    }

    @Override
    public void updateImageSituation() {

    }

    public void burnZombies(){

    }

}