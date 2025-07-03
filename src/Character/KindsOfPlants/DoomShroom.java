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

    public static int cooldown = 1;
    public static boolean isReady = true;
    private static final String doomShroomAddress = "/Images/resources/graphics/Plants/DoomShroom/BeginBoom.gif";
    private static final String burnDoomShroomImageAddress = "/Images/resources/graphics/Plants/DoomShroom/Boom.gif";
    private static final String doomCardImageAddress = "/Images/resources/graphics/Cards/doomshroom.jpg";
    private static final String squareBombAddress = "/Images/resources/graphics/Plants/DoomShroom/square bomb.png";
    private static final String doomShroomSleepAddress = "/Images/resources/graphics/Plants/DoomShroom/Sleep.gif";
    public static Timeline cooldownTimeline;
    private boolean morningAwake = false;
    private boolean isMorning;

    public DoomShroom() {
        this(true);
    }
    public DoomShroom(boolean isMorning){
        this(!isMorning ? doomShroomAddress : doomShroomSleepAddress, !isMorning ? 0 : 5);
        this.isMorning = isMorning;
    }
    public DoomShroom(String imageAddress, int hp) {
        super(125, hp, new Image(imageAddress), new Image(doomCardImageAddress));
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
        if (!isMorning || morningAwake) {
            nightActions(pane);
        } else {
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(500), e -> {
                if (morningAwake) {
                    nightActions(pane);
                    ((Timeline)e.getSource()).stop();
                }
            }));
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();
        }
    }
    private void nightActions(Pane pane) {
        burnAnimation(burnDoomShroomImageAddress);
        startCooldown();
    }
    public boolean isMorningAwake() {
        return morningAwake;
    }
    public void setMorningAwake(boolean morningAwake) {
        this.morningAwake = morningAwake;
    }
}
