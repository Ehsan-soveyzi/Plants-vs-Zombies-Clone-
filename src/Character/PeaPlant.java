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
class PeaShooter extends PeaPlant {

    private static final String peaShooterImageAddress = "/Character/peaShooter.png";

    public PeaShooter(int cost, int hp, double x, double y, int row) {
        super(cost, hp, x, y, row, new Image(peaShooterImageAddress));

    }

    @Override
    public void shoot() {
        Bullet normalBullet = new NormalBullet(getX(),getY(),getRow(), 1, 0.5);

    }

    @Override
    public void update(double deltaTime) {

    }


}
class Repeater extends PeaPlant {
    private static final String repeaterImageAddress = "/Character/repeater.png";
    public Repeater(int cost, int hp, double x, double y, int row) {
        super(cost, hp, x, y, row, new Image(repeaterImageAddress));

    }

    // we should check the same row for checking there is a zombie or not .
    @Override
    public void shoot() {
        Bullet firstBullet = new NormalBullet(getX(), getY(), getRow(), 1, 0.5);

        Bullet secondBullet = new NormalBullet(getX(), getY(), getRow(), 1, 0.5);
    }

    @Override
    public void update(double deltaTime) {

    }

}
class SnowPea extends PeaPlant {
    private static final String snowPeaImageAddress = "/Character/snowPea.png";
    public SnowPea(int cost, int hp, double x, double y, int row) {
        super(cost, hp, x, y, row, new Image(snowPeaImageAddress));

    }

    @Override
    public void shoot() {
        Bullet snowBullet = new SlowBullet(getX(), getY(),getRow(),1,0.5);

    }

//    @Override
//    public void update(double deltaTime) {
//
//    }


}
