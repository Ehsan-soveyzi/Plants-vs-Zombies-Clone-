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
import java.util.concurrent.atomic.AtomicInteger;


public abstract class Zombie {


    private int hp;
    private double speed;
    private double eatingSpeed;
    private final int row;
    private int col;
    private double x = 1500, y = 0.0;


    private boolean isDead;
    private boolean isEating;
    private boolean isSlowed;
    private boolean isBurn;
    protected Timeline timeline;
    private Pane parentPane;
    private PauseTransition slowTimer;
    private Timeline biteTimeline;

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
        setY(row * 140 + 60);
        getImageView().setLayoutX(x);
        getImageView().setLayoutY(y);
    }

    public void addToPane(Pane pane) {
        // اگه imageView قبلاً در یک والد بوده، اول از اون جداش کن
        if (imageView.getParent() != null && imageView.getParent() != pane) {
            ((Pane) imageView.getParent()).getChildren().remove(imageView);
        }

        // فقط اگر هنوز در همین pane نیست، اضافه‌اش کن
        if (imageView.getParent() == null) {
            pane.getChildren().add(imageView);
        }
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
    public void burn(){
        isBurn = true;
        isDead = true;
        die();
    }

    public void die() {
        isDead = true;
        if(timeline != null)timeline.stop();
        ZombieFactory.zombies.remove(this);
        updateImageSituation();
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

    public void updateImageSituation(){
        if (isSlowed) {
            playWakingSlowerAnimation();
        }
        if (isBurn){
            System.out.println("one zombie burn");
            playDeathAnimation(19, "/Images/resources/graphics/Zombies/NormalZombie/BoomDie/BoomDie_");
            return;
        }
        if (isDead){
            System.out.println("one zombie die");
            playDeathAnimation(10 ,"/Images/resources/graphics/Zombies/NormalZombie/ZombieDie/ZombieDie_");
            return;
        }
        if (isEating) {
            System.out.println("one zombie eating");
            playEatingAnimation();
        }


    }
    public abstract void playWalkingAnimation(Pane pane);
    public void playWalkingAnimation(Pane pane, int number, String path) {
        addToPane(pane);
        Image[] frames = new Image[number];
        for (int i = 0; i < number; i++) {
            frames[i] = new Image(Objects.requireNonNull(getClass().getResourceAsStream(
                    path + i + ".png"
            )));
        }

        ImageView zombieView = getImageView();

        final int[] frameIndex = {0};

        timeline = new Timeline(new KeyFrame(Duration.millis(100), e -> {
            this.update(0.1);
            zombieView.setImage(frames[frameIndex[0]]);
            frameIndex[0] = (frameIndex[0] + 1) % frames.length;
        }));

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
    public abstract void  playEatingAnimation();
    public void playDeathAnimation(int number, String path) {
        Image[] frames = new Image[number];
        for (int i = 0; i < number; i++) {
            frames[i] = new Image(Objects.requireNonNull(getClass().getResourceAsStream(
                    path + i + ".png"
            )));
        }
        ImageView zombieView = getImageView();
        final int[] frameIndex = {0};

        timeline = new Timeline(new KeyFrame(Duration.millis(100), e -> {
            zombieView.setImage(frames[frameIndex[0]]);
            frameIndex[0] = (frameIndex[0] + 1) % frames.length;
        }));
        timeline.setCycleCount(frames.length);
        timeline.setOnFinished(e -> {
            imageView.setImage(null);
            imageView.setOnMouseClicked(mouseEvent -> {
                System.out.println(mouseEvent.getSceneX());
            });
            if(parentPane != null)parentPane.getChildren().remove(imageView);
            else System.out.println("realllllllllly");
        });
        timeline.playFromStart();
    }

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
                imageView.setEffect(null);
            });
            slowTimer.playFromStart();
        }
    }

    public void startBiting(Plant plant, GameMap map) {
        if (biteTimeline != null) return; // اگر در حال گاز زدن هست، برنگرد
        AtomicInteger i = new AtomicInteger(1);
        biteTimeline = new Timeline(new KeyFrame(Duration.millis(eatingSpeed), e -> {
            bite(plant);
            System.out.println("bite " + i.getAndIncrement());
            if(isDead){
                stopBiting();
            }
            if (plant.getHp() <= 0) {
                plant.setDead(true);
                GameMap.plants.remove(plant);
                map.removePlant(plant.getRow(), plant.getCol());
                stopBiting();
            }
        }));
        biteTimeline.setCycleCount(Timeline.INDEFINITE);
        biteTimeline.play();
    }

    public void stopBiting() {
        if (biteTimeline != null) {
            biteTimeline.stop();
            biteTimeline = null;
        }
        setEating(false);
        timeline.stop();
        updateImageSituation();
        if(!isDead)playWalkingAnimation((Pane)this.getImageView().getParent());
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


    public void setEatingSpeed(double eatingSpeed) {
        this.eatingSpeed = eatingSpeed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public int getCol() {
        return (int)((this.getX() - 260)/122);
    }

    public void setCol(int col){
        this.col = col;
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

    public Timeline getTimeline() {
        return timeline;
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
