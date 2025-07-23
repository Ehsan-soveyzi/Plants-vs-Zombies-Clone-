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
    public static int cooldown = 5;
    public static Timeline cooldownTimeline;

    public PuffShroom() {
        super(0, 5, new Image(puffShroomImageAddress), new Image(cardViewImageAddress));
        setShroom(true);
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
        if(!isDay()){
            getImageView().setImage(new Image(puffShroomImageAddress));
            checkBullet();
            timeline = new Timeline(new KeyFrame(Duration.seconds(1.75), e ->{
                if(getCheckShot()){
                    shoot(pane);
                }
            }));
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();
        }
        else{
            getImageView().setImage(new Image(puffShroomSleepImage));
        }
    }

    public static void startCooldown() {
        isReady = false;

        cooldownTimeline = new Timeline(new KeyFrame(Duration.seconds(1),event -> {
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
    public void sameRowZombies(){
        boolean zombieInRow = false;

        for (Zombie z : ZombieFactory.zombies) {
            if (z.getRow() == getRow() && z.getX() - 10 >= getX() && z.getCol() - getCol() <= 4) {
                zombieInRow = true;
                break;
            }
        }
        setCheckShot(zombieInRow);
    }
}
