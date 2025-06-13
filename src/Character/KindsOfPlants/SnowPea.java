package Character.KindsOfPlants;
import Character.Bullet;
import Character.SnowBullet;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.Objects;

public class SnowPea extends PeaPlant {
    private static final String snowPeaImageAddress = "/Images/resources/graphics/Plants/SnowPea/SnowPea_0.png";
    public SnowPea(int cost, int hp) {
        super(cost, hp, new Image(snowPeaImageAddress));

    }

    public void playAnimation() {
        boolean check = false;
        Image[] frames = new Image[14];
        for(int i = 0;i < 14;i++){
            frames[i] = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/resources/graphics/Plants/SnowPea/SnowPea_" + i + ".png")));
        }
        final int[] index = {0};
        ImageView SnowPea = getImageView();

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(20), e ->{
//            this.update(0.1);
            SnowPea.setImage(frames[index[0]]);
            index[0] = (index[0] + 1) % frames.length;
            sameRowZombies();
            sameRowBullet();
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    @Override
    public void shoot(Pane pane) {
        if(!isDead()) {
            Bullet snowBullet = new SnowBullet(getX(), getY(), getRow());
            snowBullet.addToPane(pane);
            bulletQueue.add(snowBullet);
        }
    }

    @Override
    public void updateImageSituation(Pane pane) {
        playAnimation();
        timeline = new Timeline(new KeyFrame(Duration.millis(1000), e ->{
            if(getCheckShot()){
                shoot(pane);
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }


}