package Character;

import Character.KindsOfZombie.Zombie;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class NormalBullet extends Bullet {
    private static final String normalBulletImageAddress = "/Images/resources/graphics/Bullets/PeaNormal/PeaNormal_0.png";
    public NormalBullet(double x,double y,int row) {
        super(x,y,row,1,20,new Image(normalBulletImageAddress));

    }

    @Override
    public void onHit(Zombie zombie){
        zombie.takeDamage(1);
        die();
    }
    
}
