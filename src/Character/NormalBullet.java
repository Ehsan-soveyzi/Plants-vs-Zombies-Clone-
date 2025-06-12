package Character;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class NormalBullet extends Bullet {
    private static final String normalBulletImageAddress = "/Images/resources/graphics/Bullets/PeaNormal/PeaNormal_0.png";
    public NormalBullet(double x,double y,int row, int damage, double speed) {
        super(x,y,row,damage,speed,  new Image(normalBulletImageAddress));
//        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000), e ->{
//            System.out.println(this.getX());
//        }));
//        timeline.setCycleCount(Timeline.INDEFINITE);
//        timeline.play();

    }
    
}
