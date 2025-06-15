package Character.KindsOfPlants;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public  class CherryBomb extends BombPlant {
    public static final int cooldown = 7;
    public static boolean isReady = true;
    private static final String cherryBombImageAddress = "/Images/resources/graphics/Plants/CherryBomb/CherryBomb_";
    public CherryBomb() {
        //dont have idea about the hp!
        super(150, 100000, new Image(cherryBombImageAddress + "0.png"));

    }

    @Override
    public void updateImageSituation(Pane pane) {
        playAnimation(6, cherryBombImageAddress);
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
    public void burnZombies(){

    }
}