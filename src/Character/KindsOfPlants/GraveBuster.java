package Character.KindsOfPlants;

import Map.GameMap;
import Map.Grave;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.Serializable;

public class GraveBuster extends Plant implements Serializable {
    private static final String graveBusterImageAddress = "/Images/resources/graphics/Plants/GraveBuster/GraveBuster.gif";
    private static final String graveBusterCardImageAddress = "/Images/resources/graphics/Cards/graveBuster.jpg";
    private Grave grave;
    public static int cooldown = 10;
    public static boolean isReady = true;
    public static Timeline cooldownTimeline;


    public GraveBuster() {
        super(75,5,new Image(graveBusterImageAddress),new Image(graveBusterCardImageAddress));
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

    public Grave findeGrave() {
        for(Grave grave : Grave.graves){
            if(grave.getCol() == getCol() && grave.getRow() == getRow()){
                return grave;
            }
        }
        return null;
    }

    @Override
    public void updateImageSituation(Pane pane) {
        startCooldown();
        grave = findeGrave();
        timeline = new Timeline(new KeyFrame(Duration.seconds(1),event -> {
            grave.setHp(grave.getHp() - 1);
        }));
        timeline.setCycleCount(grave.getHp());
        timeline.play();

        timeline.setOnFinished(event -> {
            ((Pane)getImageView().getParent()).getChildren().remove(getImageView());
            ((Pane)grave.getImageView().getParent()).getChildren().remove(grave.getImageView());
            GameMap.getInstance().setGraved(getRow(),getCol(),false);
            GameMap.getInstance().removePlant(getRow(),getCol());
            Grave.graves.remove(grave);
        });
    }

}
