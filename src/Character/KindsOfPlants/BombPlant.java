package Character.KindsOfPlants;

import javafx.animation.PauseTransition;
import javafx.scene.image.Image;
import javafx.util.Duration;

public abstract class BombPlant extends Plant {
    BombPlant(int cost, int hp, Image image) {
        super(cost,hp, image);
    }
    public abstract void burnZombies();

    public void burnAnimation(String imagePath){
        PauseTransition pause = new PauseTransition(Duration.millis(500));
        pause.setOnFinished(e -> {
            getImageView().setLayoutY(getImageView().getLayoutY() - 50);
            getImageView().setImage(new Image(imagePath));

            die();
            burnZombies();
        });
        pause.playFromStart();
    }

}



