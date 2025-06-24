package Character.KindsOfZombie;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.Objects;

public class ScreenDoorZombie extends Zombie {
    private final static String screenDoorImageAddress =  "/Images/resources/graphics/Zombies/BucketheadZombie/BucketheadZombie/BucketheadZombie_";
    static int count = 0;
    public ScreenDoorZombie() {}
    public ScreenDoorZombie(int row) {
        super(10, 30, 500, row,  new Image(screenDoorImageAddress + "0.png"));
        System.out.println("SCREEN " + (count++) + " " + row);
    }
    @Override
    public void playEatingAnimation() {
        super.playAnimation(10,
                "/Images/resources/graphics/Zombies/BucketheadZombie/BucketheadZombieAttack/BucketheadZombieAttack_");
    }
    public void playWalkingAnimation(Pane pane){
        super.playWalkingAnimation(pane, 14, screenDoorImageAddress);
    }
}


