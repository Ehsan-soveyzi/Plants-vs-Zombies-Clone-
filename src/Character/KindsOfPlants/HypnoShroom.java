package Character.KindsOfPlants;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.Serializable;

public class HypnoShroom extends Plant implements Serializable {
    private static final String hypnoShroomImageAddress = "/Images/resources/graphics/Plants/HypnoShroom/HypnoShroom/HypnoShroom.gif";
    private static final String hypnoShroomCardImageAddress = "/Images/resources/graphics/Cards/HypnoShroom.png";
    private static final String hypnoShroomSleepImageAddress = "/Images/resources/graphics/Plants/HypnoShroom/HypnoShroomSleep/HypnoShroomSleep.gif";
    public static int cooldown = 7;
    public static boolean isReady = true;
    public static Timeline cooldownTimeline;

    public HypnoShroom() {
        super(75,5, new Image(hypnoShroomImageAddress), new Image(hypnoShroomCardImageAddress));
        setShroom(true);
    }

    public static void startCooldown() {
        isReady = false;

        cooldownTimeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            cooldown--;
            if(cooldown == 0) {
                cooldown = 7;
                isReady = true;
                cooldownTimeline.stop();
            }
        }));
        cooldownTimeline.setCycleCount(7);
        cooldownTimeline.play();
    }

    @Override
    public void updateImageSituation(Pane pane) {
        startCooldown();
        if(!isDay()){
            getImageView().setImage(new Image(hypnoShroomImageAddress));
            setHp(1);

        }else{
            getImageView().setImage(new Image(hypnoShroomSleepImageAddress));
        }
    }

}
