package Character.KindsOfPlants;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public  class CherryBomb extends BombPlant {
    private static final String cherryBombImageAddress = "/Character/CherryBomb.png";
    CherryBomb(int cost, int hp, Image image) {
        super(cost, hp, image);

    }

    @Override
    public void updateImageSituation(Pane pane) {

    }

    public void burnZombies(){

    }
}