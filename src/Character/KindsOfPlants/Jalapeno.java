package Character.KindsOfPlants;

import Character.KindsOfZombie.Zombie;
import Map.ZombieFactory;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import java.io.Serializable;
import java.util.ArrayList;

public class Jalapeno extends BombPlant implements Serializable {
    private static final String jalapenoCardImageAddress = "/Images/resources/graphics/Cards/Jalapeno.png";
    private static final String JalapenoImageAddress = "/Images/resources/graphics/Plants/Jalapeno/Jalapeno/Jalapeno.gif";
    private static final String BurnJalapenoImageAddress =  "/Images/resources/graphics/Plants/Jalapeno/JalapenoExplode/JalapenoAttack.gif";
    public static int cooldown = 10;
    public static boolean isReady = true;
    public static Timeline cooldownTimeline;

    public Jalapeno() {
        //dont have idea about the hp!
        super(125, 100000, new Image(JalapenoImageAddress),new Image(jalapenoCardImageAddress));
    }

    @Override
    public void burnZombies(){
        ArrayList<Zombie> removeZombies = new ArrayList<>();
        for(Zombie zombie : ZombieFactory.zombies){
            if(zombie.getRow() == getRow()){
                removeZombies.add(zombie);
            }
        }
        for(Zombie zombie : removeZombies)zombie.burn();
    }

    public static void startCooldown() {
        isReady = false;

        cooldownTimeline = new Timeline(new KeyFrame(Duration.seconds(1),event -> {
            cooldown--;
            if(cooldown == 0) {
                cooldown = 10;
                isReady = true;
                cooldownTimeline.stop();
            }
        }));
        cooldownTimeline.setCycleCount(10);
        cooldownTimeline.play();
    }

    @Override
    public void updateImageSituation(Pane pane) {
        burnAnimation(BurnJalapenoImageAddress);
        startCooldown();
    }
}