package Character.KindsOfPlants;
import Character.Bullet;
import Character.NormalBullet;
import javafx.scene.image.Image;

public class Repeater extends PeaPlant {
    private static final String repeaterImageAddress = "/Character/repeater.png";
    public Repeater(int cost, int hp, double x, double y, int row) {
        super(cost, hp, x, y, row, new Image(repeaterImageAddress));

    }

    // we should check the same row for checking there is a zombie or not .
    @Override
    public void shoot() {
        Bullet firstBullet = new NormalBullet(getX(), getY(), getRow(), 1, 0.5);

        Bullet secondBullet = new NormalBullet(getX(), getY(), getRow(), 1, 0.5);
    }

    @Override
    public void updateImageSituation() {

    }
}