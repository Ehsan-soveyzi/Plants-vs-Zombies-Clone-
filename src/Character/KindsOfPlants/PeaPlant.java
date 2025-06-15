package Character.KindsOfPlants;
import  Character.Bullet;
import Character.KindsOfZombie.Zombie;
import Map.ZombieFactory;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Objects;

public abstract class PeaPlant extends Plant {
    private boolean checkShot;
    ArrayList<Bullet> bulletQueue = new ArrayList<>();
    PeaPlant(int cost, int hp, Image image) {
        super(cost, hp, image);
        this.checkShot = false;
    }

    public boolean getCheckShot() {
        return checkShot;
    }
    public void setCheckShot(boolean checkShot) {
        this.checkShot = checkShot;
    }

    public void sameRowZombies() {
        boolean zombieInRow = false;

        for (Zombie z : ZombieFactory.zombies) {
            if (z.getRow() == getRow() && z.getX() + 10 >= getX()) {
                zombieInRow = true;
            }
        }

        setCheckShot(zombieInRow);
    }


    public void sameRowBullet() {
        for (Bullet b : bulletQueue) {
            for (Zombie z : ZombieFactory.zombies) {
                if (z.getRow() != b.getRow()) continue;
                if (z.getRow() == getRow() && Math.abs(z.getX() - b.getX()) < 30) {
                    b.onHit(z);
                    b.die();
                    if (z.isDead()) ZombieFactory.zombies.remove(z);
                    bulletQueue.remove(b);
                    break;
                }
            }
        }
    }

    @Override
    public void playAnimation(int number, String address) {
        Image[] frames = new Image[number];
        for (int i = 0; i < number; i++) {
            frames[i] = new Image(Objects.requireNonNull(getClass().getResourceAsStream(
                    address + i + ".png"
            )));
        }
        ImageView imageView = getImageView();

        final int[] frameIndex = {0};

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), e -> {
            imageView.setImage(frames[frameIndex[0]]);
            frameIndex[0] = (frameIndex[0] + 1) % frames.length;
            sameRowBullet();
            sameRowZombies();
        }));

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }


    //every time this method called a bullet object will be created!
    // باید در بازی بررسی بشه که در سطر تا زمانی که زامبی هست صدا زده بشه
    abstract public void shoot(Pane pane);
}



