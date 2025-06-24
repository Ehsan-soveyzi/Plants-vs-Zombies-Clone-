package Character.KindsOfZombie;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.Objects;

public class ConeHead extends Zombie {
    private final static String coneHeadImageAddress =
            "/Images/resources/graphics/Zombies/ConeheadZombie/ConeheadZombie/ConeheadZombie_";
    static int count = 0;
    public ConeHead() {}
    public ConeHead(int row) {
        super(7,30,500,row,new Image(coneHeadImageAddress + "0.png"));
        System.out.println("Conehead " + (count++) + " " + row);
    }


    public void  playEatingAnimation(){
        super.playAnimation(11,
                "/Images/resources/graphics/Zombies/ConeheadZombie/ConeheadZombieAttack/ConeheadZombieAttack_");
    }

    public void playWalkingAnimation(Pane pane){
        super.playWalkingAnimation(pane, 20, coneHeadImageAddress);
    }
}



