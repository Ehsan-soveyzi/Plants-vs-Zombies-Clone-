package Character.KindsOfPlants;

import Character.KindsOfZombie.Zombie;
import Map.ZombieFactory;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.Objects;

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
        for(Zombie x : ZombieFactory.zombies){
            if(x.getRow() == getRow()){
                x.burn();
            }
        }
    }
    public void burnAnimation(){
        playAnimationJalapeno(7, BurnJalapenoImageAddress);
        getImageView().setImage(null);
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

    public void playAnimationJalapeno(int number, String address){
        Image[] frames = new Image[number];
        for (int i = 0; i < number; i++) {
            frames[i] = new Image(Objects.requireNonNull(getClass().getResourceAsStream(
                    address + i + ".png"
            )));
        }
        ImageView imageView = getImageView();

        final int[] frameIndex = {0};

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), e -> {
            imageView.setImage(frames[frameIndex[0]]);
            frameIndex[0] = (frameIndex[0] + 1) % frames.length;

        }));
        timeline.setCycleCount(frames.length);
        timeline.setOnFinished(event -> {
            imageView.setImage(null);
        });
        timeline.play();

    }

    @Override
    public void updateImageSituation(Pane pane) {
        playAnimationJalapeno(7, JalapenoImageAddress);
        die();
        burnAnimation();
        startCooldown();
    }
}