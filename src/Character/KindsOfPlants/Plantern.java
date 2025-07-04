package Character.KindsOfPlants;

import Map.GameMap;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.security.Key;

public class Plantern extends Plant {
    private static final String planternImageAddress = "/Images/resources/graphics/Plants/Plantern/Plantern.gif";
    private static final String planternCardImageAddress = "/Images/resources/graphics/Cards/plantern.jpg";
    public static int cooldown = 1;
    public static boolean isReady = true;
    public static Timeline cooldownTimeline;


    public Plantern() {
        super(25,5,new Image(planternImageAddress),new Image(planternCardImageAddress));
        //just for handling the size of imageview
        getImageView().setLayoutX(getImageView().getLayoutX() - 80);
        getImageView().setLayoutY(getImageView().getLayoutY() - 80);
    }

    public static void startCooldown() {
        isReady = false;
        cooldownTimeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            cooldown--;
            if(cooldown == 0) {
                cooldown = 1;
                isReady = true;
                cooldownTimeline.stop();
            }
        }));
        cooldownTimeline.setCycleCount(7);
        cooldownTimeline.play();
    }

    public void setFog(boolean fog) {
        for(int i = getRow() - 1;i < getRow() + 2; i++) {
            for(int j = getCol() - 1;j < getCol() + 2; j++) {
                if(i < 0 || i > 4 || j < 0 || j > 8)break;
                GameMap.getInstance().setFoged(i,j,fog);
            }
        }
    }

    @Override
    public void updateImageSituation(Pane pane) {
        startCooldown();
        timeline = new Timeline(new KeyFrame(Duration.millis(100),event ->{
            setFog(false);
            if(isDead()){
                System.out.println("Plantern dead");
                setFog(true);
                timeline.stop();
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
}
