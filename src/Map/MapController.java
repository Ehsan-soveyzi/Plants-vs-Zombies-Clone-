package Map;

import Character.KindsOfZombie.Regular;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import Character.KindsOfZombie.Zombie;
import javafx.scene.layout.Pane;
import Character.KindsOfPlants.Plant;
import Character.KindsOfPlants.PeaShooter;
import javafx.util.Duration;

public class MapController {

    @FXML
    public Pane paneWindow;
    @FXML
    private GridPane gridPane;
    @FXML
    private ImageView peeShooter;


    GameMap map = new GameMap();
    Plant choosenPlant;
    ZombieFactory zombieFactory;
    Timeline timeline;





    @FXML
    public void initialize() {
        peeShooter.setOnMouseClicked(event -> {
            choosePeaShooter();
        });
//        double cellWidth = gridPane.getPrefWidth() / gridPane.getColumnCount();
//        double cellHeight = gridPane.getPrefHeight() / gridPane.getRowCount();

        zombieFactory = new ZombieFactory(160,160,paneWindow);

        timeline = new Timeline(new KeyFrame(Duration.seconds(5),e -> {
            attackOne();
        }));
        timeline.setCycleCount(3);
        timeline.play();

        createGrid();
    }
    public void choosePeaShooter(){
//        choosenPlant = ZombieFactory.createPeaPlant(100,5,750,90);
        choosenPlant = new PeaShooter(100,5);
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
                choosenPlant = null;
            }
        });
    }
    public void attackOne(){
        zombieFactory.createRegularZombie(0);
        zombieFactory.createRegularZombie(1);
        zombieFactory.createRegularZombie(2);
    }


}
