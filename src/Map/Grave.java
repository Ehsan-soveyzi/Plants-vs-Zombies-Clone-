package Map;

import ApplyGraphics.MapController;
import GameServer.Server;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Grave implements Serializable {
    private static final String ImageAddress = "/Images/resources/graphics/extentions/grave.png";
    public static ArrayList<Grave> graves = new ArrayList<>();
    private int row;
    private int col;
    private double x;
    private double y;
    private int hp;

    private transient ImageView imageView;

    public Grave(int row, int col) {
        this.row = row;
        this.col = col;
        this.hp = 5;
        imageView = new ImageView(new Image(ImageAddress));
        graves.add(this);
    }

    public void generateZombies() {
        int number = Server.generateRandom(10);
        if(MapController.time/1000 <= 60){
            if(number < 5)MapController.zombieFactory.createRegularZombie(row, x);
            else MapController.zombieFactory.createConeHeadZombie(row, x);
        }
        else{
            if(number < 4)MapController.zombieFactory.createRegularZombie(row, x);
            else if(number < 7)MapController.zombieFactory.createConeHeadZombie(row, x);
            else if(number < 9)MapController.zombieFactory.createScreenDoorZombie(row, x);
            else MapController.zombieFactory.createIMPZombie(row, x);
        }
    }

    public int getRow() {return row;}
    public void setRow(int row) {this.row = row;}
    public int getCol() {return col;}
    public void setCol(int col) {this.col = col;}
    public double getX() {return x;}
    public void setX(double x) {this.x = x;}
    public double getY() {return y;}
    public void setY(double y) {this.y = y;}
    public int getHp() {return hp;}
    public void setHp(int hp) {this.hp = hp;}
    public ImageView getImageView(){return imageView;}
    public void setImageView(ImageView imageView){this.imageView = imageView;}
}
