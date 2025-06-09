package Character.KindsOfPlants;

import javafx.scene.image.Image;

public  class CherryBomb extends BombPlant {
    private static final String cherryBombImageAddress = "/Character/CherryBomb.png";
    CherryBomb(int cost, int hp, double x, double y, int row, Image image) {
        super(cost, hp, x, y, row, image);

    }
    public void burnZombies(){

    }
}