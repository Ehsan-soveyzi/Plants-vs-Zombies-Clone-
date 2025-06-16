package Character.KindsOfPlants;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class WallNut extends NutPlant {

    public static final int cooldown = 7;
    public static boolean isReady = true;
    private static final String WallNutImageAddress = "/Images/resources/graphics/Plants/WallNut/WallNut/WallNut.gif";
    private static final String WallNutImageAddress1 = "/Images/resources/graphics/Plants/WallNut/WallNut/WallNut1.gif";
    private static final String WallNutImageAddress2 = "/Images/resources/graphics/Plants/WallNut/WallNut/WallNut2.gif";

    public WallNut() {
        super(50, 10, new Image(WallNutImageAddress));
    }

    @Override
    public void updateImageSituation(Pane pane) {
        changeImage();
        startCooldown();
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
    public void changeImage() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100),e ->{
            if(getHp() == 7)getImageView().setImage(new Image(WallNutImageAddress1));
            if(getHp() == 3)getImageView().setImage(new Image(WallNutImageAddress2));
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
}