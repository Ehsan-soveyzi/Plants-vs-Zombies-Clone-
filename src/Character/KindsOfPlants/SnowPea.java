package Character.KindsOfPlants;

import Character.Bullet;
import Character.SnowBullet;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import java.io.Serializable;

public class SnowPea extends PeaPlant implements Serializable {
    private static final String snowPeaImageAddress = "/images/resources/graphics/Plants/SnowPea/SnowPea.gif";
    private static final String snowPeaCardImageAddress = "/Images/resources/graphics/Cards/SnowPea.png";
    public static final ImageView cardView = new ImageView(new Image(snowPeaCardImageAddress));
    public static int cooldown = 7;
    public static boolean isReady = true;
    public static Timeline cooldownTimeline;

    public SnowPea() {
        super(175, 5, new Image(snowPeaImageAddress),new Image(snowPeaCardImageAddress));
    }

    @Override
    public void shoot(Pane pane) {
        if(!isDead()) {
            Bullet snowBullet = new SnowBullet(getX() + 5, getY(), getRow());
            snowBullet.addToPane(pane);
            bulletList.add(snowBullet);
        }
    }

    @Override
    public void updateImageSituation(Pane pane) {
        checkBullet();
        timeline = new Timeline(new KeyFrame(Duration.seconds(1.75), e ->{
            if(getCheckShot()){
                shoot(pane);
            }
            if(isDead())timeline.stop();
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        startCooldown();
    }

    public static void startCooldown() {
        isReady = false;

        cooldownTimeline = new Timeline(new KeyFrame(Duration.seconds(1),event -> {
            cooldown--;
            if(cooldown == 0) {
                cooldown = 7;
                isReady = true;
                cooldownTimeline.stop();
            }
        }));
        cooldownTimeline.setCycleCount(7);
        cooldownTimeline.play();
    }

}