package Character;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.util.Duration;

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

    }

    protected void  playEatingAnimation(){

    }
}



