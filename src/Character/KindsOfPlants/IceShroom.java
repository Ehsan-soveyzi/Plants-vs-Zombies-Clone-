package Character.KindsOfPlants;

import Character.KindsOfZombie.Zombie;
import Map.ZombieFactory;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import java.io.Serializable;
import java.util.ArrayList;


public class IceShroom extends Plant implements Serializable {
    private static final String iceShooterImageAddress = "/Images/resources/graphics/Plants/IceShroom/IceShroom.gif";
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
        startCooldown();
        freeze();

    }
    public static void startCooldown() {
        isReady = false;

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(cooldown), event -> {
            isReady = true;
        }));
        timeline.setCycleCount(1);
        timeline.play();
    }
    public void freeze() {
        die();
        for (Zombie zombie : new ArrayList<>(ZombieFactory.zombies)) {
            if (!zombie.isDead()) {
                freezeZombie(zombie);
                zombie.takeDamage(1);
            }
        }
    }
    public void freezeZombie(Zombie zombie) {
        if (zombie == null || zombie.isDead()) return;

        zombie.setFreezed(true);
        zombie.updateImageSituation();

        PauseTransition freeze = new PauseTransition(Duration.seconds(5));
        freeze.setOnFinished(event -> {
            zombie.getImageView().setEffect(null);
            zombie.setFreezed(false);
            if (zombie.getTimeline() != null && !zombie.isDead()) {
                zombie.getTimeline().play();
            }
        });

        zombie.setFreezeTimer(freeze);
        freeze.playFromStart();
        if (zombie.getTimeline() != null) {
            zombie.getTimeline().pause();
        }

    }

}
