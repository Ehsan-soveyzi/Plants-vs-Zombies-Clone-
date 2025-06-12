package Character.KindsOfPlants;
import Character.Bullet;
import Character.NormalBullet;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class PeaShooter extends PeaPlant {

    private static final String peaShooterImageAddress = "/Images/resources/graphics/Plants/Peashooter/Peashooter_";

    public PeaShooter(int cost, int hp, double x, double y, int row) {
        super(cost, hp,row, new Image(peaShooterImageAddress + "0.png"));
        playAnimation(12, peaShooterImageAddress);

    }

    @Override
    public void shoot(Pane pane) {
        Bullet normalBullet = new NormalBullet(1000,100,getRow(), 1, 0.5);
        pane.getChildren().add(normalBullet.getImageView());

    }

    @Override
    public void updateImageSituation(Pane pane) {
    }
}