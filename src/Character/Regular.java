package Character;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.Objects;

public class Regular extends Zombie {
    private final static String regularImageAddress = "/Character/regular.png";
    // or have a image field in parent class and
    // static because before making this field the super execute
    // میتونیم تعریف نکنیم صرفا این فیلد رو همون ادرس رو مستقیم بدیم

    public Regular(int hp, double speed, double eatingSpeed, int row, double x, double y) {
        super(hp, speed, eatingSpeed, row, x, y, new Image(regularImageAddress));
    }

    @Override
    protected void playWalkingAnimation() {

        time = new Timeline(new KeyFrame(Duration.millis(1000)));

            Image[] frames = new Image[22];
            for (int i = 0; i < 22; i++) {
                frames[i] = new Image(Objects.requireNonNull(getClass().getResourceAsStream(
                        "/Images/resources/graphics/Zombies/NormalZombie/Zombie/Zombie_" + i + ".png"
                )));
            }

            ImageView zombieView = getImageView();

            final int[] frameIndex = {0};

            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), e -> {
                this.update(0.1);
                zombieView.setImage(frames[frameIndex[0]]);
                frameIndex[0] = (frameIndex[0] + 1) % frames.length;
            }));

            timeline.setCycleCount(Animation.INDEFINITE);
            timeline.play();
        }


    protected void  playEatingAnimation(){

    }
}



