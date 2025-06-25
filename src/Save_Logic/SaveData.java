package Save_Logic;

import ApplyGraphics.ChooseCardController;
import ApplyGraphics.MapController;
import ApplyGraphics.ModeController;
import Character.KindsOfPlants.*;
import Character.KindsOfZombie.Zombie;
import Character.Sun;
import Character.Bullet;
import Map.GameMap;
import Map.ZombieFactory;
import javafx.scene.layout.VBox;

import java.io.Serializable;
import java.util.ArrayList;

public class SaveData implements Serializable {
    ModeController.Mode mode;
    int score;
    long time;
    int wave;
    int peashooterCooldown;
    int repeaterCooldown;
    int snowPeaCooldown;
    int sunFlowerCooldown;
    int tallNutCooldown;
    int wallNutCooldown;
    int cherryBombCooldown;
    int jalapenoCooldown;
    ArrayList<Zombie> zombies;
    ArrayList<Plant> plants;
    ArrayList<Sun> suns;
    ArrayList<Plant> playerCard;
    ArrayList<Bullet> bullets;
    public SaveData() {
            this.mode = ModeController.getSelectedMode();
            this.score = MapController.score;
            this.peashooterCooldown = PeaShooter.cooldown;
            this.repeaterCooldown = Repeater.cooldown;
            this.snowPeaCooldown = SnowPea.cooldown;
            this.sunFlowerCooldown = SunFlower.cooldown;
            this.tallNutCooldown = TallNut.cooldown;
            this.wallNutCooldown = WallNut.cooldown;
            this.cherryBombCooldown = CherryBomb.cooldown;
            this.jalapenoCooldown = Jalapeno.cooldown;
            this.time = MapController.time;
            this.wave = MapController.waveCount;
            this.zombies = ZombieFactory.zombies;
            this.plants = GameMap.plants;
            this.suns = Sun.sunList;
            this.playerCard = ChooseCardController.cardPlants;
            this.bullets = PeaPlant.bulletList;
    }

}
