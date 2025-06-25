package Map;

import Character.KindsOfZombie.*;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class ZombieFactory {

    public static ArrayList<Zombie> zombies = new ArrayList<>();

    private double width;
    private double height;
    private Pane pane;

    public ZombieFactory(Pane pane) {
        this.width = 160;
        this.height = 160;
        this.pane =pane;
    }

    public Zombie createRegularZombie(int row,double x){
        Zombie zombie = new Regular(row);
//        zombie.setX(x);
        zombie.getImageView().setFitWidth(width);
        zombie.getImageView().setFitHeight(height);
        zombie.playWalkingAnimation(pane);
        zombies.add(zombie);
        return zombie;
    }
    public Zombie createConeHeadZombie(int row,double x){
        Zombie zombie = new ConeHead(row);
        zombie.setX(x);
        zombie.getImageView().setFitWidth(width);
        zombie.getImageView().setFitHeight(height);
        zombie.playWalkingAnimation(pane);
        zombies.add(zombie);
        return zombie;
    }
    public Zombie createScreenDoorZombie(int row,double x){
        Zombie zombie = new ScreenDoorZombie(row);
        zombie.setX(x);
        zombie.getImageView().setFitWidth(width);
        zombie.getImageView().setFitHeight(height);
        zombie.playWalkingAnimation(pane);
        zombies.add(zombie);
        return zombie;
    }
    public Zombie createIMPZombie(int row,double x){
        Zombie zombie = new IMPZombie(row);
        zombie.setX(x);
        zombie.getImageView().setFitWidth(width);
        zombie.getImageView().setFitHeight(height);
        zombie.playWalkingAnimation(pane);
        zombies.add(zombie);
        return zombie;
    }
    //overLoading method for creating zombies in every coordination

}
