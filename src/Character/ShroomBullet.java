package Character;

import Character.KindsOfZombie.Zombie;
import javafx.scene.image.Image;

public class ShroomBullet extends Bullet {
    private static final String normalBulletImageAddress = "/new_resources/images/Plants/ShroomBullet.gif";
    public ShroomBullet(double x,double y,int row) {
        super(x,y,row,20,new Image(normalBulletImageAddress));

    }

    @Override
    public void onHit(Zombie zombie){
        zombie.takeDamage(1);
        die();
    }
}
