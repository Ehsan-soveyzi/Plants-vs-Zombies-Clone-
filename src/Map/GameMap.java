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

    public boolean addPlant(Plant plant, int row, int col) {
        if(!isCellEmpty(row, col)) return false;
        grid[row][col] = plant;
        plants.add(plant);
        return true;
    }

    public void checkWar(){
        for(Zombie z : ZombieFactory.zombies){
            for(Plant plant : GameMap.plants){
                if(z.getX() - plant.getX() >= 100 && z.getRow() == plant.getRow() ){
//                    System.out.println("zombie eating .........");
                    z.stopWalking();
                    Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000), e -> {
                        z.bite(plant);
//                        System.out.println("bite....");
//                        System.out.println(plant.getHp());
                        if(plant.getHp() == 0){
                            removePlant(plant.getRow(),plant.getCol());
//                            System.out.println(grid[plant.getRow()][plant.getCol()]);
                        }
                    }));
                    timeline.setCycleCount(Timeline.INDEFINITE);
                    timeline.play();
                }
            }
        }
    }

    public void removePlant(int row, int col) {
        if(isValidCell(row, col)){
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

    //no need
//    public int getColForX(double x){
//        return (int)(x/CELL_WIDTH);
//    }

//    public int getRowForY(double y){
//        return (int)((y - OFFSET_Y) / CELL_HEIGHT);
//    }

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

