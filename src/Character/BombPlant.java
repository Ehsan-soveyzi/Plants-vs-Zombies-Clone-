package Character;

import javafx.scene.image.Image;

public abstract class BombPlant extends Plant {
    BombPlant(int cost, int hp, double x, double y, int row, Image image) {
        super(cost,hp, x, y, row, image);
    }
    public abstract void burnZombies();
}



