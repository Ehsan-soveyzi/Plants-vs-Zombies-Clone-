package Character;

public abstract class BombPlant extends Plant {
    BombPlant(int cost, int hp, double x, double y) {
        super(cost,hp, x, y);
    }
    public abstract void burnZombies();
}

abstract class CherryBomb extends BombPlant {
    CherryBomb(int cost, int hp, double x, double y) {
        super(cost, hp, x, y);

    }
    public void burnZombies(){

    }
}
class Jalapeno extends BombPlant {
    Jalapeno(int cost, int hp, double x, double y) {
        super(cost, hp, x, y);
    }

    public void burnZombies(){

    }

}
