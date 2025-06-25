package Character.KindsOfZombie;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.io.Serializable;

public class ScreenDoorZombie extends Zombie implements Serializable {
    private final static String screenDoorImageAddress = "/Character/screenDoor.png";
    public ScreenDoorZombie(int row) {
        super(10, 1, 1, row,  new Image(screenDoorImageAddress));
    }
    @Override
    public void updateImageSituation(){

    }
    @Override
    public void playEatingAnimation() {

    }
    public void playWalkingAnimation(Pane pane){
        super.playWalkingAnimation(pane, 22, screenDoorImageAddress);
    }
}


