package Character;

public class ZombieFactory {
    public static NormalZombie createNormalZombie(int row, double x, double y) {
        return new NormalZombie(row, x, y);
    }
}
