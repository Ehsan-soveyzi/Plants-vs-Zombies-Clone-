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

    public static final int cooldown = 7;
    public static boolean isReady = true;
    private static final String snowPeaImageAddress = "/images/resources/graphics/Plants/SnowPea/SnowPea.gif";
    public SnowPea() {
        super(175, 5, new Image(snowPeaImageAddress));
    }

    @Override
    public void shoot(Pane pane) {
        if(!isDead()) {
            Bullet snowBullet = new SnowBullet(getX() + 5, getY(), getRow());
            snowBullet.addToPane(pane);
            bulletQueue.add(snowBullet);
        }
    }

    @Override
    public void updateImageSituation(Pane pane) {
        checkBullet();
        timeline = new Timeline(new KeyFrame(Duration.seconds(1.75), e ->{
            if(getCheckShot()){
                shoot(pane);
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        startCooldown();
    }

    public static void startCooldown() {
        isReady = false;

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(cooldown),event -> {
            isReady = true;
        }));
        timeline.setCycleCount(1);
        timeline.play();
    }


}