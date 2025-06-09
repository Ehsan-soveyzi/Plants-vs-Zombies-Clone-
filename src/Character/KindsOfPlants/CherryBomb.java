package Character.KindsOfPlants;

import javafx.scene.image.Image;

public  class CherryBomb extends BombPlant {
    private static final String cherryBombImageAddress = "/Character/CherryBomb.png";
    CherryBomb(int cost, int hp, int row, Image image) {
        super(cost, hp, row, image);

    }

    @Override
    public void updateImageSituation() {

    }

    public void burnZombies(){

    }
}