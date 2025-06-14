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
    public static final int cooldown = 7;
    public static boolean isReady = true;

    public PeaShooter() {
        super(100,5, new Image(peaShooterImageAddress + "0.png"));
    }

    @Override
    public void shoot(Pane pane) {
            if(!isDead()) {
//        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000), event -> {
                Bullet normalBullet = new NormalBullet(getX() + 10, getY(), getRow());
                normalBullet.addToPane(pane);
                bulletQueue.add(normalBullet);
//        }));
//        timeline.setCycleCount(Timeline.INDEFINITE);
//        timeline.play();
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

//    public void playAnimation() {
//        Image[] frames = new Image[12];
//        for(int i = 0;i < 12;i++){
//            frames[i] = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/resources/graphics/Plants/Peashooter/Peashooter_" + i + ".png")));
//        }
//        final int[] index = {0};
//        ImageView peaShooter = getImageView();
//
//         timeline = new Timeline(new KeyFrame(Duration.millis(80), e ->{
////            this.update(0.1);
//            peaShooter.setImage(frames[index[0]]);
//            index[0] = (index[0] + 1) % frames.length;
//            sameRowZombies();
//            sameRowBullet();
//        }));
//        timeline.setCycleCount(Timeline.INDEFINITE);
//        timeline.play();
//    }




    @Override
    public void updateImageSituation(Pane pane) {
        playAnimation(12,peaShooterImageAddress);
        timeline = new Timeline(new KeyFrame(Duration.seconds(1.75), e ->{
            if(getCheckShot()){
                shoot(pane);
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

    }
}