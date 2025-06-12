package Character.KindsOfZombie;

import Character.KindsOfPlants.Plant;
import Map.GameMap;
import Map.ZombieFactory;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.Objects;


public abstract class Zombie {


    private int hp;
    private double speed;
    private double eatingSpeed;
    private int row;
    private double x = 1500, y = 0.0;


    private boolean isDead;
    private boolean isEating;
    private boolean isSlowed;
    private boolean isBurn;
    protected Timeline timeline;
    private Pane parentPane;
    private PauseTransition slowTimer;

//    protected Image image;
    private ImageView imageView;



    public Zombie(int hp, double speed, double eatingSpeed, int row, Image image) {
        this.hp = hp;
        this.speed = speed;
        this.eatingSpeed = eatingSpeed;
        this.row = row;
        this.isDead = false;
        this.isEating = false;
        this.isSlowed = false;
        imageView = new ImageView(image);
        setY(row * 140 + 80);
        getImageView().setLayoutX(x);
        getImageView().setLayoutY(y);
    }

    public void addToPane(Pane pane) {
        this.parentPane = pane;
        pane.getChildren().add(imageView);
    }


    //updating zombie movement per moment.
    public void update(double deltaTime) {
        if (!isEating && !isDead) {
            x -= speed * deltaTime;
            imageView.setLayoutX(x);
            updateImageSituation();
        }
    }


    public void takeDamage(int damage) {
        if (isDead) return;
        hp -= damage;
        if (hp <= 0){
            die();

        }
    }

    public void die() {
        isDead = true;
        if(timeline != null)timeline.stop();
        updateImageSituation();
//        if(parentPane != null)parentPane.getChildren().remove(imageView);
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
    }

    public void stopWalking() {
        isEating = true;
    } // abstract

    protected void updateImageSituation(){
        if (isBurn) {
            playBurningAnimation();
            return;
        }
        if (isDead){
            System.out.println("one zombie die");
            playDeathAnimation();
            return;
        }
        if (isSlowed) {
            playWakingSlowerAnimation();
        }

        if (isEating) {
            System.out.println("one zombie eating");
            timeline.stop();
            playEatingAnimation();
        }
    }
    public abstract void playWalkingAnimation(Pane pane);//abstract
    protected abstract void  playEatingAnimation();
    public void playDeathAnimation(){
        Image[] frames = new Image[22];
        for (int i = 0; i < 10; i++) {
            frames[i] = new Image(Objects.requireNonNull(getClass().getResourceAsStream(
                    "/Images/resources/graphics/Zombies/NormalZombie/ZombieDie/ZombieDie_" + i + ".png"
            )));
        }

        ImageView zombieView = getImageView();

        final int[] frameIndex = {0};

         timeline = new Timeline(new KeyFrame(Duration.millis(100), e -> {
//            this.update(0.1);
            zombieView.setImage(frames[frameIndex[0]]);
            frameIndex[0] = (frameIndex[0] + 1) % frames.length;
        }));

        timeline.setCycleCount(frames.length);
        timeline.play();
        timeline.setOnFinished(e -> {
            if(parentPane != null)parentPane.getChildren().remove(imageView);
        });
        timeline.playFromStart();
    }
    private void playBurningAnimation(){};

    private void playWakingSlowerAnimation(){
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setHue(-1);
        colorAdjust.setContrast(-0.07);
        colorAdjust.setBrightness(-0.19);
        imageView.setEffect(colorAdjust);
    }



    //this method will call after the ice bullet damage.
    public void setSlowed(boolean slowed) {
        if (slowed) {
            // فقط اگر سرعت فعلی هنوز زیاد بود، کندش کن
            if (!isSlowed) {
                setSpeed(speed / 2.0);
            }

            isSlowed = true;

            if (slowTimer != null) {
                slowTimer.stop();
            }

            //new timer for affect slowing for 5 sec!
            slowTimer = new PauseTransition(Duration.seconds(5));
            slowTimer.setOnFinished(event -> {
                //if after 5 seconds ice shoot did not applied remove the slow.
                isSlowed = false;
                setSpeed(speed * 2.0);
            });
            slowTimer.playFromStart();
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

    public void setHp(int hp) {
        this.hp = hp;
    }
}
