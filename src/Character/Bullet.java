package Character;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Bullet {
    private double speed;
    private double x,y;
    private int damage;
    private int row;
    private boolean slower = false;
    private ImageView imageView;

    private boolean isAlive;
    public Bullet(double x, double y, int row, int damage, double speed, Image image) {
        this.x = x;
        this.y = y;
        this.row = row;
        this.damage = damage;
        this.speed = speed;
        this.imageView = new ImageView(image);
    }
    public void move(double deltaTime) {
        if(!isAlive)return;
        x += speed * deltaTime;
        imageView.setLayoutX(x);
//        int length = ;     // اگر از زمین خارج شد
//        if(x > length) die();

        //the implementation of the damaging to the zombies would be here.
        //checking the distance between each zombie and bullet!

    }

    //after each shot the current bullet will die!
    public void die(){
        isAlive = false;
    }

    public void onHit(Zombie zombie){
        if(slower) zombie.setSlowed(true);
        zombie.takeDamage(damage);
        die();

    }
    // از طریق بورد بازی صف اون ردیف را بگیریم و به زامبی های اون دسترسی پیذا کنیم به چپ ترین زامبی رو به عنوان پارامتر

    public ImageView getImageView() {
        return imageView;
    }
    public int getRow() {
        return row;
    }
    public void setRow(int row) {
        this.row = row;
    }
    public double getY(){
        return y;
    }
    public void setY(double y){
        this.y = y;
    }
}
//other class like peashot and iceshot use this bullets!

class NormalBullet extends Bullet{
    private static final String normalBulletImageAddress = "/Character/normalBullet.png";
    public NormalBullet(double x,double y,int row, int damage, double speed) {
        super(x,y,row,damage,speed,  new Image(normalBulletImageAddress));
    }
}
class SlowBullet extends Bullet{
    private static final String snowBulletImageAddress = "/Character/SnowBullet.png";
    public SlowBullet(double x,double y,int row, int damage, double speed) {
        super(x,y,row,damage,speed,  new Image(snowBulletImageAddress));
    }
}



//enum BulletType {NORMAL, ICE, DOUBLE}
//switch (type) {
//        case ICE:
//slower = true;
//        case NORMAL:
//speed = 1;
//damage = 1;
//        break;
//        case DOUBLE: // 0.5 seconds later make a new bullet Normal
//speed = 1;
//damage = 1;
//        break;
//        }
