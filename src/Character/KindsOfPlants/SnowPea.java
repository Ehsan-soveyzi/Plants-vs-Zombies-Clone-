package Character.KindsOfPlants;
import Character.Bullet;
import Character.SlowBullet;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class SnowPea extends PeaPlant {
    private static final String snowPeaImageAddress = "/images/resources/graphics/Plants/SnowPea/SnowPea_";

    public SnowPea(int cost, int hp, int row) {
        super(cost, hp, row, new Image(snowPeaImageAddress + "0.png"));
        playAnimation(14, snowPeaImageAddress );

    }

    @Override
    public void shoot(Pane pane) {
        Bullet snowBullet = new SlowBullet(getX(), getY(),getRow(),1,0.5);

    }


    @Override
    public void updateImageSituation(Pane pane) {

    }
}