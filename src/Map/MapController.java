package Map;

import Character.KindsOfPlants.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
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
    @FXML
    private ImageView tallNut;
    @FXML
    private ImageView rePeater;


    GameMap map = new GameMap();
    Plant choosenPlant;
    ZombieFactory zombieFactory;
    Timeline waveZombies;
    Timeline gameLoop;
    long time;
    static int waveCount = 1;
    boolean shovelUsed  = false;

    public static int score = 1000;

    ArrayList<Plant> plants = new ArrayList<>();





    @FXML
    public void initialize() {

        mouseEvents();

        zombieFactory = new ZombieFactory(160,160,paneWindow);


        gameLoop = new Timeline(new KeyFrame(Duration.millis(100),e -> {
            setOnMouseEntered();
            time += 100;
            if(time % 10000 == 0)Sun.sunCollector(paneWindow);
            map.checkWar();
            sunPoint.setText(Integer.toString(score));
            if(time % 10000 == 0)attackOne();
            if(time % 20000 == 0)waveCount++;
        }));
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        gameLoop.play();






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
    public void chooseTallNut(){if(score >= 125 && TallNut.isReady)choosenPlant = new TallNut();}
    public void chooseRePeater(){if(score >= 200 && Repeater.isReady)choosenPlant = new Repeater();}


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
        tallNut.setOnMouseClicked(event -> {
            chooseTallNut();
        });
        rePeater.setOnMouseClicked(event -> {
            chooseRePeater();
        });
        shovel.setOnMouseClicked(event -> {
            shovelUsed = true;
            choosenPlant = null;
        });

    }

    public void setOnMouseEntered(){

        for (Node node : gridPane.getChildren()) {
            node.setOnMouseEntered(event -> {
                if (choosenPlant != null) {
                    Pane cell = (Pane) node;

                    boolean hasImageView = false;
                    for (Node child : cell.getChildren()) {
                        if (child instanceof ImageView) {
                            hasImageView = true;
                            break;
                        }
                    }

                    if (hasImageView) {
                        cell.setStyle("-fx-background-color: rgba(255, 0, 0, 0.5);");
                    } else {
                        cell.setStyle("-fx-background-color: rgba(255, 255, 100, 0.5);");
                    }
                }
            });

            node.setOnMouseExited(event -> {
                ((Pane) node).setStyle("");
            });
        }

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
    
    public void attackOne(){
        Random rand  = new Random();
        for (int i = 1; i <= waveCount; i++) {
            chooseRandomZombie(i);
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

    public void chooseRandomZombie(int wave){
        Random rand = new Random();
        int randomRow = rand.nextInt(5);
        int number = rand.nextInt(wave);
        if(number <= 4)zombieFactory.createRegularZombie(randomRow);
        else if(number <= 7)zombieFactory.createConeHeadZombie(randomRow);
        else if(number <= 9)zombieFactory.createScreenDoorZombie(randomRow);
        else zombieFactory.createIMPZombie(randomRow);
    }
}
