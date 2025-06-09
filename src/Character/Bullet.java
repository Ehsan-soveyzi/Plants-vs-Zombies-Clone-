package Character;


import Character.KindsOfZombie.Zombie;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

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
        update();
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
    public void update() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100),e ->{
            move(0.1);
        }));
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
