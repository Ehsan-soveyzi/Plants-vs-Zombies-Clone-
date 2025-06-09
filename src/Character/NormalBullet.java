package Character;

import javafx.scene.image.Image;

public class NormalBullet extends Bullet {
    private static final String normalBulletImageAddress = "/Character/normalBullet.png";
    public NormalBullet(double x,double y,int row, int damage, double speed) {
        super(x,y,row,damage,speed,  new Image(normalBulletImageAddress));
    }
}
