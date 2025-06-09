package Character.KindsOfZombie;

import javafx.scene.image.Image;

public class IMPZombie extends Zombie {
    private final static String IMPZombieImageAddress = "/Character/IMPZombie.png";
    public IMPZombie(int hp, double speed, double eatingSpeed, int row, double x, double y) {
        super(hp, speed, eatingSpeed, row, x, y, new Image(IMPZombieImageAddress));
    }

    @Override
    public void playWalkingAnimation() {

    }

    @Override
    protected void playEatingAnimation() {

    }
}
