package Character.KindsOfPlants;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class TallNut extends NutPlant {

    public static final int cooldown = 2;
    public static boolean isReady = true;
    private static final String TallNutImageAddress = "/Images/resources/graphics/Plants/TallNut/TallNut.gif";
    private static final String TallNutImageAddress1 = "/Images/resources/graphics/Plants/TallNut/TallNutCracked1.gif";
    private static final String TallNutImageAddress2 = "/Images/resources/graphics/Plants/TallNut/TallNutCracked2.gif";

    public TallNut() {
        super(125, 20, new Image(TallNutImageAddress));
        getImageView().setLayoutY(getImageView().getLayoutY() - 20);
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