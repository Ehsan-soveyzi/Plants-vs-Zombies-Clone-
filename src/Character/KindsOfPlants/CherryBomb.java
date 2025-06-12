package Character.KindsOfPlants;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public  class CherryBomb extends BombPlant {
    private static final String cherryBombImageAddress = "/Images/resources/graphics/Plants/CherryBomb/CherryBomb_";
    CherryBomb(int cost, int hp, int row) {
        super(cost, hp, row, new Image(cherryBombImageAddress + "0.png"));
        playAnimation(6, cherryBombImageAddress);

    }

    @Override
    public void updateImageSituation(Pane pane) {

    }

    public void burnZombies(){

    }
}