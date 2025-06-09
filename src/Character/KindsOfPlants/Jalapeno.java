package Character.KindsOfPlants;

import javafx.scene.image.Image;

public class Jalapeno extends BombPlant {
    private static final String jalapenoImageAddress = "/Character/Jalapeno.png";
    Jalapeno(int cost, int hp, int row, Image image) {
        super(cost, hp, row, image);
    }

    public void burnZombies(){

    }

    @Override
    public void updateImageSituation() {

    }
}