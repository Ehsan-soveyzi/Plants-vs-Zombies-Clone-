package Character;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Sun {
    double x;
    double y;
    boolean clicked = false;
    ImageView imageView = new ImageView(new Image("images/sun.png"));
    Sun(){ // random in map
        sunMovement();
        sunCollector();
    }
    Sun(double x, double y) {
        this.x = x;
        this.y = y;
        imageView.setX(x);
        imageView.setY(y);
        // imageview should add as a child
        sunCollector();
    }
    public void sunCollector(){
        imageView.setOnMouseClicked(event -> {
            if(clicked) {
                removeSun();
            }
            else clicked = true;
        });

        new Thread(()->{
            try {
                Thread.sleep(3000);
                if(clicked){
                    addSun();
                    removeSun();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (!clicked) {
                Platform.runLater(() -> {
                    removeSun();
                });
            }
        }).start();
    }
    private void sunMovement(){

    }

    public void addSun(){
//        map.setSun(map.getSun() + 1);
    }
    public void removeSun(){
//        imageView.setOpacity(0);
//        if (imageView.getParent() != null) {
//            ((Pane) imageView.getParent()).getChildren().remove(imageView);
//        }
    }


}
