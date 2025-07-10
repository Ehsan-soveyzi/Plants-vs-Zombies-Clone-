package Character;

import ApplyGraphics.MapController;
import GameServer.Server;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Sun implements Serializable {

    private double x;
    private double y;
    private boolean clicked = false;
    private int pauseRemaining = 6;
    private int timelineRemaining;
    private transient ImageView sunImageView;
    private transient Timeline timeline;
    private transient Timeline pause;
    public static ArrayList<Sun> sunList = new ArrayList<>();
    private static final String sunAddress = "/Images/resources/graphics/Plants/Sun/sun.png";

    public Sun() throws IOException { // random in map
        sunImageView = new ImageView(sunAddress);
        sunMovement();
        sunImageView.setOnMouseClicked(event -> {
            clicked = true;
            addSun();
            removeSun();
        });
        sunList.add(this);
    }

    public Sun(double x, double y) {
        this.x = x;
        this.y = y;
        sunImageView = new ImageView(sunAddress);
        sunImageView.setX(x);
        sunImageView.setY(y);
        sunCollector();
        sunImageView.setOnMouseClicked(event -> {
            clicked = true;
            addSun();
            removeSun();
        });
        sunList.add(this);
    }

    public static void addToPane(Pane pane) throws IOException {
        Sun sun = new Sun();
        pane.getChildren().add(sun.getImageView());
    }

    public void sunCollector(){
        pause = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            pauseRemaining--;
            if(pauseRemaining == 0) {
                sunImageView.setImage(null);
                sunList.remove(this);
                pause.stop();
                return;
            }
        }));
        pause.setCycleCount(Timeline.INDEFINITE);
        pause.play();
    }

    private void sunMovement() throws IOException {
        int x = Server.generateRandom(1030) + 370;
        int y = Server.generateRandom(180) + 20;
        timelineRemaining = y;
        this.setX(x);
        sunImageView.setX(x);

        timeline = new Timeline(new KeyFrame(Duration.millis(50), e -> {
            sunImageView.setY(sunImageView.getY() + 4);
            this.setY(sunImageView.getY());
            timelineRemaining--;
        }));
        timeline.setCycleCount(y);
        timeline.play();
        timeline.setOnFinished(e -> {
            sunCollector();
        });
    }
    public void applyTimelineRemaining(){
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(50), e -> {
            sunImageView.setY(sunImageView.getY() + 4);
            this.setY(sunImageView.getY());
        }));
        timeline.setCycleCount(timelineRemaining);
        timeline.play();
    }

    public void addSun(){
        MapController.score += 25;
    }

    public void removeSun(){
        if (sunImageView.getParent() != null) {
            ((Pane) sunImageView.getParent()).getChildren().remove(sunImageView);
            sunList.remove(this);
        }
    }

    public ImageView getImageView(){
        return sunImageView;
    }
    public void setImageView(ImageView imageView){this.sunImageView = imageView;}
    public Timeline getTimeline(){
        return timeline;
    }
    public Timeline getPause(){
        return pause;
    }
    public double getX() {return x;}
    public double getY() {return y;}
    public void setX(double x) {this.x = x;}
    public void setY(double y) {this.y = y;}
    public boolean isClicked() {return clicked;}
    public int getPauseRemaining() {return pauseRemaining;}
    public ImageView getSunImageView() {return sunImageView;}

}
