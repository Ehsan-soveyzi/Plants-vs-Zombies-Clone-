package Save_Logic;

import ApplyGraphics.ChooseCardController;
import ApplyGraphics.MapController;
import Character.KindsOfPlants.PeaPlant;
import Character.KindsOfPlants.Plant;
import Character.KindsOfZombie.Zombie;
import Character.Sun;
import Character.Bullet;
import Map.GameMap;
import Map.ZombieFactory;
import javafx.scene.layout.VBox;

import java.io.Serializable;
import java.util.ArrayList;

public class SaveData implements Serializable {
    int score;
    ArrayList<Zombie> zombies;
    ArrayList<Plant> plants;
    ArrayList<Sun> suns;
    ArrayList<Plant> playerCard;
    ArrayList<Bullet> bullets;
    transient VBox cards;
    public SaveData() {
            this.score = MapController.score;
            this.zombies = ZombieFactory.zombies;
            this.plants = GameMap.plants;
            this.suns = Sun.sunList;
            this.playerCard = ChooseCardController.cardPlants;
            this.bullets = PeaPlant.bulletList;
            this.cards = ChooseCardController.cards;
    }

}
