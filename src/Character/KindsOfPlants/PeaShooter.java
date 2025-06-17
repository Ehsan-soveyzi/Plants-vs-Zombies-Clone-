package Character.KindsOfPlants;
import Character.Bullet;
import Character.KindsOfZombie.Zombie;
import Character.NormalBullet;
import Character.SnowBullet;
import Map.ZombieFactory;
import Map.MapController;
import Map.ZombieFactory;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;


import java.util.Objects;

public class PeaShooter extends PeaPlant {

    private static final String peaShooterImageAddress = "/Images/resources/graphics/Plants/Peashooter/Peashooter.gif";
    public static final int cooldown = 7;
    public static boolean isReady = true;

    public PeaShooter() {
        super(100,5, new Image(peaShooterImageAddress));
    }

    @Override
    public void shoot(Pane pane) {
            if(!isDead()) {
                Bullet normalBullet = new NormalBullet(getX() + 10, getY(), getRow());
                normalBullet.addToPane(pane);
                bulletQueue.add(normalBullet);
            }
    }

    public static void startCooldown() {
        isReady = false;

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(cooldown),event -> {
            isReady = true;
        }));
        timeline.setCycleCount(1);
        timeline.play();
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