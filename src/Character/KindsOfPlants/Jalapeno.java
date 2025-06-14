package Character.KindsOfPlants;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class Jalapeno extends BombPlant {
    private static final String JalapenoImageAddress = "/Images/resources/graphics/Plants/Jalapeno/Jalapeno/Jalapeno_";
    private static final String BurnJalapenoImageAddress =  "/Images/resources/graphics/Plants/Jalapeno/JalapenoExplode/JalapenoExplode_";
    Jalapeno(int cost, int hp) {
        super(cost, hp, new Image(JalapenoImageAddress +"0.png"));

        // امتحان کن اگه ایتچا متود سوختن اجرا بشه یا باید اوراید بشه برای متود burnanimation
    }

    public void burnZombies(){

    }
    public void burnAnimation(){
        playAnimation(7, BurnJalapenoImageAddress);
        // delete background
        burnZombies();
    }

    @Override
    public void updateImageSituation(Pane pane) {
        playAnimation(7, JalapenoImageAddress);
    }
}