package Character.KindsOfPlants;
import Character.Bullet;
import Character.NormalBullet;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.Serializable;
import java.sql.Time;

public class PeaShooter extends PeaPlant implements Serializable {

    private static final String peaShooterImageAddress = "/Images/resources/graphics/Plants/Peashooter/Peashooter.gif";
    public static final int cooldown = 7;
    public static boolean isReady = true;
    public static Timeline cooldownTimeline;

    public PeaShooter() {
        super(100,5, new Image(peaShooterImageAddress));
    }

    @Override
    public void shoot(Pane pane) {
            if(!isDead()) {
                Bullet normalBullet = new NormalBullet(getX() + 10, getY(), getRow());
                normalBullet.addToPane(pane);
                bulletList.add(normalBullet);
            }
    }

    public static void startCooldown() {
        isReady = false;

         cooldownTimeline = new Timeline(new KeyFrame(Duration.seconds(cooldown),event -> {
            isReady = true;
        }));
        cooldownTimeline.setCycleCount(1);
        cooldownTimeline.play();
    }





    @Override
    public void updateImageSituation(Pane pane) {
        checkBullet();
        timeline = new Timeline(new KeyFrame(Duration.seconds(1.75), e ->{
            if(getCheckShot()){
                shoot(pane);
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        startCooldown();

    }
}