package Character;

public abstract class Plant {



    private int cost;
    private double eatDuration;
    private double hp;
    private double x, y;

    static private double cooldown;

    Plant(int cost, double eatDuration, double hp, double x, double y) {
        this.cost = cost;
        this.eatDuration = eatDuration;
        this.hp = hp;
        this.x = x;
        this.y = y;

    }
    public void takeDamage(double dmg) {
        hp -= dmg;
        if (hp <= 0) die();
    }

    public void die() {
        clearDiedPlant();
    }
    private void clearDiedPlant(){ // با مختصات گرید

    }

    public int getCost() {return cost;}
    public void setCost(int cost) {this.cost = cost;}
    public double getEatDuration() {return eatDuration;}
    public void setEatDuration(double eatDuration) {this.eatDuration = eatDuration;}
    public double getHp() {return hp;}
    public void setHp(double hp) {this.hp = hp;}
    public double getX() {return x;}
    public void setX(double x) {this.x = x;}
    public double getY() {return y;}
    public void setY(double y) {this.y = y;}
    public static double getCooldown() {return cooldown;}
    public static void setCooldown(double cooldown) {Plant.cooldown = cooldown;}
}

abstract class PeaPlant extends Plant {
    private int attackPower; // amount of damage per seconds
    private int attackSpeed; // seconds between

    PeaPlant(int cost,double eatDuration, double hp, double x, double y, int attackPower, int attackSpeed) {
        super(cost, eatDuration, hp, x, y);
        this.attackPower = attackPower;
        this.attackSpeed = attackSpeed;
    }
    private void attack(){
//        if(!listOfZombieInRow[y].isEmpty()){
//            listOfZombieInRow[y].getTop().takedamge();
//        }
    }
}
abstract class NutPlant extends Plant {
    // میتونیم با خود کلاس نوشت
    NutPlant(int cost,double eatDuration, double hp, double x, double y) {
        super(cost, eatDuration, hp, x, y);
    }
}
abstract class BombPlant extends Plant {
    BombPlant(int cost,double eatDuration, double hp, double x, double y) {
        super(cost, eatDuration, hp, x, y);
    }
}
abstract class otherPlant extends Plant {
    otherPlant(int cost,double eatDuration, double hp, double x, double y) {
        super(cost, eatDuration, hp, x, y);
    }
}
