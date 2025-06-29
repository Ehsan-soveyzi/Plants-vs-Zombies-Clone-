package Character.KindsOfPlants;

import Character.KindsOfZombie.Zombie;
import Map.ZombieFactory;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.Serializable;

public class IceShroom extends Plant implements Serializable {
    private static final String iceShooterImageAddress = "/new_resources/images/Plants/IceShroom/IceShroom.gif";
    private static final String iceShooterCardImageAddress = "/Images/resources/graphics/Cards/IceShroom.png";
    public static int cooldown = 7;
    public static boolean isReady = true;
    public static Timeline cooldownTimeline;
    public static PauseTransition freezeTimer;

    public IceShroom() {
        super(75,0,new Image(iceShooterImageAddress),new Image(iceShooterCardImageAddress));
    }



    @Override
    public void updateImageSituation(Pane pane) {
        freeze();
        startCooldown();

    }
    public static void startCooldown() {
        isReady = false;

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(cooldown), event -> {
            isReady = true;
        }));
        timeline.setCycleCount(1);
        timeline.play();
    }
    public void freeze(){
        PauseTransition removeTimer = new PauseTransition(Duration.seconds(2));
        removeTimer.setOnFinished(event -> {
            die();

        });
        for (Zombie zombie : ZombieFactory.zombies){
            freezeZombie(zombie);
        }

    }
    public void freezeZombie(Zombie zombie) {
        if (freezeTimer != null) freezeTimer.stop();
        zombie.setFreezed(true);
        //new timer for affect slowing for 5 sec!
        freezeTimer = new PauseTransition(Duration.seconds(5));
        freezeTimer.setOnFinished(event -> {
            zombie.getImageView().setEffect(null);
            zombie.setFreezed(false);
            zombie.takeDamage(1);
            zombie.getTimeline().play();


        });
        freezeTimer.playFromStart();

    }

}
