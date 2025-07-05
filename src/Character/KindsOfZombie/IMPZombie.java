package Character.KindsOfZombie;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class IMPZombie extends Zombie {
    private final static String IMPZombieImageAddress = "/Images/resources/graphics/Zombies/Imp/Zombiewalk/";

    public IMPZombie(int row) {
        super(3, -40, 500, row,new Image(IMPZombieImageAddress + "0.png"));
    }

    @Override
    public void playEatingAnimation() {
        super.playEatingAnimation(26,
                "/Images/resources/graphics/Zombies/Imp/ZombieAttack/");
    }
    public void playWalkingAnimation(Pane pane){super.playWalkingAnimation(pane, 32, IMPZombieImageAddress);}

}
