package Character.KindsOfPlants;

import Map.GameMap;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import java.io.Serializable;

public class Blover extends Plant implements Serializable {
    private static final String bloverImageAddress = "/Images/resources/graphics/Plants/Blover/blover.gif";
    private static final String bloverCardImageAddress = "/Images/resources/graphics/Cards/blover.jpg";
    public static int cooldown = 7;
    public static boolean isReady = true;
    public static Timeline cooldownTimeline;

    public Blover() {
        super(100,1000,new Image(bloverImageAddress),new Image(bloverCardImageAddress));
    }

    public void removeFog(){
        for(int i = 0;i < 5;i++){
            for(int j = 5;j < 9;j++){
                GameMap.getInstance().setFoged(i,j,false);
            }
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
        startCooldown();
        timeline = new Timeline(new KeyFrame(Duration.millis(50),event -> {
            removeFog();
        }));
        timeline.setCycleCount(100);
        timeline.play();
        timeline.setOnFinished(event -> {
            GameMap.getInstance().initializeFog();
            ((Pane)getImageView().getParent()).getChildren().remove(getImageView());
            die();
        });

    }
}
