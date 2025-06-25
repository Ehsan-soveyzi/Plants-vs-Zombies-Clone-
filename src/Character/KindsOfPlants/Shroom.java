package Character.KindsOfPlants;

import javafx.scene.image.Image;

public abstract class Shroom extends Plant {
    boolean isAwakeInDayMode = false;

    Shroom(int cost, int hp, Image image) {
        super(cost, hp, image);
    }
    public void setAwakeInDayMode() {
        isAwakeInDayMode = true;
    }
}
