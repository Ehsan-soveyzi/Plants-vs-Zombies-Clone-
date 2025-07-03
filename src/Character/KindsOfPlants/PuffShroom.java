package Character.KindsOfPlants;

import ApplyGraphics.ModeController;
import Character.KindsOfZombie.Zombie;
import Map.ZombieFactory;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import Character.Bullet;
import Character.ShroomBullet;
import javafx.util.Duration;

import java.io.Serializable;

public class PuffShroom extends PeaPlant implements Serializable {
    private static final String cardViewImageAddress = "/Images/resources/graphics/Cards/PuffShroom.png";
    private static final String puffShroomImageAddress = "/Images/resources/graphics/Plants/PuffShroom/PuffShroom.gif";
    private static final String puffShroomSleepImage = "/Images/resources/graphics/Plants/PuffShroom/PuffShroomSleep.gif";
    public static boolean isReady = true;
    public static int cooldown = 7;
    public static Timeline cooldownTimeline;
    private boolean morningAwake = false;
    private boolean isMorning;

    public PuffShroom() {
        this(true);
    }
    public PuffShroom(boolean isMorning){
        this(!isMorning ? puffShroomImageAddress : puffShroomSleepImage);
        this.isMorning = isMorning;
    }
    public PuffShroom(String imageAddress) {
        super(0, 5, new Image(imageAddress), new Image(cardViewImageAddress));
    }

    @Override
    public void shoot(Pane pane) {
        if(!isDead()) {
            Bullet shroomBullet = new ShroomBullet(getX() + 15, getY() + 40, getRow());
            shroomBullet.addToPane(pane);
            bulletList.add(shroomBullet);
        }
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
        checkBullet();
        timeline = new Timeline(new KeyFrame(Duration.seconds(1.75), e ->{
            if(getCheckShot()){
                shoot(pane);
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
    public static void startCooldown() {
        isReady = false;

        cooldownTimeline = new Timeline(new KeyFrame(Duration.seconds(1),event -> {
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
    public void sameRowZombies(){
        boolean zombieInRow = false;

        for (Zombie z : ZombieFactory.zombies) {
            if (z.getRow() == getRow() && z.getX() - 10 >= getX() && z.getCol() - getCol() <= 4) {
                zombieInRow = true;
            }
        }
        setCheckShot(zombieInRow);
    }
    public boolean isMorningAwake() {
        return morningAwake;
    }
    public void setMorningAwake(boolean morningAwake) {
        this.morningAwake = morningAwake;
    }
}
