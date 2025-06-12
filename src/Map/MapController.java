package Map;

import Character.KindsOfPlants.*;
import Character.KindsOfZombie.Regular;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import Character.KindsOfZombie.Zombie;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.ArrayList;

public class MapController {

    @FXML
    public Pane paneWindow;
    @FXML
    private GridPane gridPane;
    @FXML
    private ImageView peeShooter;
    @FXML
    private ImageView iceShooter;
    @FXML
    private ImageView sunFlower;
    @FXML
    private ImageView nutFlower;


    GameMap map = new GameMap();
    Plant choosenPlant;
    ZombieFactory zombieFactory;
    Timeline waveZombies;
    Timeline gameLoop;

    ArrayList<Plant> plants = new ArrayList<>();





    @FXML
    public void initialize() {

        mouseEvents();

        zombieFactory = new ZombieFactory(160,160,paneWindow);

        waveZombies = new Timeline(new KeyFrame(Duration.seconds(4),e -> {
            //wave 1:
            attackOne();
        }));
        waveZombies.setCycleCount(1);
        waveZombies.play();

//        gameLoop = new Timeline(new KeyFrame(Duration.millis(1000),e -> {
//            map.checkWar();
//        }));
//        gameLoop.setCycleCount(Timeline.INDEFINITE);
//        gameLoop.play();



        createGrid();
    }
    public void choosePeaShooter(){
        choosenPlant = new PeaShooter(100,5);
    }
    public void chooseIceShooter(){
        choosenPlant = new SnowPea(175,5);
    }
    public void chooseSunFlower(){
        choosenPlant = new SunFlower(50,5,0);
    }
    public void chooseNutFlower(){
        choosenPlant = new WallNut(50,10 ,0);
    }

    public void mouseEvents(){
        peeShooter.setOnMouseClicked(event -> {
            choosePeaShooter();
        });
        iceShooter.setOnMouseClicked(event -> {
            chooseIceShooter();
        });
        sunFlower.setOnMouseClicked(event -> {
            chooseSunFlower();
        });
        nutFlower.setOnMouseClicked(event -> {
            chooseNutFlower();
        });
    }

    public void createGrid(){
        final int rows = gridPane.getRowCount();
        final int cols = gridPane.getColumnCount();

        for(int i = 0 ; i < rows ; i++){
            for(int j = 0; j < cols; j++){

                Pane cell = new Pane();
                cell.setPrefSize(80, 80);

                setOnCell(cell, i, j);

                gridPane.add(cell, j, i);
            }
        }
    }
    public void setOnCell(Pane cell,int row,int col){
        cell.setOnMouseClicked(event -> {
            if (choosenPlant == null ){
                System.out.println("No plant selected");
            }
            else if(!map.isCellEmpty(row, col)){
                System.out.println("cell is already not empty");
            }
            else{

                cell.getChildren().add(choosenPlant.getImageView());
                map.addPlant(choosenPlant, row, col);
                choosenPlant.setX(choosenPlant.getImageView().localToScreen(choosenPlant.getImageView().getBoundsInLocal()).getMinX());
                choosenPlant.setY(choosenPlant.getImageView().localToScreen(choosenPlant.getImageView().getBoundsInLocal()).getMinY());
                choosenPlant.setRow(row);
                System.out.println(choosenPlant.getRow());
                System.out.println(choosenPlant.getX() + " ..." + choosenPlant.getY());
                choosenPlant.updateImageSituation(paneWindow);
                choosenPlant.setRow(row);
                choosenPlant.setCol(col);
                choosenPlant = null;
            }
        });
    }
    public void attackOne(){
        zombieFactory.createRegularZombie(0);
        zombieFactory.createRegularZombie(1);
        zombieFactory.createRegularZombie(2);
        zombieFactory.createRegularZombie(3);
    }


}
