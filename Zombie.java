public abstract class Zombie {
    private int speed;
    private int hp;

    public Zombie(int speed, int hp) {
        this.speed = speed;
        this.hp = hp;
    }

    public int getHp(){
        return hp;
    }
    public int getSpeed() {
        return speed;
    }
}
