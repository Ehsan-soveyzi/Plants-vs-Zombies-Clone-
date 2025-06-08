package Character;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.Objects;


public abstract class Zombie {


    private int hp;
    private double speed;
    private double eatingSpeed;
    private int row;
    private double x, y;


    private boolean isDead;
    private boolean isEating;
    private boolean isSlowed;


    private ImageView imageView = new ImageView();



    public Zombie(int hp, double speed, double eatingSpeed, int row, double x, double y) {
        this.hp = hp;
        this.speed = speed;
        this.eatingSpeed = eatingSpeed;
        this.row = row;
        this.x = x;
        this.y = y;
        this.isDead = false;
        this.isEating = false;
        this.isSlowed = false;
        setupImage();
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


    public abstract void setupImage();
    public abstract void playWalkingAnimation();
    public abstract void playEatingAnimation();
    public abstract void playDeathAnimation();



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
class NormalZombie extends Zombie {

    public NormalZombie(int row, double x, double y) {
        super(5,10, 0.5, row, x, y);
    }

    @Override
    public void setupImage() {
        Image firstFrame = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/resources/graphics/Zombies/NormalZombie/Zombie/Zombie_0.png")));
        ImageView iv = getImageView();
        iv.setImage(firstFrame);
        iv.setLayoutX(getX());
        iv.setLayoutY(getY());
//        iv.setFitWidth(120);
//        iv.setFitHeight(110);
    }

    @Override
    public void playWalkingAnimation() {
        Image[] frames = new Image[22];
        for (int i = 0; i < 22; i++) {
            frames[i] = new Image(Objects.requireNonNull(getClass().getResourceAsStream(
                    "/Images/resources/graphics/Zombies/NormalZombie/Zombie/Zombie_" + i + ".png"
            )));
        }

        ImageView zombieView = getImageView();

        final int[] frameIndex = {0};

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), e -> {
            this.update(0.1);
            zombieView.setImage(frames[frameIndex[0]]);
            frameIndex[0] = (frameIndex[0] + 1) % frames.length;
        }));

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    @Override
    public void playEatingAnimation() {

    }

    @Override
    public void playDeathAnimation() {

    }
}
