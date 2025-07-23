package Character.KindsOfPlants;

import ApplyGraphics.ModeController;
import Map.GameMap;
import javafx.animation.Timeline;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.Serializable;

public abstract class Plant implements Serializable {
    private int cost;
    private int hp;
    private double x, y;
    private boolean isDead;
    private static boolean isMorningAwake;
    private int row;
    private int col;
    private boolean isDay;
    protected transient Timeline timeline;
    private transient ImageView cardView;
    private transient ImageView imageView;
    private boolean isShroom;



    Plant(int cost, int hp, Image image, Image cardImage) {
        this.cost = cost;
        this.hp = hp;
        this.isDead = false;
        imageView = new ImageView(image);
        cardView = new ImageView(cardImage);
        imageView.setLayoutX(imageView.getX() + 20);
        imageView.setLayoutY(imageView.getY() + 20);
        isDay = ModeController.Mode.DAY == ModeController.getSelectedMode();
    }

    public void takeDamage() {
        if (isDead) return;
        hp--;
        if (hp <= 0){
            die();
        }
    }

    public void die() {
        isDead = true;
        GameMap.getInstance().removePlant(row,col);
    }

    public abstract void updateImageSituation(Pane pane); // abstract

    public int getRow(){return row;};
    public void setRow(int row){this.row = row;};
    public int getCost() {return cost;}
    public int getHp() {return hp;}
    public void setHp(int hp) {this.hp = hp;}
    public double getX() {return x;}
    public void setX(double x) {this.x = x;}
    public double getY() {return y;}
    public void setY(double y) {this.y = y;}
    public boolean isDead() {return isDead;}
    public void setCol(int col) {this.col = col;}
    public int getCol() {return col;}
    public Timeline getTimeline() {return timeline;}
    public ImageView getCardView() {return cardView;}
    public ImageView getImageView() {return imageView;}
    public void setDead(boolean dead) {isDead = dead;}
    public void setImageView(ImageView imageView) {this.imageView = imageView;}
    public boolean isDay() {return isDay;}
    public void setDay(boolean day) {isDay = day;}
    public boolean isShroom() {return isShroom;}
    public void setShroom(boolean shroom) {isShroom = shroom;}
}

