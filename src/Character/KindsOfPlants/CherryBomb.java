package Character.KindsOfPlants;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public  class CherryBomb extends BombPlant {
    private static final String cherryBombImageAddress = "/Images/resources/graphics/Plants/CherryBomb/CherryBomb_";
    CherryBomb() {
        //dont have idea about the hp!
        super(150, 100000, new Image(cherryBombImageAddress + "0.png"));

    }

    @Override
    public void updateImageSituation(Pane pane) {
        playAnimation(6, cherryBombImageAddress);
    }

    public void burnZombies(){

    }
}