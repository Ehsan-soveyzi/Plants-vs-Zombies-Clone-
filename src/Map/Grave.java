package Map;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.Serializable;
import java.util.ArrayList;

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
