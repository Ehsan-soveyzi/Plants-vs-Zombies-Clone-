package Save_Logic;

import ApplyGraphics.MapController;
import Character.KindsOfPlants.PeaShooter;
import Character.KindsOfPlants.Plant;
import Character.KindsOfPlants.*;
import Character.KindsOfZombie.*;
import Map.GameMap;
import Map.ZombieFactory;

import javax.smartcardio.Card;
import java.io.*;

public abstract class SaveGame implements Serializable {
    public static Zombie identifyKindsOfZombie(Zombie zombie){
        if(zombie instanceof Regular)return MapController.zombieFactory.createRegularZombie(zombie.getRow(),zombie.getX());
        if(zombie instanceof ConeHead)return MapController.zombieFactory.createConeHeadZombie(zombie.getRow(),zombie.getX());
        if(zombie instanceof ScreenDoorZombie)return MapController.zombieFactory.createScreenDoorZombie(zombie.getRow(),zombie.getX());
        if (zombie instanceof IMPZombie)return MapController.zombieFactory.createIMPZombie(zombie.getRow(),zombie.getX());
        return null;
    }
    public static Plant identifyKindsOfPlant(Plant plant){
        if(plant instanceof SunFlower)return new SunFlower();
        if(plant instanceof PeaShooter)return new PeaShooter();
        if(plant instanceof Repeater)return new Repeater();
        if(plant instanceof SnowPea)return new SnowPea();
        if(plant instanceof TallNut)return new TallNut();
        if(plant instanceof WallNut)return new WallNut();
        return null;
    }
    public static void saveGame(){
        try(ObjectOutputStream saveGame = new ObjectOutputStream(new FileOutputStream("saveGame.dat"))){
            SaveData data = new SaveData();
            saveGame.writeObject(data);
            System.out.println("game successfully saved!!");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public static void loadGame(){
        try(ObjectInputStream loadGame = new ObjectInputStream(new FileInputStream("saveGame.dat"))){
            SaveData data = (SaveData) loadGame.readObject();
            MapController.score = data.score;
            for(Zombie zombie : data.zombies){
                Zombie loadZombie = identifyKindsOfZombie(zombie);
                loadZombie.setHp(zombie.getHp());
                loadZombie.setSlowed(zombie.isSlowed());
            }
            for(Plant plant : data.plants){
                Plant loadPlant = identifyKindsOfPlant(plant);
                loadPlant.setHp(plant.getHp());
                loadPlant.setRow(plant.getRow());
                loadPlant.setX(plant.getX());
                loadPlant.setY(plant.getY());
                loadPlant.setCol(plant.getCol());
                GameMap.plants.add(loadPlant);
            }
            for(Plant plant : data.playerCard){
                Plant loadCard = identifyKindsOfPlant(plant);

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
