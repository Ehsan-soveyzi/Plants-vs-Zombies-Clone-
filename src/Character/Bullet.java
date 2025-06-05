package Character;


import javafx.scene.image.ImageView;

public abstract class Bullet {
    private double speed;
    private double x,y;
    //the damage always equal to one!
    private int damage;
    private int row;
    private ImageView imageView = new ImageView();

    private boolean isAlive;

    public Bullet(double x,double y,int row,double speed,int damage) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.damage = damage;
        this.row = row;
        this.isAlive = true;

    }

    public void update(double deltaTime) {
        if(!isAlive)return;

        x += speed*deltaTime;
        imageView.setLayoutX(x);

        //the implementation of the damaging to the zombies would be here.
        //checking the distance between each zombie and bullet!

    }

    //after each shot the current bullet will die!
    public void die(){
        isAlive = false;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public abstract void setupImage();

    public abstract void onHit(Zombie zombie);

    public int getRow() {
        return row;
    }
    public void setRow(int row) {
        this.row = row;
    }
    public double getY(){
        return y;
    }
    public void setY(double y){
        this.y = y;
    }
}
//other class like peashot and iceshot extent this bullets!
