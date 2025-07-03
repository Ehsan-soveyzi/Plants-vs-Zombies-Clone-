package Character.KindsOfPlants;

import Character.KindsOfZombie.Zombie;
import Map.ZombieFactory;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import Character.Bullet;
import Character.ShroomBullet;

import java.io.Serializable;

public class ScaredyShroom extends PeaPlant implements Serializable {

    public static final String cardViewImageAddress = "/Images/resources/graphics/Cards/ScaredyShroom.png";
    private static final String scaredyShroomImageAddress = "/Images/resources/graphics/Plants/ScaredyShroom/ScaredyShroom/ScaredyShroom.gif";
    private static final String ScaredyShroomCryImageAddress = "/Images/resources/graphics/Plants/ScaredyShroom/ScaredyShroomCry/ScaredyShroomCry.gif";
    private static final String ScaredyShroomSleepImageAddress =  "/Images/resources/graphics/Plants/ScaredyShroom/ScaredyShroomSleep/ScaredyShroomSleep.gif";
    public static int cooldown = 7;
    public static boolean isReady = true;
    public static Timeline cooldownTimeline;
    private boolean morningAwake = false;
    private boolean isMorning;

    public ScaredyShroom() {
        this(true);
    }
    public ScaredyShroom(boolean isMorning){
        this(!isMorning ? scaredyShroomImageAddress : ScaredyShroomSleepImageAddress);
        this.isMorning = isMorning;
    }
    public ScaredyShroom(String imageAddress) {
        super(25, 5, new Image(imageAddress), new Image(cardViewImageAddress));
    }


    @Override
    public void shoot(Pane pane) {
        if(!isDead()) {
            Bullet shroomBullet = new ShroomBullet(getX() + 15, getY() + 40, getRow());
            shroomBullet.addToPane(pane);
            bulletList.add(shroomBullet);
        }
    }


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

            if (z.getRow() == getRow() && z.getX() - 10 >= getX() && z.getCol() - getCol() >= 2) {
                zombieInRow = true;
            }
            else if (z.getRow() == getRow() && z.getX() - 10 >= getX() && z.getCol() - getCol() < 2){
                getImageView().setImage(new Image(ScaredyShroomCryImageAddress));
                zombieInRow = false;
                break;
            }
            else getImageView().setImage(new Image(scaredyShroomImageAddress));
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
