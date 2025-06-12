package Character;

import Character.KindsOfZombie.Zombie;
import javafx.scene.image.Image;

public class SnowBullet extends Bullet {
    private static final String snowBulletImageAddress = "/Images/resources/graphics/Bullets/PeaIce/PeaIce_0.png";
    public SnowBullet(double x,double y,int row) {
        super(x,y,row,1,20,  new Image(snowBulletImageAddress));
    }

    @Override
    public void onHit(Zombie zombie){
        zombie.takeDamage(1);
        zombie.setSlowed(true);
        die();
    }
}