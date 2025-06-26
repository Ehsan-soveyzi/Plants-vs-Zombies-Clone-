package Character.KindsOfZombie;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class IMPZombie extends Zombie {
    private final static String IMPZombieImageAddress = "/Images/resources/graphics/Zombies/Imp/ZombieWalk/";
    static int count = 0;
    public IMPZombie() {}
    public IMPZombie(int row) {
        super(3, 40, 500, row ,new Image(IMPZombieImageAddress + "0.png"));
        System.out.println("IMPZombie " + (count++) + " " + row);
    }



    @Override
    public void playEatingAnimation() {
        super.playAnimation(26,
                "/Images/resources/graphics/Zombies/Imp/ZombieAttack/");
    }
    public void playWalkingAnimation(Pane pane){
        super.playWalkingAnimation(pane, 32, IMPZombieImageAddress);
    }

}
