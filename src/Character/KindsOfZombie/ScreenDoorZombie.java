package Character.KindsOfZombie;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.Objects;

public class ScreenDoorZombie extends Zombie {
    private final static String screenDoorImageAddress =  "/Images/resources/graphics/Zombies/BucketheadZombie/BucketheadZombie/BucketheadZombie_";
    static int count = 0;
    public ScreenDoorZombie() {}
    public ScreenDoorZombie(int row) {
        super(10, 30, 500, row,  new Image(screenDoorImageAddress + "0.png"));
        System.out.println("SCREEN " + (count++) + " " + row);
    }
    @Override
    public void updateImageSituation(){

    }
    @Override
    public void playEatingAnimation() {
        Image[] frames = new Image[11];
        for(int i = 0;i <= 10;i++){
            frames[i] = new Image(Objects.requireNonNull(getClass().getResourceAsStream(
                    "/Images/resources/graphics/Zombies/BucketheadZombie/BucketheadZombieAttack/BucketheadZombieAttack_" + i + ".png"
            )));
        }
        ImageView zombieView = getImageView();
        final int[] frameIndex = {0};
        timeline = new Timeline(new KeyFrame(Duration.millis(100), e -> {
            zombieView.setImage(frames[frameIndex[0]]);
            frameIndex[0] = (frameIndex[0] + 1) % frames.length;
            if(isDead()) {
                timeline.stop();
                setDead(true);
                die();
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.playFromStart();
    }
    public void playWalkingAnimation(Pane pane){
        super.playWalkingAnimation(pane, 14, screenDoorImageAddress);
    }
}


