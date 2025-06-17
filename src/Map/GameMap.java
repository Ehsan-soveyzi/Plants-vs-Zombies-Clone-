package Map;

import Character.KindsOfPlants.Plant;
import Character.KindsOfZombie.Regular;
import Character.KindsOfZombie.Zombie;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import javax.swing.text.html.ImageView;
import java.util.ArrayList;

public class GameMap {

    private final int ROWS = 5;
    private final int COLS = 9;

    public static ArrayList<Plant> plants = new ArrayList<>();



    private final int CELL_WIDTH = 1100;
    private final int CELL_HEIGHT = 700;
    private final int OFFSET_Y = 20;


    private final Plant[][] grid = new Plant[ROWS][COLS];

    public boolean isCellEmpty(int row, int col) {
        return grid[row][col] == null && isValidCell(row,col);
    }

    public void addPlant(Plant plant, int row, int col) {
        if(!isCellEmpty(row, col))return;
        grid[row][col] = plant;
        plants.add(plant);
        MapController.score -= plant.getCost();
    }

    public void checkWar() {
        for (Zombie z : ZombieFactory.zombies) {
            for (Plant plant : GameMap.plants) {
                if(plant.isDead()){
                    plants.remove(plant);
                    removePlant(plant.getRow(), plant.getCol());
//                    System.out.println("123");
                    break;
                }
                else if (z.getX() - plant.getX() <= 20 && z.getX() - plant.getX() >= -50 && z.getRow() == plant.getRow() && !z.isEating() && !z.isDead()) {
                    z.stopWalking();
                    z.getTimeline().stop();
                    z.updateImageSituation();
                    z.startBiting(plant, this);
//                    break;
                }
            }
        }
    }

    public void removePlant(int row, int col) {
        if(isValidCell(row, col)){
            Plant plant = grid[row][col];
            if (plant != null && plant.getImageView() != null) {
                Pane parent = (Pane) plant.getImageView().getParent();
                if (parent != null) parent.getChildren().remove(plant.getImageView());
                plant.setDead(true);
            }
            plants.remove(plant);
            grid[row][col] = null;
        }
    }


    public Plant getPlant(int row, int col) {
        if(!isValidCell(row, col)) return null;
        return grid[row][col];
    }

    public double getXForCol(int col){
        return col*CELL_WIDTH;
    }

    public double getYForRow(int row){
        return row*CELL_HEIGHT + OFFSET_Y;
    }

    public int getRows(){
        return ROWS;
    }

    public int getCols(){
        return COLS;
    }

    public Plant[][] getGrid() {
        return grid;
    }

    public boolean isValidCell(int row, int col) {
        return row >= 0 && row < ROWS && col >= 0 && col < COLS;
    }


}

