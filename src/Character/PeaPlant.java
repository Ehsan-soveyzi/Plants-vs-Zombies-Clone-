package Character;

import javafx.scene.image.Image;

public abstract class PeaPlant extends Plant {
    protected Bullet bullet;
    PeaPlant(int cost, int hp, double x, double y, int row, Image image) {
        super(cost, hp, x, y,row, image);
    }

    //every time this method called a bullet object will be created!
    // باید در بازی بررسی بشه که در سطر تا زمانی که زامبی هست صدا زده بشه
    abstract public void shoot();
}



