package Character.KindsOfZombie;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class ConeHead extends Zombie {
    private final static String coneHeadImageAddress = "/Character/coneHead.png";
    public ConeHead(int row) {
        super(7, 1, 1, row, new Image(coneHeadImageAddress));
    }

    @Override
    public void playWalkingAnimation(Pane pane) {

    }

    @Override
    public void playEatingAnimation() {

    }
}
