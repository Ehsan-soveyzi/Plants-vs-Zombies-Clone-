package Map;

import Character.KindsOfPlants.*;
import Character.KindsOfZombie.Regular;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import Character.KindsOfZombie.Zombie;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import Character.Sun;

import java.util.ArrayList;
import java.util.Random;

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
    private ImageView walNutFlower;
    @FXML
    private ImageView shovel;
    @FXML
    private Label sunPoint;
    @FXML
    private ImageView jalapeno;
    @FXML
    private ImageView cherryBomb;


    GameMap map = new GameMap();
    Plant choosenPlant;
    ZombieFactory zombieFactory;
    Timeline waveZombies;
    Timeline gameLoop;
    static int waveCount = 1;
    boolean shovelUsed  = false;

    public static int score = 1000;

    ArrayList<Plant> plants = new ArrayList<>();





    @FXML
    public void initialize() {

        mouseEvents();

        zombieFactory = new ZombieFactory(160,160,paneWindow);

        waveZombies = new Timeline(new KeyFrame(Duration.seconds(1),e -> {
            //wave 1:
//            if(waveZombies.getCurrentTime() == Duration.seconds(120)) {
//                attackOne();
//            }
//            else if(waveZombies.getCurrentTime() == Duration.seconds(240)) {
//                attackOne();
//            }
//            attackOne();

        }));
        waveZombies.setCycleCount(1);
        waveZombies.play();

        gameLoop = new Timeline(new KeyFrame(Duration.millis(100),e -> {
            map.checkWar();
            sunPoint.setText(Integer.toString(score));
        }));
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        gameLoop.play();


        Timeline timeBetweenSun = new Timeline(new KeyFrame(Duration.seconds(10), event -> {
            Sun sun = new Sun();
            paneWindow.getChildren().add(sun.getImageView());
        }));
        timeBetweenSun.setCycleCount(10);
        timeBetweenSun.play();



        createGrid();
    }
    public void choosePeaShooter(){
        if (score >= 100 && PeaShooter.isReady){
            choosenPlant = new PeaShooter();
        }
    }
    public void chooseIceShooter(){if(score >= 175 && SnowPea.isReady)choosenPlant = new SnowPea();}
    public void chooseSunFlower(){if(score >= 50 && SunFlower.isReady)choosenPlant = new SunFlower();}
    public void chooseWallNutFlower(){if(score >= 50 && WallNut.isReady)choosenPlant = new WallNut();}
    public void chooseJalapenoFlower(){if(score >= 125 && Jalapeno.isReady)choosenPlant = new Jalapeno();}
    public void chooseCherryBomb(){if(score >= 150 && CherryBomb.isReady)choosenPlant = new CherryBomb();}

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
        walNutFlower.setOnMouseClicked(event -> {
            chooseWallNutFlower();
        });
        jalapeno.setOnMouseClicked(event -> {
            chooseJalapenoFlower();
        });
        cherryBomb.setOnMouseClicked(event -> {
            chooseCherryBomb();
        });
        shovel.setOnMouseClicked(event -> {
            shovelUsed = true;
            choosenPlant = null;
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
            if(!map.isCellEmpty(row, col) && shovelUsed){
                //using shovel
                shovel(row, col);
                System.out.println("cell is already not empty");
            } else if (!map.isCellEmpty(row, col)) {
                System.out.println("cell is already not empty");
            } else if (choosenPlant == null ){
                System.out.println("No plant selected");
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
                shovelUsed = false;
            }
        });
    }
    
    public void attackOne(int wave){
        Random rand  = new Random();
        for (int i = 0; i < waveCount; i++) {
            int iterate = rand.nextInt(wave);
            if(waveCount == 1)zombieFactory.createRegularZombie(iterate);
            else if(waveCount == 2){
                zombieFactory.createRegularZombie(iterate);
                zombieFactory.createConeHeadZombie(iterate);
            }
            else if(waveCount == 3){
                zombieFactory.createRegularZombie(iterate);
                zombieFactory.createConeHeadZombie(iterate);
                zombieFactory.createScreenDoorZombie(iterate);
            }
        }
    }

    public void attackTwo(){
        for(int i = 0;i < 5;i++){
            zombieFactory.createRegularZombie(i);
            zombieFactory.createConeHeadZombie(i);
        }
    }

    public void attackThree(){
        for(int i = 0;i < 5;i++){

        }
    }


    public void shovel(int row, int col){
        choosenPlant = null;
        map.removePlant(row, col);
        shovelUsed = false;
    }

    public void chooseRandomZombie(){
        Random rand = new Random();
        int randomRow = rand.nextInt(5);
        double number = Math.random();
        if(number < 0.35)zombieFactory.createRegularZombie(randomRow);
        else if(number < 0.60)zombieFactory.createConeHeadZombie(randomRow);
        else if(number < 0.80)zombieFactory.createScreenDoorZombie(randomRow);
        else zombieFactory.createIMPZombie(randomRow);
    }
}
