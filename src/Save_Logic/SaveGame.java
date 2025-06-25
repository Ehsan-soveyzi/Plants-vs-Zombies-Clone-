package Save_Logic;

import ApplyGraphics.ChooseCardController;
import ApplyGraphics.MapController;
import ApplyGraphics.ModeController;
import Character.KindsOfPlants.PeaShooter;
import Character.KindsOfPlants.Plant;
import Character.KindsOfPlants.*;
import Character.KindsOfZombie.*;
import Map.GameMap;
import Character.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.*;

public abstract class SaveGame implements Serializable {
    public static boolean load = new File("saveGame.dat").exists();

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
        if(plant instanceof Jalapeno)return new Jalapeno();
        if(plant instanceof CherryBomb)return new CherryBomb()  ;
        return null;
    }

    public static Bullet identifyKindsOfBullet(Bullet bullet){
        if(bullet instanceof NormalBullet)return new NormalBullet(bullet.getX(),bullet.getY(),bullet.getRow());
        if(bullet instanceof SnowBullet)return new SnowBullet(bullet.getX(),bullet.getY(),bullet.getRow());
        return null;
    }

    public static void saveGame(){
        load = true;
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
            ModeController.setSelectedMode(data.mode);
            MapController.score = data.score;
            MapController.time = data.time;
            MapController.waveCount = data.wave;
            PeaShooter.cooldown = data.peashooterCooldown;
            Repeater.cooldown = data.repeaterCooldown;
            SnowPea.cooldown = data.snowPeaCooldown;
            TallNut.cooldown = data.tallNutCooldown;
            Jalapeno.cooldown = data.jalapenoCooldown;
            CherryBomb.cooldown = data.cherryBombCooldown;
            WallNut.cooldown = data.wallNutCooldown;
            SunFlower.cooldown = data.sunFlowerCooldown;

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

            ChooseCardController.cards.getChildren().clear();
            ChooseCardController.cardPlants.clear();
            ChooseCardController.cards.setLayoutY(150);

            for(Plant plant : data.playerCard){
                Plant loadCard = identifyKindsOfPlant(plant);
                ChooseCardController.cardPlants.add(loadCard);
                ChooseCardController.cards.getChildren().add(loadCard.getCardView());
            }

            for(Bullet bullet : data.bullets){
                Bullet loadBullet = identifyKindsOfBullet(bullet);
                PeaPlant.bulletList.add(loadBullet);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
