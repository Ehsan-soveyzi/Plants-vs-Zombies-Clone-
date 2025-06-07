package Character;

abstract class NutPlant extends Plant {
    // میتونیم با خود کلاس نوشت
    NutPlant(int cost, int hp, double x, double y) {
        super(cost, hp, x, y);
    }

}
class WallNut extends NutPlant {
    WallNut(double x, double y) {
        super(50, 10, x, y);
    }

    public void update(double deltaTime) {
        //no movement
    }

    public void setupImage() {
        //set the unique image here
    }
}