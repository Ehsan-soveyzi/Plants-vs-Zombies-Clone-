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

    private static final String peaShooterImageAddress = "/Images/resources/graphics/Plants/Peashooter/Peashooter_";

    public PeaShooter(int cost, int hp, int row) {
        super(cost, hp,row, new Image(peaShooterImageAddress + "0.png"));

    }
    @Override
    public void shoot(Pane pane) {

//        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000), event -> {
            Bullet normalBullet = new NormalBullet(getX() + 20,getY(),getRow());
            normalBullet.addToPane(pane);
            bulletQueue.add(normalBullet);
//        }));
//        timeline.setCycleCount(Timeline.INDEFINITE);
//        timeline.play();
    }

    @Override
    public void updateImageSituation(Pane pane) {
        playAnimation(12, peaShooterImageAddress);
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000), e ->{
            if(getCheckShot()){
                shoot(pane);
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

    }
}