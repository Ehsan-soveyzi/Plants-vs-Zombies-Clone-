package Character.KindsOfPlants;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class CoffeeBean extends Plant{
    public static int cooldown = 7;
    public static boolean isReady = true;
    public static Timeline cooldownTimeline;
    private static final String coffeeBeanImageAddress = "/new_resources/images/Plants/CoffeeBean/CoffeeBean.gif";
    private static final String coffeeBeanCardImageAddress = "/new_resources/images/Card/Plants/CoffeeBean.png";
    private static final String coffeeBeanEatImageAddress = "/new_resources/images/Plants/CoffeeBean/CoffeeBeanEat.gif";


    CoffeeBean() {
        super(75, 0, new Image(coffeeBeanImageAddress), new Image(coffeeBeanCardImageAddress));
    }

    @Override
    public void updateImageSituation(Pane pane) {
        startCooldown();
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
    public void wakeUp(Plant plant) {
        if (!plant.isAwake()){
            setAwake(true);

        }
    }
}
