package Character.KindsOfZombie;

import javafx.scene.image.Image;

public class ScreenDoorZombie extends Zombie {
    private final static String screenDoorImageAddress = "/Character/screenDoor.png";
    public ScreenDoorZombie(int row) {
        super(10, 1, 1, row,  new Image(screenDoorImageAddress));
    }
    @Override
    public void updateImageSituation(){

    }

    @Override
    public void playWalkingAnimation() {

    }

    @Override
    protected void playEatingAnimation() {

    }
}
