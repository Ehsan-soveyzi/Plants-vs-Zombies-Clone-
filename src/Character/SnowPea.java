package Character;

import javafx.scene.image.Image;

public class SnowPea extends PeaPlant {
    private static final String snowPeaImageAddress = "/Character/snowPea.png";
    public SnowPea(int cost, int hp, double x, double y, int row) {
        super(cost, hp, x, y, row, new Image(snowPeaImageAddress));

    }

    @Override
    public void shoot() {
        Bullet snowBullet = new SlowBullet(getX(), getY(),getRow(),1,0.5);

    }



}