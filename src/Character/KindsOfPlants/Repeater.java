package Character.KindsOfPlants;
import Character.Bullet;
import Character.NormalBullet;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class Repeater extends PeaPlant {
    private static final String repeaterImageAddress = "/Images/resources/graphics/Plants/RepeaterPea/RepeaterPea_.png";
    public Repeater(int cost, int hp, int row) {
        super(cost, hp, row, new Image(repeaterImageAddress + "0.png"));
        playAnimation(14, repeaterImageAddress);

    }

    // we should check the same row for checking there is a zombie or not .
    @Override
    public void shoot(Pane pane) {
        Bullet firstBullet = new NormalBullet(getX(), getY(), getRow(), 1, 0.5);

        Bullet secondBullet = new NormalBullet(getX(), getY(), getRow(), 1, 0.5);
    }

    @Override
    public void updateImageSituation() {

    }
}