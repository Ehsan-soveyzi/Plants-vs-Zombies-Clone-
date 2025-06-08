package Character;

public abstract class PeaPlant extends Plant {
    protected Bullet bullet;
    PeaPlant(int cost, int hp, double x, double y, int row) {
        super(cost, hp, x, y,row);
    }

    //every time this method called a bullet object will be created!
    // باید در بازی بررسی بشه که در سطر تا زمانی که زامبی هست صدا زده بشه
    abstract public void shoot();
}
class PeaShooter extends PeaPlant {
    public PeaShooter(int cost, int hp, double x, double y, int row) {
        super(cost, hp, x, y, row);

    }

    @Override
    public void shoot() {
        Bullet normalBullet = new NormalBullet(getX(),getY(),getRow(), 1, 0.5);

    }

    @Override
    public void update(double deltaTime) {

    }

    @Override
    public void setupImage() {

    }

}
class Repeater extends PeaPlant {
    public Repeater(int cost, int hp, double x, double y, int row) {
        super(cost, hp, x, y, row);

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

    @Override
    public void setupImage() {

    }

}
class SnowPea extends PeaPlant {
    public SnowPea(int cost, int hp, double x, double y, int row) {
        super(cost, hp, x, y, row);

    }

    @Override
    public void shoot() {
        Bullet snowBullet = new SlowBullet(getX(), getY(),getRow(),1,0.5);

    }

//    @Override
//    public void update(double deltaTime) {
//
//    }

    @Override
    public void setupImage() {

    }

}
