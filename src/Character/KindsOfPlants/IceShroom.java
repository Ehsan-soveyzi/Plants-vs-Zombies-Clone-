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
    private static final String iceShroomSleepImage = "/Images/resources/graphics/Plants/IceShroom/IceShroomSleep/IceShroomSleep.gif";
    public static int cooldown = 7;
    public static boolean isReady = true;
    public static Timeline cooldownTimeline;
    private boolean morningAwake = false;
    private boolean isMorning;



    public IceShroom() {
        this(true);
    }
    public IceShroom(boolean isMorning){
        this(!isMorning ? iceShroomImageAddress : iceShroomSleepImage, !isMorning  ? 0 : 5);
        this.isMorning = isMorning;
    }
    public IceShroom(String imageAddress, int hp) {
        super(75, hp, new Image(imageAddress), new Image(iceShroomCardImageAddress));
    }

    @Override
    public void updateImageSituation(Pane pane) {
        startCooldown();
        if (!isMorning || morningAwake) {
            nightActions(pane);
        } else {
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(500), e -> {
                if (morningAwake) {
                    nightActions(pane);
                    ((Timeline)e.getSource()).stop();
                }
            }));
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();
        }
    }
    private void nightActions(Pane pane) {
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
    public boolean isMorningAwake() {
        return morningAwake;
    }
    public void setMorningAwake(boolean morningAwake) {
        this.morningAwake = morningAwake;
    }

}
