package Character.KindsOfPlants;

import Character.Bullet;
import Character.NormalBullet;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import java.io.Serializable;

public class Repeater extends PeaPlant implements Serializable {
    private static final String repeaterImageAddress = "/Images/resources/graphics/Plants/RepeaterPea/Repeater.gif";
    private static final String repeaterCardImageAddress = "/Images/resources/graphics/Cards/Repeater.png";
    public static int cooldown = 7;
    public static boolean isReady = true;
    public static Timeline cooldownTimeline;

    public Repeater() {
        super(200, 5, new Image(repeaterImageAddress),new Image(repeaterCardImageAddress));
    }

    // we should check the same row for checking there is a zombie or not .
    @Override
    public void shoot(Pane pane) {
        if(!isDead()) {
            Bullet normalBullet = new NormalBullet(getX() + 10, getY(), getRow());
            normalBullet.addToPane(pane);
            bulletList.add(normalBullet);
        }
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

    @Override
    public void updateImageSituation(Pane pane) {
        checkBullet();
        timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), e -> {
                    if (getCheckShot()) shoot(pane);
                    if(isDead())timeline.stop();
                }),
                new KeyFrame(Duration.seconds(1.1), e -> {
                    if (getCheckShot()) shoot(pane);
                })
        );

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        startCooldown();

    }
}