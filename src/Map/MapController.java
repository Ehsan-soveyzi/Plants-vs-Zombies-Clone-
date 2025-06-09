package Map;

import Character.KindsOfZombie.Regular;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import Character.KindsOfZombie.Zombie;
import javafx.scene.layout.Pane;
import Character.KindsOfPlants.Plant;
import Character.KindsOfPlants.PeaShooter;

public class MapController {

    GameMap map = new GameMap();
    Plant choosenPlant;

    @FXML
    private Pane paneWindow;
    @FXML
    private GridPane gridPane;
    @FXML
    private ImageView peeShooter;

    @FXML
    public void initialize() {
        peeShooter.setOnMouseClicked(event -> {
            choosePeaShooter();
        });

        double cellWidth = gridPane.getPrefWidth() / gridPane.getColumnCount();
        double cellHeight = gridPane.getPrefHeight() / gridPane.getRowCount();
//        Zombie zombie1 = ZombieFactory.createNormalZombie(1,1500,85);
        Zombie zombie1 = new Regular(10,12,13,1,1500,85);
        zombie1.getImageView().setFitWidth(cellWidth);
        zombie1.getImageView().setFitHeight(cellHeight);
        paneWindow.getChildren().add(zombie1.getImageView());
        zombie1.playWalkingAnimation();
        createGrid();
    }
    public void choosePeaShooter(){
//        choosenPlant = ZombieFactory.createPeaPlant(100,5,750,90);
        choosenPlant = new PeaShooter(100,5,1000,50,2);

    }

    public void createGrid(){
        final int rows = gridPane.getRowCount();
        final int cols = gridPane.getColumnCount();

        for(int i = 0 ; i < rows ; i++){
            for(int j = 0; j < cols; j++){

                Pane cell = new Pane();
                cell.setPrefSize(80, 80); // سایز دلخواه

                int finalI = i;
                int finalJ = j;
                cell.setOnMouseClicked(event -> {
                    if (choosenPlant == null ){
                        System.out.println("No plant selected");
                    }
                    else if(!map.isCellEmpty(finalI, finalJ)){
                        System.out.println("cell is already not empty");
                    }
                    else{
//                        gridPane.add(choosenPlant.getImageView(), finalI, finalJ);
                        cell.getChildren().add(choosenPlant.getImageView());
                        map.addPlant(choosenPlant, finalI, finalJ);
                        choosenPlant.updateImageSituation();
                        choosenPlant = null;
                    }
                });

                gridPane.add(cell, j, i);
            }
        }
    }


}
