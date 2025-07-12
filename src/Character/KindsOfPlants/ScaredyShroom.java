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
    public static int cooldown = 5;
    public static boolean isReady = true;
    public static Timeline cooldownTimeline;
    static final Image scaredyShroomCry = new Image(ScaredyShroomCryImageAddress);
    static final Image scaredyShroom = new Image(scaredyShroomImageAddress);
    public ScaredyShroom() {
        super(25, 5, new Image(scaredyShroomImageAddress), new Image(cardViewImageAddress));
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

    public void updateImageSituation(Pane pane) {
        startCooldown();
        if(!isDay()){
            getImageView().setImage(new Image(scaredyShroomImageAddress));
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
            getImageView().setImage(new Image(ScaredyShroomSleepImageAddress));
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

            if (z.getRow() == getRow() && z.getX() - 10 >= getX() && z.getCol() - getCol() >= 2) {
                zombieInRow = true;
            }
            else if (z.getRow() == getRow() && z.getX() - 10 >= getX() && z.getCol() - getCol() < 2){
                getImageView().setImage(scaredyShroomCry);
                zombieInRow = false;
                break;
            }
            else {
                getImageView().setImage(scaredyShroom);
            }
        }
        setCheckShot(zombieInRow);
    }

}
