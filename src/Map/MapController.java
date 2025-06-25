package Map;

import Character.KindsOfPlants.*;
import Character.KindsOfZombie.*;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
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
            if(time > 120000) gameLoop.stop();
            if(time % 1000 == 0){
                System.out.println("in seconds : "+time);
                handleZombiesWave1();
            }
            time += 100;
            if(time % 10000 == 0)Sun.sunCollector(paneWindow);
            map.checkWar();
            sunPoint.setText(Integer.toString(score));
//            if(time % 10000 == 0)attackOne();
//            if(time % 20000 == 0)waveCount++;
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

    public void shovel(int row, int col){
        choosenPlant = null;
        map.removePlant(row, col);
        shovelUsed = false;
    }

        private void handleZombiesWave(){
        long current = time/1000;
        ArrayList<Zombie> types;
        Random rand = new Random();
        if(current > 60){
            return;
        }
        else if (current>= 47) {
            types = possibleZombie(true, true, true, true);
            for(int i = 0;i < 5;i++){ // 3 zombies per second
                for(int j = 0;j < 3;j++){
                    int index = rand.nextInt(types.size());
                    zombieFactory.createZombie(types.get(index), i);
                }
            }
        } else if (current >= 45) {
            if (current % 2 == 1){
                types = possibleZombie(true, true, true, true);
                zombieFactory.createZombie(types.get(rand.nextInt(types.size())), rand.nextInt(5));
                zombieFactory.createZombie(types.get(rand.nextInt(types.size())), rand.nextInt(5));
            }
        }else if (current > 33) {
            if (current % 2 == 0){
                types = possibleZombie(true, true, true, false);
                zombieFactory.createZombie(types.get(rand.nextInt(types.size())), rand.nextInt(5));
            }
        }else if (current >= 26) {
            types = possibleZombie(true, true, false, false);
            for(int i = 0;i < 5;i++){ // 3 zombies per second
                for(int j = 0;j < 2;j++){
                    int index = rand.nextInt(types.size());
                    zombieFactory.createZombie(types.get(index), i);
                }
            }
        } else if (current>= 15) {
            if (current % 2 == 0){
                types = possibleZombie(true, true, false, false);
                zombieFactory.createZombie(types.get(rand.nextInt(types.size())), rand.nextInt(5));
            }

        }else if (current >= 0) {
            if (current % 3 == 0){
                types = possibleZombie(true, false, false, false);
                zombieFactory.createZombie(types.get(rand.nextInt(types.size())), rand.nextInt(5));
            }
        }


    }
    private void handleZombiesWave1() {
        long current = time / 1000;
        ArrayList<Zombie> types;
        Random rand = new Random();

        // choose zombies depending on the time
        boolean normal = true;
        boolean conehead = current >= 20;
        boolean screendoor = current >= 60;
        boolean imp = current >= 80;
        // make an array for possible zombies
        types = possibleZombie(normal, conehead, screendoor, imp);

        boolean isBreakTime = (current >= 45 && current < 50) || (current >= 90 && current < 100);
        boolean isStrongAttack = (current >= 50 && current < 60) || (current >= 100 && current < 120);

        if (isBreakTime) {
            return;
        }
        int numberOfZombies;

        if (isStrongAttack) {
            if (current % 3 != 0) return;
            numberOfZombies = 2 + (int)(current / 35);
        }
        else {
            if (current % 6 != 0) return;
            numberOfZombies = 1 + (int)(current / 60);

        }

        for (int i = 0; i < numberOfZombies; i++) {
            int index = rand.nextInt(types.size());
            int lane = rand.nextInt(5);
            zombieFactory.createZombie(types.get(index), lane);

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }


    private ArrayList<Zombie> possibleZombie(boolean regular, boolean coneHead, boolean screenDoor, boolean imp) {
        ArrayList<Zombie> zombies = new ArrayList<>();
        if (regular) zombies.add(new Regular());
        if (coneHead) zombies.add(new ConeHead());
        if (screenDoor) zombies.add(new ScreenDoorZombie());
        if (imp) zombies.add(new IMPZombie());
        return zombies;
    }
}
