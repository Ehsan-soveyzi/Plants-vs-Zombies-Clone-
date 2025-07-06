package Character.KindsOfZombie;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import java.io.Serializable;

public class Regular extends Zombie implements Serializable {
    public final static String regularImageAddress =
            "/Images/resources/graphics/Zombies/NormalZombie/Zombie/Zombie_";

    public Regular(int row) {
        super(5,-30,500,row,new Image(regularImageAddress + "0.png"));
    }

    public void  playEatingAnimation(){
        super.playEatingAnimation(21,
                "/Images/resources/graphics/Zombies/NormalZombie/ZombieAttack/ZombieAttack_");
    }

    public void playWalkingAnimation(Pane pane){super.playWalkingAnimation(pane, 21, regularImageAddress);}

}



