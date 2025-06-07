package Character;

public abstract class PeaPlant extends Plant {
    protected Bullet bullet;
    PeaPlant(int cost, int hp, double x, double y) {
        super(cost, hp, x, y);
    }

    //every time this method called a bullet object will be created!
    // باید در بازی بررسی بشه که در سطر تا زمانی که زامبی هست صدا زده بشه
    abstract public void shoot();
}
class PeaShooter extends PeaPlant {
    public PeaShooter(int cost, int hp, double x, double y) {
        super(cost, hp, x, y);

    }

    @Override
    public void shoot() {
        //
    }

    @Override
    public void update(double deltaTime) {

    }

    @Override
    public void setupImage() {

    }

}
class Repeater extends PeaPlant {
    public Repeater(int cost, int hp, double x, double y) {
        super(cost, hp, x, y);

    }

    @Override
    public void shoot() {
        //
    }

    @Override
    public void update(double deltaTime) {

    }

    @Override
    public void setupImage() {

    }

}
class SnowPea extends PeaPlant {
    public SnowPea(int cost, int hp, double x, double y) {
        super(cost, hp, x, y);

    }

    @Override
    public void shoot() {
        //
    }

    @Override
    public void update(double deltaTime) {

    }

    @Override
    public void setupImage() {

    }

}
