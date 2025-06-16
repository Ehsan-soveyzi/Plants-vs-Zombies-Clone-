package Character.KindsOfPlants;

import Character.KindsOfZombie.Zombie;
import Map.ZombieFactory;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.Objects;

public class Jalapeno extends BombPlant {
    public static final int cooldown = 1;
    public static boolean isReady = true;
    private static final String JalapenoImageAddress = "/Images/resources/graphics/Plants/Jalapeno/Jalapeno/Jalapeno.gif";
    private static final String BurnJalapenoImageAddress =  "/Images/resources/graphics/Plants/Jalapeno/JalapenoExplode/JalapenoAttack.gif";

    public Jalapeno() {
        //dont have idea about the hp!
        super(125, 100000, new Image(JalapenoImageAddress));
    }

    @Override
    public void burnZombies(){
        for(Zombie x : ZombieFactory.zombies){
            if(x.getRow() == getRow()){
                x.burn();
            }
        }
    }

    public static void startCooldown() {
        isReady = false;

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(cooldown), event -> {
            isReady = true;
        }));
        timeline.setCycleCount(1);
        timeline.play();
    }

    @Override
    public void updateImageSituation(Pane pane) {
        burnAnimation(BurnJalapenoImageAddress);
        startCooldown();
    }
}