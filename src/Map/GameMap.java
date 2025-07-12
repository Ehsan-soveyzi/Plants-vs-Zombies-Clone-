package Map;

import Character.KindsOfPlants.Plant;
import Character.KindsOfPlants.Plantern;
import Character.KindsOfZombie.Zombie;
import GameServer.Server;
import javafx.scene.layout.Pane;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class GameMap {

    public static GameMap instance;
    private final int ROWS = 5;
    private final int COLS = 9;
    private final Plant[][] grid = new Plant[ROWS][COLS];

    private final boolean[][] graved = new boolean[ROWS][COLS];
    private final boolean[][] foged = new boolean[ROWS][COLS];

    public static ArrayList<Plant> plants = new ArrayList<>();


    private GameMap() {}

    public static GameMap getInstance() {
        if(instance == null) instance = new GameMap();
        return instance;
    }

    public boolean isCellEmpty(int row, int col) {
        return grid[row][col] == null && isValidCell(row,col);
    }

    public void addPlant(Plant plant, int row, int col) {
        if(!isCellEmpty(row, col))return;
        grid[row][col] = plant;
    }


    public void checkWar() {
        for (Zombie z : ZombieFactory.zombies) {
            for (Plant plant : plants) {
                if (z.getX() - plant.getX() <= 20 && z.getX() - plant.getX() >= -50 && z.getRow() == plant.getRow() && !z.isEating() && !z.isDead() && !z.isFreezed()) {
                    z.stopWalking();
                    z.getTimeline().stop();
                    z.updateImageSituation();
                    z.startBiting(plant);
                    break;
                }
            }for(Zombie zombie : Zombie.hypnoZombie){
                if(z.getX() - zombie.getX() <= 20 && z.getX() >= -50 && z.getRow() == zombie.getRow()){
                    if(!z.isEating() && !z.isDead() && !z.isFreezed()){
                    z.stopWalking();
                    z.getTimeline().stop();
                    z.updateImageSituation();
                    z.startBiting(zombie);
                    }
                    if(!zombie.isDead() && !zombie.isFreezed() && !zombie.isEating()){
                        zombie.stopWalking();
                        zombie.getTimeline().stop();
                        zombie.updateImageSituation();
                        zombie.startBiting(z);
                    }
                }
            }
        }
    }

    public void zombieGraveAttack() {;
        int numberOfZombies = Server.generateRandom(Grave.graves.size());
        for(Grave grave : Grave.graves) {
            grave.generateZombies();
            numberOfZombies--;
            if(numberOfZombies == -1) break;
        }
    }

    public void removePlant(int row, int col) {
        if(isValidCell(row, col)){
            Plant plant = grid[row][col];
            if (plant != null && plant.getImageView() != null) {
                Pane parent = (Pane) plant.getImageView().getParent();
                if (parent != null) parent.getChildren().remove(plant.getImageView());
                plant.setDead(true);
//                if(plant instanceof Plantern)((Plantern) plant).setFog(true);
            }
            plants.remove(plant);
            grid[row][col] = null;
        }
    }

    public void refreshPlants() {
        for(int i = 0;i < ROWS;i++){
            for(int j = 0;j < COLS;j++){
                grid[i][j] = null;
            }
        }
    }


    public boolean isValidCell(int row, int col) {
        return row >= 0 && row < ROWS && col >= 0 && col < COLS;
    }

    public void generateGrave(){
        //at most 5 grave can be existed in the map
        int numberOfGraves = Server.generateRandom(5);
        for(int i = 0; i < numberOfGraves; i++){
            int row = Server.generateRandom(5);
            int col = Server.generateRandom(3) + 6;
            new Grave(row,col);
            graved[row][col] = true;
        }
    }

    public void initializeFog(){
        for(int i = 0;i < ROWS;i++){
            for(int j = 5;j < COLS;j++){
                foged[i][j] = true;
            }
        }
    }



    public void cleanGrave(){
        for(boolean[] row : graved){
            Arrays.fill(row, false);
        }
    }

    public Plant getPlant(int row, int col) {
        if(!isValidCell(row, col)) return null;
        return grid[row][col];
    }
    public boolean getGraved(int row, int col) {return graved[row][col];}
    public void setGraved(int row, int col,boolean bool){graved[row][col] = bool;}
    public void setFoged(int row, int col,boolean bool){foged[row][col] = bool;}
    public ArrayList<Plant> getPlants() {return plants;}
    public int getRows(){
        return ROWS;
    }
    public int getCols(){
        return COLS;
    }
    public Plant[][] getGrid() {
        return grid;
    }
    public boolean getFog(int row, int col) {return foged[row][col];}

    public boolean[][] getFoged() {
        return foged;
    }
}


