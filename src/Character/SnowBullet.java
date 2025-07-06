package Character;

import Character.KindsOfZombie.Zombie;
import javafx.scene.image.Image;

import java.io.Serializable;

public class SnowBullet extends Bullet implements Serializable {
    private static final String snowBulletImageAddress = "/Images/resources/graphics/Bullets/PeaIce/PeaIce_0.png";
    public SnowBullet(double x,double y,int row) {
        super(x,y,row,30,  new Image(snowBulletImageAddress));
    }

    @Override
    public void onHit(Zombie zombie){
        zombie.takeDamage();
        zombie.setSlowed(true);
        die();
    }
}