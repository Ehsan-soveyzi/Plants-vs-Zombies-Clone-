package Character;

import ApplyGraphics.MapController;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.Random;

public class Sun {
    double x;
    double y;
    boolean clicked = false;
    ImageView sunImageView;
    private static final String sunAddress = "/Images/resources/graphics/Plants/Sun/sun.png";

    public Sun(){ // random in map
        sunImageView = new ImageView(sunAddress);
        sunMovement();
        sunImageView.setOnMouseClicked(event -> {
            clicked = true;
            addSun();
            removeSun();
        });
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
    }

    public static void addToPane(Pane pane){
        Sun sun = new Sun();
        pane.getChildren().add(sun.getImageView());
    }

    public void sunCollector(){
        PauseTransition pause = new PauseTransition(Duration.seconds(6));
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

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(50), e -> {
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
        }
    }

    public ImageView getImageView(){
        return sunImageView;
    }

    public void setImageView(ImageView imageView){
        this.sunImageView = imageView;
    }


}
