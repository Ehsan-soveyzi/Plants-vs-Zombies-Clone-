package Character.KindsOfZombie;

import javafx.scene.image.Image;

public class IMPZombie extends Zombie {
    private final static String IMPZombieImageAddress = "/Character/IMPZombie.png";
    public IMPZombie(int row) {
        super(3, 2, 1, row,new Image(IMPZombieImageAddress));
    }

    @Override
    public void playWalkingAnimation() {

    }

    @Override
    protected void playEatingAnimation() {

    }
}
