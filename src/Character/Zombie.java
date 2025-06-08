package Character;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public abstract class Zombie {


    private int hp;
    private double speed;
    private double eatingSpeed;
    private int row;
    private double x, y;


    private boolean isDead;
    private boolean isEating;
    private boolean isSlowed;

//    protected Image image;
    private ImageView imageView;



    public Zombie(int hp, double speed, double eatingSpeed, int row, double x, double y, Image image) {
        this.hp = hp;
        this.speed = speed;
        this.eatingSpeed = eatingSpeed;
        this.row = row;
        this.x = x;
        this.y = y;
        this.isDead = false;
        this.isEating = false;
        this.isSlowed = false;
        imageView = new ImageView(image);
    }

    //updating zombie movement per moment.
    public void update(double deltaTime) {
        if (!isEating && !isDead) {
            x -= speed * deltaTime;
            imageView.setLayoutX(x);
        }
    }


    public void takeDamage(int damage) {
        if (isDead) return;
        hp -= damage;
        if (hp <= 0) die();
    }

    public void die() {
        isDead = true;
        speed = 0;
        playDeathAnimation();
    }

    public void startEating() {
        isEating = true;
        playEatingAnimation();
    }

    //if the target is a plant
    public void bite(Plant target) {
        target.takeDamage();
    }



    public void stopEating() {
        isEating = false;
        playWalkingAnimation();
    }


    protected void setupImage(){} //abstract
    protected void playWalkingAnimation() {}//abstract
    protected void playEatingAnimation(){}//abstract
    protected void playDeathAnimation(){}//abstract



    public double getEatingSpeed() {
        return eatingSpeed;
    }
    public double getSpeed(){
        return speed;
    }

    public int getRow() {
        return row;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public boolean isDead() {
        return isDead;
    }

    public boolean isEating() {
        return isEating;
    }

    public boolean isSlowed() {
        return isSlowed;
    }

    public ImageView getImageView() {
        return imageView;
    }
    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    //this method will call after the ice bullet damage.
    public void setSlowed(boolean slowed) {
        isSlowed = slowed;
        if(isSlowed){
            setSpeed(speed / 2);
        }
        else{
            setSpeed(speed * 2);
        }
    }

    public void setEating(boolean eating) {
        isEating = eating;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setEatingSpeed(double eatingSpeed) {
        this.eatingSpeed = eatingSpeed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
}
