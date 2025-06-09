package Character;

public class ZombieFactory {
    public static NormalZombie createNormalZombie(int row, double x, double y) {
        return new NormalZombie(row, x, y);
    }

    public static PeaPlant createPeaPlant(int cost,int hp, int row, int col) {
        return new PeaPlant(cost,hp,row,col);
    }
}
