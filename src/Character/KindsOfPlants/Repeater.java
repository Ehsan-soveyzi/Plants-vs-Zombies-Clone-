package Character.KindsOfPlants;
import Character.Bullet;
import Character.NormalBullet;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class Repeater extends PeaPlant {
    private static final String repeaterImageAddress = "/Character/repeater.png";
    public Repeater() {
        super(200, 5, new Image(repeaterImageAddress));
    }

    // we should check the same row for checking there is a zombie or not .
    @Override
    public void shoot(Pane pane) {
        Bullet firstBullet = new NormalBullet(getX(), getY(), getRow());

        Bullet secondBullet = new NormalBullet(getX(), getY(), getRow());
    }

    @Override
    public void updateImageSituation(Pane pane) {

    }
}