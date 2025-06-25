package Character.KindsOfPlants;

import  Character.Bullet;
import Character.KindsOfZombie.Zombie;
import Map.ZombieFactory;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class PeaPlant extends Plant implements Serializable {

    private boolean checkShot;
    public static ArrayList<Bullet> bulletList = new ArrayList<>();

    PeaPlant(int cost, int hp, Image image,Image cardImage) {
        super(cost, hp, image,cardImage);
        this.checkShot = false;
    }


    public void sameRowZombies() {
        boolean zombieInRow = false;

        for (Zombie z : ZombieFactory.zombies) {
            if (z.getRow() == getRow() && z.getX() - 10 >= getX()) {
                zombieInRow = true;
            }
        }
        setCheckShot(zombieInRow);
    }


    public void sameRowBullet() {
        ArrayList<Bullet> removeBullets = new ArrayList<>();
        for (Bullet b : bulletList) {
            for (Zombie z : ZombieFactory.zombies) {
                if (z.getRow() != b.getRow()) continue;
                if (z.getRow() == getRow() && Math.abs(z.getX() - b.getX()) < 30) {
                    b.onHit(z);
                    b.die();
                    removeBullets.add(b);
                    break;
                }
            }
        }
        bulletList.removeAll(removeBullets);
    }


    public void checkBullet() {
        timeline = new Timeline(new KeyFrame(Duration.millis(100), e -> {
            sameRowZombies();
            sameRowBullet();
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }


    //every time this method called , a bullet object will be created!
    // باید در بازی بررسی بشه که در سطر تا زمانی که زامبی هست صدا زده بشه
    abstract public void shoot(Pane pane);

    public boolean getCheckShot() {
        return checkShot;
    }
    public void setCheckShot(boolean checkShot) {
        this.checkShot = checkShot;
    }
}



