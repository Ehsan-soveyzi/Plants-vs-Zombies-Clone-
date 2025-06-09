package Character;

import javafx.scene.image.Image;

public class SlowBullet extends Bullet{
    private static final String snowBulletImageAddress = "/Character/SnowBullet.png";
    public SlowBullet(double x,double y,int row, int damage, double speed) {
        super(x,y,row,damage,speed,  new Image(snowBulletImageAddress));
    }
}