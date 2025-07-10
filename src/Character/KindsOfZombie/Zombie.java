package Character.KindsOfZombie;

import ApplyGraphics.GameMain;
import ApplyGraphics.MapController;
import ApplyGraphics.PauseGameController;
import Character.KindsOfPlants.HypnoShroom;
import Character.KindsOfPlants.IceShroom;
import Character.KindsOfPlants.Plant;
import GameServer.Server;
import Map.GameMap;
import Map.ZombieFactory;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;


public abstract class Zombie implements Serializable {


    private int hp;
    private double speed;
    private double eatingSpeed;
    private final int row;
    private double col;
    private double x = 1500 , y = 0.0;


    private boolean isDead;
    private boolean isEating;
    private boolean isSlowed;
    private boolean isBurn;
    private boolean isFreezed;
    private boolean isHypno;
    protected transient Timeline timeline;
    private transient Pane parentPane;
    private transient PauseTransition slowTimer;
    private transient Timeline biteTimeline;
    private transient ImageView imageView;
    private transient PauseTransition freezeTimer;

    public static ArrayList<Zombie> hypnoZombie = new ArrayList<>();
    public static int NumberOfTotalZombies = 0;


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
        imageView.setMouseTransparent(true);
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
    public void update(double deltaTime) throws IOException {
        if (!isEating && !isDead) {
            x += speed * deltaTime;
            imageView.setLayoutX(x);
            updateImageSituation();
            setCol(getCol());
            if(col <= 0 || GameMain.loser) {
                Server.sendEndMessage(-1);
                PauseGameController.isWin = -1;
                MapController.pause();
            }
        }
    }

    public void takeDamage() {
        if (isDead) return;
        hp--;
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
        System.out.println(NumberOfTotalZombies++);
        isDead = true;
        if(timeline != null)timeline.stop();
        ZombieFactory.zombies.remove(this);
        hypnoZombie.remove(this);
        updateImageSituation();
    }

    public void startEating() {
        isEating = true;
        playEatingAnimation();
    }

    //if the target is a plant
    public void bite(Plant target) {
        target.takeDamage();
        if(target instanceof HypnoShroom && !target.isDay()){
            isHypno = true;
            setRedEffect();
        }
    }
    //overloading method
    public void bite(Zombie zombie) {
        if (zombie == null || zombie.isDead()) return;
        zombie.takeDamage();
    }

    public void stopEating() {
        isEating = false;
    }

    public void stopWalking() {
        isEating = true;
    }

    public void updateImageSituation(){
        if (isSlowed) {
            setSlowedEffect();
        }
        if (isBurn){
            playDeathAnimation(19, "/Images/resources/graphics/Zombies/NormalZombie/BoomDie/BoomDie_");
            return;
        }
        if (isDead){
            if (this instanceof IMPZombie){
                playDeathAnimation(21, "/Images/resources/graphics/Zombies/Imp/ZombieDie/");
                return;
            }
            playDeathAnimation(10 ,"/Images/resources/graphics/Zombies/NormalZombie/ZombieDie/ZombieDie_");
            return;
        }
        if (isFreezed){
            setFreezedEffect();
            freezeZombie();
            return;
        }
        if (isEating) {
            playEatingAnimation();
        }
        if(isHypno && speed < 0){
            hypnoZombie.add(this);
            ZombieFactory.zombies.remove(this);
            speed *= -1;
            imageView.setScaleX(-1);
        }

    }

    public abstract void playWalkingAnimation(Pane pane);

