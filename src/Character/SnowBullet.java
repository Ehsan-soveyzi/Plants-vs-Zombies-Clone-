package Character;

import Character.KindsOfZombie.Zombie;
import javafx.scene.image.Image;

public class SnowBullet extends Bullet {
    private static final String snowBulletImageAddress = "/Images/resources/graphics/Bullets/PeaIce/PeaIce_0.png";
    private static final String snowBulletHit =  "/new_resources/images/Plants/PeaBulletHit1.gif";
    public SnowBullet(double x,double y,int row) {
        super(x,y,row,20,  new Image(snowBulletImageAddress), new Image(snowBulletHit));
    }

    @Override
    public void onHit(Zombie zombie){
        zombie.takeDamage(1);
        zombie.setSlowed(true);
        die();
    }
}