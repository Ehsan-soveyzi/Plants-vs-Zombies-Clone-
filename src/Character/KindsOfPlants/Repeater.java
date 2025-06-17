package Character.KindsOfPlants;
import Character.Bullet;
import Character.NormalBullet;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Repeater extends PeaPlant {

    public static final int cooldown = 7;
    public static boolean isReady = true;
    private static final String repeaterImageAddress = "/Images/resources/graphics/Plants/RepeaterPea/Repeater.gif";

    public Repeater() {
        super(200, 5, new Image(repeaterImageAddress));
    }

    // we should check the same row for checking there is a zombie or not .
    @Override
    public void shoot(Pane pane) {
        if(!isDead()) {
            Bullet normalBullet = new NormalBullet(getX() + 10, getY(), getRow());
            normalBullet.addToPane(pane);
            bulletQueue.add(normalBullet);
        }
    }

    public static void startCooldown() {
        isReady = false;

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(cooldown), event -> {
            isReady = true;
        }));
        timeline.setCycleCount(1);
        timeline.play();
    }

    @Override
    public void updateImageSituation(Pane pane) {
        checkBullet();
        timeline = new Timeline(
                new KeyFrame(Duration.seconds(1.75), e -> {
                    if (getCheckShot()) shoot(pane);
                }),
                new KeyFrame(Duration.seconds(1.85), e -> {
                    if (getCheckShot()) shoot(pane);
                })
        );

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        startCooldown();

    }
}