    public void playEatingAnimation(int number, String path) {
        Image[] frames = new Image[number];
        for(int i = 0; i < number; i++){
            frames[i] = new Image(Objects.requireNonNull(getClass().getResourceAsStream(
                    path + i + ".png"
            )));
        }
        ImageView zombieView = getImageView();
        final int[] frameIndex = {0};
        timeline = new Timeline(new KeyFrame(Duration.millis(100), e -> {
            zombieView.setImage(frames[frameIndex[0]]);
            frameIndex[0] = (frameIndex[0] + 1) % frames.length;
            if(isDead()) {
                timeline.stop();
                setDead(true);
                die();
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.playFromStart();
    }

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
            try {
                this.update(0.1);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            zombieView.setImage(frames[frameIndex[0]]);
            frameIndex[0] = (frameIndex[0] + 1) % frames.length;
        }));

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public abstract void playEatingAnimation();

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
            if(parentPane != null)parentPane.getChildren().remove(imageView);
        });
        timeline.playFromStart();
    }

    private void setSlowedEffect(){
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setHue(-1);
        colorAdjust.setContrast(-0.07);
        colorAdjust.setBrightness(-0.19);
        imageView.setEffect(colorAdjust);
    }

    public void setRedEffect() {
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setHue(-0.5); // حالت طبیعی، یا یک مقدار نزدیک به آن
        colorAdjust.setSaturation(1); // اشباع بالا برای قوی‌تر کردن رنگ
        colorAdjust.setContrast(-0.07);

        colorAdjust.setBrightness(-0.19);
        imageView.setEffect(colorAdjust);
    }
    private void setFreezedEffect() {
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setBrightness(0.33);
        colorAdjust.setSaturation(-0.02);
        colorAdjust.setContrast(0.09);
        colorAdjust.setHue(1.0);
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(11.76);
        dropShadow.setWidth(23.34);
        dropShadow.setHeight(25.71);
        dropShadow.setInput(colorAdjust);
        imageView.setEffect(dropShadow);
    }


    //this method will call after the ice bullet damage.
    public void setSlowed(boolean slowed) {
        // فقط اگر سرعت فعلی هنوز زیاد بود، کندش کن
        if (slowed) {
            if (!isSlowed) {
                setSpeed(speed / 2.0);
                setEatingSpeed(eatingSpeed * 2.0);
            }

            isSlowed = true;
//            setSlowedEffect();
            if (slowTimer != null) slowTimer.stop();


            //new timer for affect slowing for 5 sec!
            slowTimer = new PauseTransition(Duration.seconds(5));
            slowTimer.setOnFinished(event -> {
                //if after 5 seconds ice shoot did not applied remove the slow.
                isSlowed = false;
                setSpeed(speed * 2.0);
                setEatingSpeed(eatingSpeed / 2.0);
                imageView.setEffect(null);
            });
            slowTimer.playFromStart();
        }
    }

    public void startBiting(Plant plant) {
        if (biteTimeline != null) return; // اگر در حال گاز زدن هست، برنگرد
        biteTimeline = new Timeline(new KeyFrame(Duration.millis(eatingSpeed), e -> {
            bite(plant);
            if(isDead){
                stopBiting();
            }
            if (plant.getHp() <= 0 || plant.isDead()) {
                plant.die();
                stopBiting();
            }
        }));
        biteTimeline.setCycleCount(Timeline.INDEFINITE);
        biteTimeline.play();
    }
    //overloading method
    public void startBiting(Zombie zombie) {
        if (biteTimeline != null || zombie == null || zombie.isDead()) return;
        biteTimeline = new Timeline(new KeyFrame(Duration.millis(eatingSpeed), e -> {
            bite(zombie);
            if(isDead){
                stopBiting();
            }
            if (zombie.getHp() <= 0 || zombie.isDead()) {
                zombie.die();
                stopBiting();
            }
        }));
        biteTimeline.setCycleCount(Timeline.INDEFINITE);
        biteTimeline.play();
    }

    public void freezeZombie() {
        getTimeline().pause();
        if(getBiteTimeline() != null)getBiteTimeline().pause();
        freezeTimer = new PauseTransition(Duration.seconds(5));
        freezeTimer.setOnFinished(event -> {
            getImageView().setEffect(null);
            setFreezed(false);
            if (getTimeline() != null && !isDead()) {
                getTimeline().play();
                if (getBiteTimeline() != null)getBiteTimeline().play();
            }
        });
        freezeTimer.play();
//        zombie.setFreezeTimer(freeze);

    }

    public void stopBiting() {
        if (biteTimeline != null) {
            biteTimeline.stop();
            biteTimeline = null;
            System.out.println("stopBiting");
        }
        setEating(false);
        timeline.stop();
        updateImageSituation();
        if(!isDead)playWalkingAnimation((Pane)this.getImageView().getParent());
    }


    public void setEating(boolean eating) {isEating = eating;}
    public void setDead(boolean dead) {isDead = dead;}
    public void setY(double y) {this.y = y;}
    public void setX(double x) {this.x = x;}
    public void setEatingSpeed(double eatingSpeed) {this.eatingSpeed = eatingSpeed;}
    public void setSpeed(double speed) {this.speed = speed;}
    public double getCol() {return (this.getX() - 260)/122;}
    public void setCol(double col){this.col = col;}
    public double getEatingSpeed() {return eatingSpeed;}
    public double getSpeed(){return speed;}
    public Timeline getBiteTimeline(){return biteTimeline;}
    public PauseTransition getSlowTimer(){return slowTimer;}
    public int getRow() {return row;}
    public double getX() {return x;}
    public double getY() {return y;}
    public boolean isDead() {return isDead;}
    public boolean isEating() {return isEating;}
    public boolean isSlowed() {return isSlowed;}
    public Timeline getTimeline() {return timeline;}
    public ImageView getImageView() {return imageView;}
    public void setImageView(ImageView imageView) {this.imageView = imageView;}
    public void setHp(int hp) {this.hp = hp;}
    public int getHp(){return hp;}
    public boolean isFreezed() {return isFreezed;}
    public void setFreezed(boolean freezed) {isFreezed = freezed;}
    public PauseTransition getFreezeTimer() {return freezeTimer;}
    public boolean isHypno() {return isHypno;}
    public void setHypno(boolean hypno) {isHypno = hypno;}
}
