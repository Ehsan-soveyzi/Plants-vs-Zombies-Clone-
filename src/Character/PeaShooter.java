package Character;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.Objects;

public class PeaShooter extends PeaPlant {

    private static final String peaShooterImageAddress = "/Character/peaShooter.png";

    public PeaShooter(int cost, int hp, double x, double y, int row) {
        super(cost, hp, x, y, row, new Image(peaShooterImageAddress));

    }

    @Override
    public void shoot() {
        Bullet normalBullet = new NormalBullet(getX(),getY(),getRow(), 1, 0.5);

    }
    public void playAnimation() {
        Image[] frames = new Image[12];
        for(int i = 0;i < 12;i++){
            frames[i] = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/resources/graphics/Plants/Peashooter/Peashooter_" + i + ".png")));
        }
        final int[] index = {0};
        ImageView peaShooter = getImageView();

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), e ->{
//            this.update(0.1);
            peaShooter.setImage(frames[index[0]]);
            index[0] = (index[0] + 1) % frames.length;
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }



}