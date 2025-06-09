package Character.KindsOfPlants;
import Character.Bullet;
import Character.SlowBullet;
import javafx.scene.image.Image;

public class SnowPea extends PeaPlant {
    private static final String snowPeaImageAddress = "/Character/snowPea.png";
    public SnowPea(int cost, int hp, int row) {
        super(cost, hp, row, new Image(snowPeaImageAddress));

    }

    @Override
    public void shoot() {
        Bullet snowBullet = new SlowBullet(getX(), getY(),getRow(),1,0.5);

    }


    @Override
    public void updateImageSituation() {

    }
}