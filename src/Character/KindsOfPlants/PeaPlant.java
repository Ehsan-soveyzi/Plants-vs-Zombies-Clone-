package Character.KindsOfPlants;
import  Character.Bullet;
import Character.KindsOfZombie.Zombie;
import Map.ZombieFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public abstract class PeaPlant extends Plant {
    private boolean checkShot;
    ArrayList<Bullet> bulletQueue = new ArrayList<>();
    PeaPlant(int cost, int hp, int row, Image image) {
        super(cost, hp, row, image);
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
        //both these arraylist used to remove elements!
        ArrayList<Bullet> toRemove1 = new ArrayList<>();
        ArrayList<Zombie> toRemove2 = new ArrayList<>();
        for(Bullet b : bulletQueue) {
            for (Zombie z : ZombieFactory.zombies) {
                if (z.getRow() == getRow() && Math.abs(z.getX() - b.getX()) < 10) {
                    b.die();
                    toRemove1.add(b);
                    b.onHit(z);
//                    System.out.println("hiting bullet ");
                    if (z.isDead())toRemove2.add(z);
                }
            }
        }
        bulletQueue.removeAll(toRemove1);
        ZombieFactory.zombies.removeAll(toRemove2);
    }


    //every time this method called a bullet object will be created!
    // باید در بازی بررسی بشه که در سطر تا زمانی که زامبی هست صدا زده بشه
    abstract public void shoot(Pane pane);
}



