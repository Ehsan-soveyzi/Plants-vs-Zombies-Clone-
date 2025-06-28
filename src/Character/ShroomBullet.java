package Character;

import Character.KindsOfZombie.Zombie;
import javafx.scene.image.Image;

public class ShroomBullet extends Bullet {
    private static final String shroomBulletImageAddress = "/new_resources/images/Plants/ShroomBullet.gif";
    private static final String shroomBulletHit =  "/new_resources/images/Plants/ShroomBulletHit.gif";
    public ShroomBullet(double x,double y,int row) {
        super(x,y,row,20,new Image(shroomBulletImageAddress));

    }

    @Override
    public void onHit(Zombie zombie){
        zombie.takeDamage(1);
        die();
    }
}