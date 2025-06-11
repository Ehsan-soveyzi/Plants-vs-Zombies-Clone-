package Character.KindsOfPlants;

import javafx.scene.image.Image;

public class Jalapeno extends BombPlant {
    private static final String JalapenoImageAddress = "/Images/resources/graphics/Plants/Jalapeno/Jalapeno/Jalapeno_";
    private static final String BurnJalapenoImageAddress =  "/Images/resources/graphics/Plants/Jalapeno/JalapenoExplode/JalapenoExplode_";
    Jalapeno(int cost, int hp, int row) {
        super(cost, hp, row, new Image(JalapenoImageAddress +"0.png"));
        playAnimation(7, JalapenoImageAddress);
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
    public void updateImageSituation() {

    }
}