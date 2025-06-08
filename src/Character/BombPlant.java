package Character;

import javafx.scene.image.Image;

public abstract class BombPlant extends Plant {
    BombPlant(int cost, int hp, double x, double y, int row, Image image) {
        super(cost,hp, x, y, row, image);
    }
    public abstract void burnZombies();
}

abstract class CherryBomb extends BombPlant {
    private static final String cherryBombImageAddress = "/Character/CherryBomb.png";
    CherryBomb(int cost, int hp, double x, double y, int row, Image image) {
        super(cost, hp, x, y, row, image);

    }
    public void burnZombies(){

    }
}
class Jalapeno extends BombPlant {
    private static final String jalapenoImageAddress = "/Character/Jalapeno.png";
    Jalapeno(int cost, int hp, double x, double y, int row, Image image) {
        super(cost, hp, x, y, row, image);
    }

    public void burnZombies(){

    }

}
