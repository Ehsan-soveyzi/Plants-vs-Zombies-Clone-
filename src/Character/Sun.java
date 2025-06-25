package Character;

import ApplyGraphics.MapController;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Sun implements Serializable {
    double x;
    double y;
    boolean clicked = false;
    transient ImageView sunImageView;
    private transient Timeline timeline;
    private transient PauseTransition pause;
    public static ArrayList<Sun> sunList = new ArrayList<>();
    private static final String sunAddress = "/Images/resources/graphics/Plants/Sun/sun.png";

    public Sun(){ // random in map
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
            removeSun();
            addSun();
        });
        sunList.add(this);
    }

    public static void addToPane(Pane pane){
        Sun sun = new Sun();
        pane.getChildren().add(sun.getImageView());
    }

    public void sunCollector(){
        pause = new PauseTransition(Duration.seconds(6));
        pause.setOnFinished(event -> {
            sunImageView.setImage(null);
        });
        pause.play();
    }

    private void sunMovement(){
        Random rand = new Random();
        int x = rand.nextInt(1030) + 370;
        int y = rand.nextInt(180) + 20;
        sunImageView.setX(x);

        timeline = new Timeline(new KeyFrame(Duration.millis(50), e -> {
            sunImageView.setY(sunImageView.getY() + 4);
        }));
        timeline.setCycleCount(y);
        timeline.play();
        timeline.setOnFinished(e -> {
            sunCollector();
        });
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
    public void setImageView(ImageView imageView){
        this.sunImageView = imageView;
    }
    public Timeline getTimeline(){
        return timeline;
    }
    public PauseTransition getPause(){
        return pause;
    }

}
