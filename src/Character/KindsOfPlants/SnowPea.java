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
    private static final String snowPeaImageAddress = "/images/resources/graphics/Plants/SnowPea/SnowPea_";

    public SnowPea(int cost, int hp, int row) {
        super(cost, hp, row, new Image(snowPeaImageAddress + "0.png"));
    }

    @Override
    public void shoot(Pane pane) {
        Bullet snowBullet = new SnowBullet(getX(), getY(),getRow());
        snowBullet.addToPane(pane);
        bulletQueue.add(snowBullet);
    }

    @Override
    public void updateImageSituation(Pane pane) {
        playAnimation(14, snowPeaImageAddress );
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000), e ->{
            if(getCheckShot()){
                shoot(pane);
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }


}