package Character;

import javafx.scene.image.Image;

public class PeaShooter extends PeaPlant {

    private static final String peaShooterImageAddress = "/Character/peaShooter.png";

    public PeaShooter(int cost, int hp, double x, double y, int row) {
        super(cost, hp, x, y, row, new Image(peaShooterImageAddress));

    }

    @Override
    public void shoot() {
        Bullet normalBullet = new NormalBullet(getX(),getY(),getRow(), 1, 0.5);

    }



}