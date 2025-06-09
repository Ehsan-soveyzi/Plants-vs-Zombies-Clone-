package Character.KindsOfPlants;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Plant {


    private int cost;
    private int hp;
    private double cooldown;
    private double x, y;
    private boolean isDead;
    private int row;


    private ImageView imageView; // pay attention when the plant dies should set another image

    Plant(int cost, int hp, double x, double y, int row, Image image) {
        this.cost = cost;
        this.hp = hp;
        this.isDead = false;
        this.x = x;
        this.y = y;
        this.row = row;
        imageView = new ImageView(image);
        imageView.setLayoutX(x);
        imageView.setLayoutY(y);
    }
    public void takeDamage() {
        if (isDead) return;
        if (hp <= 0){
            die();
            return;
        }
        hp--;
        updateImageSituation();
    }

    public void die() {
        isDead = true;
        clearDiedPlant();
    }

    private void clearDiedPlant(){ // با مختصات گرید
        // remove from array
        // remove from the grid
    }
    public abstract void updateImageSituation(); // abstract

    public int getRow(){return row;};
    public int getCost() {return cost;}
    public void setCost(int cost) {this.cost = cost;}
    public double getHp() {return hp;}
    public void setHp(int hp) {this.hp = hp;}
    public double getX() {return x;}
    public void setX(double x) {this.x = x;}
    public double getY() {return y;}
    public void setY(double y) {this.y = y;}
    public double getCooldown() {return cooldown;}
    public void setCooldown(double cooldown) {this.cooldown = cooldown;}
    public boolean isDead() {
        return isDead;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }
}

