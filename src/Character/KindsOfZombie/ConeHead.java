package Character.KindsOfZombie;

import javafx.scene.image.Image;

public class ConeHead extends Zombie {
    private final static String coneHeadImageAddress = "/Character/coneHead.png";
    public ConeHead(int row) {
        super(7, 1, 1, row, new Image(coneHeadImageAddress));
    }

    @Override
    public void playWalkingAnimation() {

    }

    @Override
    protected void playEatingAnimation() {

    }
}
