package Character.KindsOfZombie;

import javafx.scene.image.Image;

public class ConeHead extends Zombie {
    private final static String coneHeadImageAddress = "/Character/coneHead.png";
    public ConeHead(int hp, double speed, double eatingSpeed, int row, double x, double y) {
        super(hp, speed, eatingSpeed, row, x, y, new Image(coneHeadImageAddress));
    }

    @Override
    public void playWalkingAnimation() {

    }

    @Override
    protected void playEatingAnimation() {

    }
}
