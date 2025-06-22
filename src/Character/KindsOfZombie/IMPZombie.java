package Character.KindsOfZombie;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.Objects;

public class IMPZombie extends Zombie {
    private final static String IMPZombieImageAddress = "/Images/resources/graphics/Zombies/NewspaperZombie/NewspaperZombie/NewspaperZombie_";
    static int count = 0;
    public IMPZombie() {}
    public IMPZombie(int row) {
        super(3, 40, 500, row,new Image(IMPZombieImageAddress + "0.png"));
        System.out.println("IMPZombie " + (count++) + " " + row);
    }



    @Override
    public void playEatingAnimation() {
        Image[] frames = new Image[8];
        for(int i = 0;i <= 7;i++){
            frames[i] = new Image(Objects.requireNonNull(getClass().getResourceAsStream(
                    "/Images/resources/graphics/Zombies/NewspaperZombie/NewspaperZombieAttack/NewspaperZombieAttack_" + i + ".png"
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
        super.playWalkingAnimation(pane, 18, IMPZombieImageAddress);
    }
}
