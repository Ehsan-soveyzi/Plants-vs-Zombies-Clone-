package Character.KindsOfPlants;
import Character.Bullet;
import Character.KindsOfZombie.Zombie;
import Character.NormalBullet;
import Character.SlowBullet;
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

    private static final String peaShooterImageAddress = "/Images/resources/graphics/Plants/Peashooter/Peashooter_0.png";

    public PeaShooter(int cost, int hp) {
        super(cost, hp, new Image(peaShooterImageAddress));

    }

    @Override
    public void shoot(Pane pane) {

//        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000), event -> {
            Bullet normalBullet = new NormalBullet(getX() + 20,getY(),getRow(), 1, 40);
            normalBullet.addToPane(pane);
            bulletQueue.add(normalBullet);
//        }));
//        timeline.setCycleCount(Timeline.INDEFINITE);
//        timeline.play();
    }


    public void playAnimation() {
        boolean check = false;
        Image[] frames = new Image[12];
        for(int i = 0;i < 12;i++){
            frames[i] = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/resources/graphics/Plants/Peashooter/Peashooter_" + i + ".png")));
        }
        final int[] index = {0};
        ImageView peaShooter = getImageView();

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(20), e ->{
//            this.update(0.1);
            peaShooter.setImage(frames[index[0]]);
            index[0] = (index[0] + 1) % frames.length;
            sameRowZombies();
            sameRowBullet();
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }



    @Override
    public void updateImageSituation(Pane pane) {
        playAnimation();
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000), e ->{
            if(getCheckShot()){
                shoot(pane);
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

    }
}