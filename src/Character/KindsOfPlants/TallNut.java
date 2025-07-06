package Character.KindsOfPlants;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import java.io.Serializable;

public class TallNut extends NutPlant implements Serializable {
    private static final String tallNutCardImageAddress = "/Images/resources/graphics/Cards/TallNut.png";
    private static final String TallNutImageAddress = "/Images/resources/graphics/Plants/TallNut/TallNut.gif";
    private static final String TallNutImageAddress1 = "/Images/resources/graphics/Plants/TallNut/TallNutCracked1.gif";
    private static final String TallNutImageAddress2 = "/Images/resources/graphics/Plants/TallNut/TallNutCracked2.gif";
    public static int cooldown = 10;
    public static boolean isReady = true;
    public static Timeline cooldownTimeline;

    public TallNut() {
        super(125, 20, new Image(TallNutImageAddress),new Image(tallNutCardImageAddress));
        getImageView().setLayoutY(getImageView().getLayoutY() - 20);
    }

    public static void startCooldown() {
        isReady = false;

        cooldownTimeline = new Timeline(new KeyFrame(Duration.seconds(1),event -> {
            cooldown--;
            if(cooldown == 0) {
                cooldown = 10;
                isReady = true;
                cooldownTimeline.stop();
            }
        }));
        cooldownTimeline.setCycleCount(10);
        cooldownTimeline.play();
    }

    @Override
    public void updateImageSituation(Pane pane) {
        changeImage();
        startCooldown();
    }

    @Override
    public void changeImage() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100),e ->{
            if(getHp() == 13)getImageView().setImage(new Image(TallNutImageAddress1));
            if(getHp() == 6)getImageView().setImage(new Image(TallNutImageAddress2));
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
}