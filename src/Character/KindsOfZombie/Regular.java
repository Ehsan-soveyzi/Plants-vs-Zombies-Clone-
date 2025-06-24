package Character.KindsOfZombie;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.Objects;

public class Regular extends Zombie {
    public final static String regularImageAddress =
            "/Images/resources/graphics/Zombies/NormalZombie/Zombie/Zombie_";
    static int count = 0;
    // or have a image field in parent class and
    // static because before making this field the super execute
    // میتونیم تعریف نکنیم صرفا این فیلد رو همون ادرس رو مستقیم بدیم
    public Regular() {}
    public Regular(int row) {
        super(5,30,500,row,new Image(regularImageAddress + "0.png"));
        System.out.println("Regular " + (count++) + " " + row);
    }

    public void  playEatingAnimation(){
        super.playAnimation(21,
                "/Images/resources/graphics/Zombies/NormalZombie/ZombieAttack/ZombieAttack_");
    }
    public void playWalkingAnimation(Pane pane){
        super.playWalkingAnimation(pane, 21, regularImageAddress);
    }
}



