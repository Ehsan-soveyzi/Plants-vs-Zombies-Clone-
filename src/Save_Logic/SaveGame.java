package Save_Logic;

import ApplyGraphics.ChooseCardController;
import ApplyGraphics.MapController;
import ApplyGraphics.ModeController;
import Character.KindsOfPlants.IceShroom;
import Character.KindsOfPlants.PeaShooter;
import Character.KindsOfPlants.Plant;
import Character.KindsOfPlants.*;
import Character.KindsOfZombie.*;
import Map.GameMap;
import Character.*;
import Map.Grave;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.*;

//no need to create any object from this class!
public abstract class SaveGame implements Serializable {
    //check file exist.
    public static boolean load = new File("saveGame.dat").exists();

    public static Plant identifyKindsOfPlant(Plant plant){
        if(plant instanceof SunFlower)return new SunFlower();
        if(plant instanceof PeaShooter)return new PeaShooter();
        if(plant instanceof Repeater)return new Repeater();
        if(plant instanceof SnowPea)return new SnowPea();
        if(plant instanceof TallNut)return new TallNut();
        if(plant instanceof WallNut)return new WallNut();
        if(plant instanceof Jalapeno)return new Jalapeno();
        if(plant instanceof CherryBomb)return new CherryBomb();
        if(plant instanceof GraveBuster)return new GraveBuster();
        if(plant instanceof ScaredyShroom)return new ScaredyShroom();
        if(plant instanceof PuffShroom)return new PuffShroom();
        if(plant instanceof Plantern )return new Plantern();
        if(plant instanceof Blover)return new Blover();
        if(plant instanceof DoomShroom)return new DoomShroom();
        if(plant instanceof HypnoShroom)return new HypnoShroom();
        if(plant instanceof IceShroom)return new IceShroom();
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
                Zombie loadZombie = MapController.zombieFactory.createZombie(zombie,zombie.getRow());
                loadZombie.setHp(zombie.getHp());
                loadZombie.setSlowed(zombie.isSlowed());
                loadZombie.setFreezed(zombie.isFreezed());
//                loadZombie.updateImageSituation();
            }

            for(Plant plant : data.plants){
                Plant loadPlant = identifyKindsOfPlant(plant);
                loadPlant.setHp(plant.getHp());
                loadPlant.setRow(plant.getRow());
                loadPlant.setX(plant.getX());
                loadPlant.setY(plant.getY());
                loadPlant.setCol(plant.getCol());
                GameMap.getInstance().plants.add(loadPlant);
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
            for(Sun sun : data.suns){
                sun.setImageView(new ImageView(new Image("/Images/resources/graphics/Plants/Sun/sun.png")));
                sun.getImageView().setX(sun.getX());
                sun.getImageView().setY(sun.getY());
                Sun.sunList.add(sun);
            }
            for(Grave grave : data.graves){
                grave.setImageView(new ImageView("/Images/resources/graphics/extentions/grave.png"));
                Grave.graves.add(grave);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
