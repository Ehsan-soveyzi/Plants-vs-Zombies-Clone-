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
    private static final String iceShroomImageAddress = "/Images/resources/graphics/Plants/IceShroom/IceShroom/IceShroom.gif";
    private static final String iceShroomCardImageAddress = "/Images/resources/graphics/Cards/IceShroom.png";
    public static int cooldown = 20;
    private static final String iceShroomSleepImage = "/Images/resources/graphics/Plants/IceShroom/IceShroomSleep/IceShroomSleep.gif";
    public static boolean isReady = true;
    public static Timeline cooldownTimeline;

    public IceShroom() {
        super(75,7, new Image(iceShroomImageAddress), new Image(iceShroomCardImageAddress));
        setShroom(true);
    }

    @Override
    public void updateImageSituation(Pane pane) {
        startCooldown();
        if(!isDay()){
            getImageView().setImage(new Image(iceShroomImageAddress));
            setHp(1000);
            timeline = new Timeline(new KeyFrame(Duration.seconds(1),event -> {
                freeze();
            }));
            timeline.setCycleCount(1);
            timeline.play();
        }else{
            getImageView().setImage(new Image(iceShroomSleepImage));
        }
    }

    public void startCooldown() {
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
                zombie.setFreezed(true);
                zombie.takeDamage();
                zombie.updateImageSituation();
        }
    }

}
