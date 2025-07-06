package Character.KindsOfPlants;

import javafx.animation.PauseTransition;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import java.io.Serializable;

public abstract class BombPlant extends Plant implements Serializable {

    BombPlant(int cost, int hp, Image image,Image cardImage) {
        super(cost,hp, image,cardImage);
    }

    public abstract void burnZombies();

    public void burnAnimation(String imagePath){
        PauseTransition pause = new PauseTransition(Duration.millis(200));
        pause.setOnFinished(e -> {
            getImageView().setLayoutY(getImageView().getLayoutY() - 50);
            //just for setting the images im correct square
            if(this instanceof DoomShroom){
                getImageView().setLayoutY(getImageView().getLayoutY() - 150);
                getImageView().setLayoutX(getImageView().getLayoutX() - 100);
            }
            getImageView().setImage(new Image(imagePath));
            PauseTransition pause2 = new PauseTransition(Duration.millis(100));
            pause2.setOnFinished(e1 ->{
                burnZombies();
                die();
            });
            pause2.play();
        });
        pause.playFromStart();
    }

}



