package Map;

import Character.KindsOfZombie.*;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class ZombieFactory {

    public static ArrayList<Zombie> zombies = new ArrayList<>();

    private double width;
    private double height;
    private Pane pane;

    public ZombieFactory(double width, double height, Pane pane) {
        this.width = width;
        this.height = height;
        this.pane = pane;
    }

    public void createRegularZombie(int row){
        Zombie zombie = new Regular(row);
        zombie.getImageView().setFitWidth(width);
        zombie.getImageView().setFitHeight(height);
        zombie.playWalkingAnimation(pane);
        zombies.add(zombie);
    }
    public void createConeHeadZombie(int row){
        Zombie zombie = new ConeHead(row);
        zombie.getImageView().setFitWidth(width);
        zombie.getImageView().setFitHeight(height);
        zombie.playWalkingAnimation(pane);
        zombies.add(zombie);
    }
    public void createScreenDoorZombie(int row){
        Zombie zombie = new ScreenDoorZombie(row);
        zombie.getImageView().setFitWidth(width);
        zombie.getImageView().setFitHeight(height);
        zombie.playWalkingAnimation(pane);
        zombies.add(zombie);
    }
    public void createIMPZombie(int row){
        Zombie zombie = new IMPZombie(row);
        zombie.getImageView().setFitWidth(width);
        zombie.getImageView().setFitHeight(height);
        zombie.playWalkingAnimation(pane);
        zombies.add(zombie);
    }
}
