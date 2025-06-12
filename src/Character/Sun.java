package Character;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.Objects;
import java.util.Random;

public class Sun {
    double x;
    double y;
    boolean clicked = false;
    private ImageView sunImageView;
    private static String sunAddress = "/Images/resources/graphics/Plants/Sun/Sun_0.png";
//    ImageView imageView = new ImageView(new Image("images/sun.png"));
    public Sun(){ // random in map

        sunImageView = new ImageView(sunAddress);
        sunMovement();
        sunImageView.setOnMouseClicked(event -> {
            clicked = true;
            removeSun();
            addSun();
        });


    }
    public Sun(double x, double y) {
        this.x = x;
        this.y = y;
        sunImageView = new ImageView(sunAddress);
        sunImageView.setLayoutX(x);
        sunImageView.setLayoutY(y);

//        // imageview should add as a child for being watchable
//        sunCollector();
    }
    public void sunCollector(){

    }
    public void sunMovement(){
        Random rand = new Random();
        int x = rand.nextInt(1400);
        sunImageView.setX(x);


        Image[] frames = new Image[21];
        for (int i = 0; i < 21; i++) {
            frames[i] = new Image(Objects.requireNonNull(getClass().getResourceAsStream(
                    "/Images/resources/graphics/Plants/Sun/Sun_" + i + ".png"
            )));
        }

        ImageView sunImageView = getImageView();
        final int[] frameIndex = {0};

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), e -> {
            sunImageView.setY(sunImageView.getY() + 10);
            sunImageView.setImage(frames[frameIndex[0]]);
            frameIndex[0] = (frameIndex[0] + 1) % frames.length;
        }));
        timeline.setCycleCount(75);
        timeline.play();
        timeline.setOnFinished(e -> {
           timeline.stop();
           Timeline timeline1 = new Timeline(new KeyFrame(Duration.millis(100), a -> {
               sunImageView.setImage(frames[frameIndex[0]]);
               frameIndex[0] = (frameIndex[0] + 1) % frames.length;
           }));
           timeline1.setCycleCount(80);
           timeline1.play();
           timeline1.setOnFinished(actionEvent ->
                        sunImageView.setImage(null)
                   );
        });



    }

    public void addSun(){
        System.out.println("Adding Sun");
//        map.setSun(map.getSun() + 1);
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
