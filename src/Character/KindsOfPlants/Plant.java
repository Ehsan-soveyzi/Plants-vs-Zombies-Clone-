package Character.KindsOfPlants;

import Map.GameMap;
import Map.MapController;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.Objects;

public abstract class Plant {


    private int cost;
    private int hp;
    private double x, y;
    private boolean isDead;
    private int row;
    private int col;
    protected Timeline timeline;


    private ImageView imageView; // pay attention when the plant dies should set another image

    Plant(int cost, int hp, Image image) {
        this.cost = cost;
        this.hp = hp;
        this.isDead = false;
        imageView = new ImageView(image);
        imageView.setLayoutX(imageView.getX() + 20);
        imageView.setLayoutY(imageView.getY() + 20);
    }
    public void takeDamage() {
        if (isDead) return;
        if (hp <= 0){
            die();
            return;
        }
        hp--;
//        updateImageSituation();
    }


    public void die() {
        isDead = true;
        if(timeline != null) timeline.stop();
    }



    public abstract void updateImageSituation(Pane pane); // abstract

    public int getRow(){return row;};
    public void setRow(int row){this.row = row;};
    public int getCost() {return cost;}
    public void setCost(int cost) {this.cost = cost;}
    public int getHp() {return hp;}
    public void setHp(int hp) {this.hp = hp;}
    public double getX() {return x;}
    public void setX(double x) {this.x = x;}
    public double getY() {return y;}
    public void setY(double y) {this.y = y;}
    public boolean isDead() {
        return isDead;
    }
    public void setCol(int col) {this.col = col;}
    public int getCol() {return col;}

    public ImageView getImageView() {
        return imageView;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }
}

