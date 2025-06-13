package Character.KindsOfZombie;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.Objects;

public class Regular extends Zombie {
    private final static String regularImageAddress =
            "/Images/resources/graphics/Zombies/NormalZombie/Zombie/Zombie_0.png";
    // or have a image field in parent class and
    // static because before making this field the super execute
    // میتونیم تعریف نکنیم صرفا این فیلد رو همون ادرس رو مستقیم بدیم

    public Regular(int row) {
        super(5,30,500,row,new Image(regularImageAddress));
    }

    @Override
    public void playWalkingAnimation(Pane pane) {
            addToPane(pane);
            Image[] frames = new Image[22];
            for (int i = 0; i < 22; i++) {
                frames[i] = new Image(Objects.requireNonNull(getClass().getResourceAsStream(
                        "/Images/resources/graphics/Zombies/NormalZombie/Zombie/Zombie_" + i + ".png"
                )));
            }

            ImageView zombieView = getImageView();

            final int[] frameIndex = {0};

             timeline = new Timeline(new KeyFrame(Duration.millis(100), e -> {
                this.update(0.1);
                zombieView.setImage(frames[frameIndex[0]]);
                frameIndex[0] = (frameIndex[0] + 1) % frames.length;
            }));

            timeline.setCycleCount(Animation.INDEFINITE);
            timeline.play();
    }


    public void  playEatingAnimation(){
        Image[] frames = new Image[21];
        for(int i = 0;i < 21;i++){
            frames[i] = new Image(Objects.requireNonNull(getClass().getResourceAsStream(
                    "/Images/resources/graphics/Zombies/NormalZombie/ZombieAttack/ZombieAttack_" + i + ".png"
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
}



