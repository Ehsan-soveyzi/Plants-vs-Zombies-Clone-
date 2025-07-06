package Character.KindsOfZombie;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.Serializable;
import java.util.Objects;

public class ConeHead extends Zombie implements Serializable {
    private final static String coneHeadImageAddress =
            "/Images/resources/graphics/Zombies/ConeheadZombie/ConeheadZombie/ConeheadZombie_";

    public ConeHead(int row) {
        super(7,-30,500,row,new Image(coneHeadImageAddress + "0.png"));
    }


    public void  playEatingAnimation(){
        super.playEatingAnimation(11,
                "/Images/resources/graphics/Zombies/ConeheadZombie/ConeheadZombieAttack/ConeheadZombieAttack_");
    }

    public void playWalkingAnimation(Pane pane){
        super.playWalkingAnimation(pane, 20, coneHeadImageAddress);
    }
}



