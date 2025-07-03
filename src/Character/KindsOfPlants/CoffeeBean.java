package Character.KindsOfPlants;

import Map.GameMap;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class CoffeeBean extends Plant{
    public static int cooldown = 7;
    public static boolean isReady = true;
    public static Timeline cooldownTimeline;
    private static final String coffeeBeanImageAddress = "/Images/resources/graphics/Plants/CoffeeBean/CoffeeBean.gif";
    private static final String coffeeBeanCardImageAddress = "/Images/resources/graphics/Cards/CoffeeBean.jpg";
    private static final String coffeeBeanEatImageAddress = "/Images/resources/graphics/Plants/CoffeeBean/CoffeeBeanEat.gif";


    public CoffeeBean() {
        super(75, 0, new Image(coffeeBeanImageAddress), new Image(coffeeBeanCardImageAddress));
    }

    @Override
    public void updateImageSituation(Pane pane) {
        startCooldown();
        Plant plant  = findPlant();
        PauseTransition pause = new PauseTransition(Duration.millis(300));
        pause.setOnFinished(event -> {
            ((Pane)getImageView().getParent()).getChildren().remove(getImageView());
            assert plant != null;
            wakeUp(plant);
        });
        pause.play();

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
    private Plant findPlant() {
        for (Plant p : GameMap.plants){
            if (p.getCol() == getCol() && p.getRow() == getRow()){
                return p;
            }
        }
        return null;
    }
    public void wakeUp(Plant plant) {
        if (!plant.isMorningAwake()){
            plant.setMorningAwake(true);

        }
    }
}
