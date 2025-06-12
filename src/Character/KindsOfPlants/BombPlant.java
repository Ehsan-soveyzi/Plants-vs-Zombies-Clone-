package Character.KindsOfPlants;

import javafx.scene.image.Image;

public abstract class BombPlant extends Plant {
    BombPlant(int cost, int hp,int row, Image image) {
        super(cost,hp, row, image);
    }
    public abstract void burnZombies();
}



