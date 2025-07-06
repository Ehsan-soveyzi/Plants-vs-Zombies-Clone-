package ApplyGraphics;

import Character.KindsOfPlants.*;
import Character.KindsOfPlants.IceShroom;
import Character.KindsOfZombie.Zombie;
import Map.GameMap;
import Map.ZombieFactory;
import Save_Logic.SaveGame;
import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.IOException;
import java.io.Serializable;
import Map.Grave;

import Character.*;

public class PauseGameController implements Serializable {
    @FXML
    private ImageView home;
    @FXML
    private ImageView refresh;
    @FXML
    private ImageView resume;
    @FXML
    private ImageView saveButton;
    @FXML
    private Label infoLabel;

    public static Stage pauseStage;
    public static int isWin = 0;

    @FXML
    private void initialize() {
        if(isWin == 1)infoLabel.setText("You Win!");
        if(isWin == -1)infoLabel.setText("You Lose!");
        MainMenuController.animateImage(home);
        MainMenuController.animateImage(refresh);
        mouseEnteredAction();
        home.setOnMouseClicked(event -> {
            homeButtonClicked();
        });
        refresh.setOnMouseClicked(event -> {
            refreshButtonClicked();
        });
        if(isWin == 0) {
            MainMenuController.animateImage(resume);
            MainMenuController.animateImage(saveButton);
            resume.setOnMouseClicked(event -> {
                resumeButtonClicked();
            });
            saveButton.setOnMouseClicked(event -> {
                SaveGame.saveGame();
                infoLabel.setText("Game saved!");
            });
        }
    }

    public void homeButtonClicked(){
        try {
            clearData();
            ChooseCardController.cardPlants.clear();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            GameMain.mainStage.setScene(scene);
            GameMain.mainStage.setFullScreen(true);
            GameMain.mainStage.show();
            pauseStage.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void mouseEnteredAction(){
        home.setOnMouseEntered(event -> {
            infoLabel.setText("home Screen");
        });
        home.setOnMouseExited(event -> {
            infoLabel.setText("");
        });
        refresh.setOnMouseEntered(event -> {
            infoLabel.setText("refresh Screen");
        });
        refresh.setOnMouseExited(event -> {
            infoLabel.setText("");
        });
        if(isWin == 0) {
            resume.setOnMouseEntered(event -> {
                infoLabel.setText("resume to the game");
            });
            resume.setOnMouseExited(event -> {
                infoLabel.setText("");
            });
            saveButton.setOnMouseEntered(event -> {
                infoLabel.setText("Save Game");
            });
            saveButton.setOnMouseExited(event -> {
                infoLabel.setText("");
            });
        }
    }

    public void refreshButtonClicked(){
        try{
            clearData();
            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("Map.fxml"));
            Parent root = fxmlloader.load();
            Scene scene = new Scene(root);
            GameMain.mainStage.setScene(scene);
            GameMain.mainStage.setFullScreen(true);
            GameMain.mainStage.show();
            pauseStage.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void resumeButtonClicked(){
        startTimelines();
        pauseStage.close();
        MapController.gameLoop.play();
    }

    public void clearData(){
        resetCooldowns();
        MapController.score = 10000;
        MapController.waveCount = 1;
        MapController.time = 0;
        ZombieFactory.zombies.clear();
        Zombie.hypnoZombie.clear();
        PeaShooter.bulletList.clear();
        Grave.graves.clear();
        DoomShroom.explodeArea.clear();
        GameMap.getInstance().cleanGrave();
        GameMap.getInstance().plants.clear();
        GameMap.getInstance().refreshPlants();
        Sun.sunList.clear();
        isWin = 0;
        startTimelines();
    }
    public void resetCooldowns(){
        SunFlower.isReady = true;
        CherryBomb.isReady = true;
        Jalapeno.isReady = true;
        Blover.isReady = true;
        CoffeeBean.isReady = true;
        DoomShroom.isReady = true;
        HypnoShroom.isReady = true;
        IceShroom.isReady = true;
        PeaShooter.isReady = true;
        Repeater.isReady = true;
        SnowPea.isReady = true;
        Plantern.isReady = true;
        ScaredyShroom.isReady = true;
        PuffShroom.isReady = true;
        WallNut.isReady = true;
        TallNut.isReady = true;
        GraveBuster.isReady = true;
    }

    public void startTimelines(){

        for(Bullet bullet : PeaPlant.bulletList)if(bullet.getTimeline() != null){
            bullet.getTimeline().play();
        }

        for(Sun sun : Sun.sunList){
            if(sun.getTimeline() != null)sun.getTimeline().play();
            if(sun.getPause() != null)sun.getPause().play();
        }

        for(Zombie zombie : ZombieFactory.zombies){
            if(zombie.getTimeline() != null && !zombie.isFreezed())zombie.getTimeline().play();
            if(zombie.getBiteTimeline() != null && !zombie.isFreezed())zombie.getBiteTimeline().play();
            if(zombie.getSlowTimer() != null)zombie.getSlowTimer().play();
            if(zombie.getFreezeTimer() != null)zombie.getFreezeTimer().play();
        }

        for(Zombie zombie : Zombie.hypnoZombie){
            if(zombie.getTimeline() != null && !zombie.isFreezed())zombie.getTimeline().play();
            if(zombie.getBiteTimeline() != null && !zombie.isFreezed())zombie.getBiteTimeline().play();
            if(zombie.getSlowTimer() != null)zombie.getSlowTimer().play();
            if(zombie.getFreezeTimer() != null)zombie.getFreezeTimer().play();
        }

        for(Plant plant : GameMap.getInstance().plants){
            if(plant.getTimeline() != null){
                plant.getTimeline().play();
            }
        }

        if(PeaShooter.cooldownTimeline != null) PeaShooter.cooldownTimeline.play();
        if(Repeater.cooldownTimeline != null) Repeater.cooldownTimeline.play();
        if(SnowPea.cooldownTimeline != null) SnowPea.cooldownTimeline.play();
        if(SunFlower.cooldownTimeline != null) SunFlower.cooldownTimeline.play();
        if(TallNut.cooldownTimeline != null) TallNut.cooldownTimeline.play();
        if(WallNut.cooldownTimeline != null) WallNut.cooldownTimeline.play();
        if(Jalapeno.cooldownTimeline != null) Jalapeno.cooldownTimeline.play();
        if(CherryBomb.cooldownTimeline != null) CherryBomb.cooldownTimeline.play();
        if(Plantern.cooldownTimeline != null) Plantern.cooldownTimeline.play();
        if(GraveBuster.cooldownTimeline != null) GraveBuster.cooldownTimeline.play();
        if(Blover.cooldownTimeline != null) Blover.cooldownTimeline.play();
    }


}
