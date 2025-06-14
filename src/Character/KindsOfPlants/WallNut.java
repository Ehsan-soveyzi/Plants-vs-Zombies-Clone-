package Character.KindsOfPlants;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class WallNut extends NutPlant {

    public static final int cooldown = 7;
    public static boolean isReady = true;
    private static final String WallNutImageAddress = "/Images/resources/graphics/Plants/WallNut/WallNut/WallNut_";
//    private static final String WallNutImageAddress = "C:\\Users\\user\\Downloads\\wallNut.";

    public WallNut() {
        super(50, 10, new Image(WallNutImageAddress + "0.png"));
    }

    @Override
    public void updateImageSituation(Pane pane) {
        playAnimation(15, WallNutImageAddress);
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
}