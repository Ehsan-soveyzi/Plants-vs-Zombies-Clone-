package Character.KindsOfPlants;
import Character.Bullet;
import Character.SlowBullet;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class SnowPea extends PeaPlant {
    private static final String snowPeaImageAddress = "/Character/snowPea.png";
    public SnowPea(int cost, int hp) {
        super(cost, hp, new Image(snowPeaImageAddress));

    }

    @Override
    public void shoot(Pane pane) {
        Bullet snowBullet = new SlowBullet(getX(), getY(),getRow(),1,0.5);

    }


    @Override
    public void updateImageSituation(Pane pane) {

    }
}