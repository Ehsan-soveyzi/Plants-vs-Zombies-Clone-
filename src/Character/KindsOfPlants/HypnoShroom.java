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
    private boolean morningAwake = false;
    private boolean isMorning;

    public HypnoShroom() {
        this(true);
    }
    public HypnoShroom(boolean isMorning){
        this(!isMorning ? hypnoShroomImageAddress : hypnoShroomSleepImageAddress, !isMorning ? 0 : 5);
        this.isMorning = isMorning;
    }
    public HypnoShroom(String imageAddress, int hp) {
        super(75, hp, new Image(imageAddress), new Image(hypnoShroomCardImageAddress));
    }

    public static void startCooldown() {
        isReady = false;

        cooldownTimeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            cooldown--;
            if(cooldown == 0) {
                cooldown = 5;
                isReady = true;
                cooldownTimeline.stop();
            }
        }));
        cooldownTimeline.setCycleCount(5);
        cooldownTimeline.play();
    }

    @Override
    public void updateImageSituation(Pane pane) {
        startCooldown();
    }
    public boolean isMorningAwake() {
        return morningAwake;
    }
    public void setMorningAwake(boolean morningAwake) {
        this.morningAwake = morningAwake;
    }
}
