package ApplyGraphics;

import Character.KindsOfPlants.*;
import Character.KindsOfZombie.Zombie;
import Map.GameMap;
import Map.ZombieFactory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.IOException;
import Character.*;

public class PauseGameController {
    @FXML
    private ImageView home;
    @FXML
    private ImageView refresh;
    @FXML
    private ImageView resume;

    public static Stage pauseStage;

    @FXML
    private void initialize(){
        MainMenuController.animateImage(home);
        MainMenuController.animateImage(refresh);
        MainMenuController.animateImage(resume);
        home.setOnMouseClicked(event -> {
            homeButtonClicked();
        });
        refresh.setOnMouseClicked(event -> {
            refreshButtonClicked();
        });
        resume.setOnMouseClicked(event -> {
           resumeButtonClicked();
        });
    }

    public void homeButtonClicked(){
        try {
            clearData();
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
        ZombieFactory.zombies.clear();
        PeaShooter.bulletList.clear();
        GameMap.plants.clear();
        MapController.map.refreshPlants();
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
            if(zombie.getTimeline() != null)zombie.getTimeline().play();
            if(zombie.getBiteTimeline() != null)zombie.getBiteTimeline().play();
            if(zombie.getSlowTimer() != null)zombie.getSlowTimer().play();
        }
        for(Plant plant : GameMap.plants){
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
    }


}
