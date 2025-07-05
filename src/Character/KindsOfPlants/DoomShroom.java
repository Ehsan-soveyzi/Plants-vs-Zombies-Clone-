package Character.KindsOfPlants;

import Character.KindsOfZombie.Zombie;
import Map.ZombieFactory;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import java.io.Serializable;
import java.util.ArrayList;

public class DoomShroom extends BombPlant implements Serializable {

    public static int cooldown = 7;
    public static boolean isReady = true;
    private static final String doomShroomAddress = "/Images/resources/graphics/Plants/DoomShroom/BeginBoom.gif";
    private static final String burnDoomShroomImageAddress = "/Images/resources/graphics/Plants/DoomShroom/Boom.gif";
    private static final String doomCardImageAddress = "/Images/resources/graphics/Cards/doomshroom.jpg";
    private static final String squareBombAddress = "/Images/resources/graphics/Plants/DoomShroom/square bomb.png";
    private static final String doomShroomSleepAddress = "/Images/resources/graphics/Plants/DoomShroom/Sleep.gif";
    public static Timeline cooldownTimeline;
    public static ArrayList<Plant> explodeArea = new ArrayList<>();

    public DoomShroom() {
        super(125, 5, new Image(doomShroomAddress), new Image(doomCardImageAddress));
        setShroom(true);
    }

    @Override
    public void burnZombies() {
        ImageView boom = new ImageView(new Image(squareBombAddress));
        boom.setLayoutY(boom.getLayoutY() + 25);
        ((Pane)getImageView().getParent()).getChildren().add(boom);
        ArrayList<Zombie> zombies = new ArrayList<>();
        for (Zombie zombie : ZombieFactory.zombies){
            if(Math.abs(this.getRow() - zombie.getRow()) <= 1 && Math.abs(this.getCol() - (int)zombie.getCol()) <= 1){
                zombies.add(zombie);
            }
        }
        for(Zombie zombie : zombies)zombie.burn();
    }

    public static void startCooldown() {
        isReady = false;

        cooldownTimeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            cooldown--;
            if(cooldown == 0) {
                cooldown = 7;
                isReady = true;
                cooldownTimeline.stop();
            }
        }));
        cooldownTimeline.setCycleCount(7);
        cooldownTimeline.play();
    }

    @Override
    public void updateImageSituation(Pane pane) {
        startCooldown();
        if(!isDay()){
            setHp(1000);
            burnAnimation(burnDoomShroomImageAddress);
            explodeArea.add(this);
        }else{
            getImageView().setImage(new Image(doomShroomSleepAddress));
        }
    }

}
