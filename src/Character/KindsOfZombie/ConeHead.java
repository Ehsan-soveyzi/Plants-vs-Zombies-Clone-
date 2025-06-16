package Character.KindsOfZombie;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.Objects;

public class ConeHead extends Zombie {
    private final static String coneHeadImageAddress =
            "/Images/resources/graphics/Zombies/ConeheadZombie/ConeheadZombie/ConeheadZombie_";

    public ConeHead(int row) {
        super(7,30,500,row,new Image(coneHeadImageAddress + "0.png"));
    }


    public void  playEatingAnimation(){
        Image[] frames = new Image[11];
        for(int i = 0;i < 11;i++){
            frames[i] = new Image(Objects.requireNonNull(getClass().getResourceAsStream(
                    "/Images/resources/graphics/Zombies/ConeheadZombie/ConeheadZombieAttack/ConeheadZombieAttack_" + i + ".png"
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
        super.playWalkingAnimation(pane, 20, coneHeadImageAddress);
    }
}



