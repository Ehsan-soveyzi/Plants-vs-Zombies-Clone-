package Character.KindsOfZombie;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class IMPZombie extends Zombie {
    private final static String IMPZombieImageAddress = "/Character/IMPZombie.png";
    public IMPZombie(int row) {
        super(3, 2, 1, row,new Image(IMPZombieImageAddress));
    }

    @Override
    public void playWalkingAnimation(Pane pane) {

    }

    @Override
    public void playEatingAnimation() {

    }
}
