package Character;


import Character.KindsOfZombie.Zombie;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public abstract class Bullet {
    private double speed;
    private double x,y;
    private int row;
    private ImageView imageView;
    private boolean isAlive;
    private Pane parentPane;

    private Timeline timeline;

    public Bullet(double x, double y, int row, double speed, Image image) {
        this.x = x;
        this.y = y;
        this.row = row;
        this.speed = speed;
        this.imageView = new ImageView(image);
        this.isAlive = true;
        imageView.setLayoutY(y);
        imageView.setLayoutX(x);
        update();
    }



    public void addToPane(Pane pane) {
        this.parentPane = pane;
        pane.getChildren().add(imageView);
        update();
    }

    public void die() {
        isAlive = false;
        if (timeline != null) timeline.stop();
        if (parentPane != null) parentPane.getChildren().remove(imageView);
        // اگر لیستی از گلوله‌ها داری، از اون لیست هم حذف کن
    }

//    public void onHit(Zombie zombie) {
//        zombie.takeDamage(damage);
//        die();
//    }
    public abstract void onHit(Zombie z);

    public void move(double deltaTime) {
        if (!isAlive) return;

        x += speed * deltaTime;
        imageView.setLayoutX(x);

        if (x > 1550) {
            die();
        }
    }

    public void update() {
        timeline = new Timeline(new KeyFrame(Duration.millis(10), e -> {
            move(0.1);
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public ImageView getImageView() {
        return imageView;
    }
    public int getRow() {
        return row;
    }
    public void setRow(int row) {
        this.row = row;
    }
    public double getY(){
        return y;
    }
    public void setX(double x){
        this.x = x;
    }
    public double getX(){
        return x;
    }
    public void setY(double y){
        this.y = y;
    }
    public Bullet getBullet(){
        return this;
    }
    public Timeline getTimeline() {
        return timeline;
    }

}

