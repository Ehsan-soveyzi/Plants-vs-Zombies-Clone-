package Character;

import javafx.scene.image.Image;

class AllKinds {

}
class Regular extends Zombie {
    private final static String regularImageAddress = "/Character/regular.png";
    // or have a image field in parent class and
    // static because before making this field the super execute
    // میتونیم تعریف نکنیم صرفا این فیلد رو همون ادرس رو مستقیم بدیم

    public Regular(int hp, double speed, double eatingSpeed, int row, double x, double y) {
        super(hp, speed, eatingSpeed, row, x, y, new Image(regularImageAddress));
    }
}
class ConeHead extends Zombie {
    private final static String coneHeadImageAddress = "/Character/coneHead.png";
    public ConeHead(int hp, double speed, double eatingSpeed, int row, double x, double y) {
        super(hp, speed, eatingSpeed, row, x, y, new Image(coneHeadImageAddress));
    }
}
class ScreenDoor extends Zombie {
    private final static String screenDoorImageAddress = "/Character/screenDoor.png";
    public ScreenDoor(int hp, double speed, double eatingSpeed, int row, double x, double y) {
        super(hp, speed, eatingSpeed, row, x, y, new Image(screenDoorImageAddress));
    }
}
class IMPZombie extends Zombie {
    private final static String IMPZombieImageAddress = "/Character/IMPZombie.png";
    public IMPZombie(int hp, double speed, double eatingSpeed, int row, double x, double y) {
        super(hp, speed, eatingSpeed, row, x, y, new Image(IMPZombieImageAddress));
    }
}
