package Character;

public abstract class Zombie {
    private int speed;
    private int hp;

    public Zombie(int speed, int hp) {
        this.speed = speed;
        this.hp = hp;
    }

    public int getHp(){
        System.out.println();
        return hp;
    }
    public int getSpeed() {
        return speed;
    }
}
