package Character.KindsOfPlants;

import Character.KindsOfZombie.Zombie;
import Map.ZombieFactory;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public  class CherryBomb extends BombPlant {
    public static final int cooldown = 1;
    public static boolean isReady = true;
    private static final String cherryBombImageAddress = "/Images/resources/graphics/Plants/CherryBomb/CherryBomb.gif";
    private static final String ExplodeCherryBombImageAddress = "/Images/resources/graphics/Plants/CherryBomb/Boom.gif";
    public CherryBomb() {
        //dont have idea about the hp!
        super(150, 100000, new Image(cherryBombImageAddress));

    }

    @Override
    public void burnZombies(){
        for(Zombie zombie : ZombieFactory.zombies){
            if(Math.abs(this.getRow() - zombie.getRow()) <= 1 && Math.abs(this.getCol() - zombie.getCol()) <= 1){
                zombie.burn();
            }
        }
    }

    @Override
    public void updateImageSituation(Pane pane) {
        burnAnimation(ExplodeCherryBombImageAddress);
        startCooldown();
    }

    public static void startCooldown() {
        isReady = false;

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(cooldown), event -> {
            isReady = true;
        }));
        timeline.setCycleCount(1);
        timeline.play();
    }
}