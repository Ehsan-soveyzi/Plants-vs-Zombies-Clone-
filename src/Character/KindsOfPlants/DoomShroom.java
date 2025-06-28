package Character.KindsOfPlants;

import Character.KindsOfZombie.Zombie;
import Map.ZombieFactory;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.Serializable;

public class DoomShroom extends Shroom implements Serializable {
    public static final int cooldown = 7;
    public static boolean isReady = true;
    private static final String doomShroomAddress = "/new_resources/images/Plants/DoomShroom/BeginBoom.gif";
    private static final String doomCardImageAddress = "/Images/resources/graphics/Cards/DoomShroom.png";
    DoomShroom() {
        super(125, 0, new Image(doomShroomAddress), new Image(doomCardImageAddress));
    }

    @Override
    public void updateImageSituation(Pane pane) {

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
        for(Zombie zombie : ZombieFactory.zombies){
            if(Math.abs(this.getRow() - zombie.getRow()) <= 2 && Math.abs(this.getCol() - (int)zombie.getCol()) <= 2){
                zombie.burn();
            }
        }
    }
}
