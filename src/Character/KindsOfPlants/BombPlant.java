package Character.KindsOfPlants;

import javafx.scene.image.Image;

public abstract class BombPlant extends Plant {
    BombPlant(int cost, int hp, Image image) {
        super(cost,hp, image);
    }
    public abstract void burnZombies();
}



