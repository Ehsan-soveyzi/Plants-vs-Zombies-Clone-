package Character.KindsOfPlants;

import Map.GameMap;
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
    private double cooldown;
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
        System.out.println("this die..");
        if(timeline != null) timeline.stop();
        GameMap.plants.remove(this);
    }
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

        }));

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
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
    public double getCooldown() {return cooldown;}
    public void setCooldown(double cooldown) {this.cooldown = cooldown;}
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

