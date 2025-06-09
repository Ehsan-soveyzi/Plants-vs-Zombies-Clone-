package Character.KindsOfZombie;

import javafx.scene.image.Image;

public class ScreenDoorZombie extends Zombie {
    private final static String screenDoorImageAddress = "/Character/screenDoor.png";
    public ScreenDoorZombie(int hp, double speed, double eatingSpeed, int row, double x, double y) {
        super(hp, speed, eatingSpeed, row, x, y, new Image(screenDoorImageAddress));
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
