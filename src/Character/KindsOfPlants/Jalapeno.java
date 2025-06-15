package Character.KindsOfPlants;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Jalapeno extends BombPlant {
    public static final int cooldown = 7;
    public static boolean isReady = true;
    private static final String JalapenoImageAddress = "/Images/resources/graphics/Plants/Jalapeno/Jalapeno/Jalapeno_";
    private static final String BurnJalapenoImageAddress =  "/Images/resources/graphics/Plants/Jalapeno/JalapenoExplode/JalapenoExplode_";
    public Jalapeno() {
        //dont have idea about the hp!
        super(125, 100000, new Image(JalapenoImageAddress +"0.png"));
        // امتحان کن اگه ایتچا متود سوختن اجرا بشه یا باید اوراید بشه برای متود burnanimation
    }

    public void burnZombies(){

    }
    public void burnAnimation(){
        playAnimation(7, BurnJalapenoImageAddress);
        // delete background
        burnZombies();
    }
    public static void startCooldown() {
        isReady = false;

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(cooldown), event -> {
            isReady = true;
        }));
        timeline.setCycleCount(1);
        timeline.play();
    }

    @Override
    public void updateImageSituation(Pane pane) {
        playAnimation(7, JalapenoImageAddress);
        startCooldown();
    }
}