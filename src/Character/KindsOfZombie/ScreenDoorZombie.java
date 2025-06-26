package Character.KindsOfZombie;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class ScreenDoorZombie extends Zombie {
    private final static String screenDoorImageAddress =  "/Images/resources/graphics/Zombies/BucketheadZombie/BucketheadZombie/BucketheadZombie_";

    public ScreenDoorZombie(int row) {
        super(10, 30, 500, row,  new Image(screenDoorImageAddress + "0.png"));
    }

    @Override
    public void playEatingAnimation() {
        super.playEatingAnimation(10,
                "/Images/resources/graphics/Zombies/BucketheadZombie/BucketheadZombieAttack/BucketheadZombieAttack_");
    }

    public void playWalkingAnimation(Pane pane){
        super.playWalkingAnimation(pane, 14, screenDoorImageAddress);
    }

}


