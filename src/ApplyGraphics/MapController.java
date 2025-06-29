package ApplyGraphics;

import Character.KindsOfPlants.*;
import Character.KindsOfZombie.Zombie;
import Map.GameMap;
import Map.Grave;
import Map.ZombieFactory;
import Save_Logic.SaveGame;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import Character.*;
import Character.KindsOfZombie.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import Character.Sun;
import java.util.ArrayList;
import java.util.Random;

public class MapController {

    @FXML
    private Pane paneWindow;
    @FXML
    private GridPane gridPane;
    @FXML
    private ImageView shovel;
    @FXML
    private Label sunPoint;
    @FXML
    private ImageView menu;


    //static fields used between ui and backend.
    public static ZombieFactory zombieFactory;
    public static int waveCount = 1;
    public static Timeline gameLoop;
    public static long time = 0;
    public static int score = 10000;


    ImageCursor cursor;
    Plant choosenPlant;
    boolean shovelUsed  = false;
    ArrayList<Plant> cardPlants;

    @FXML
    public void initialize() {
        zombieFactory = new ZombieFactory(paneWindow);

        //check if user enter to this part by loading or playButton.
        if(SaveGame.load && OptionController.clicked == 1) {
            SaveGame.loadGame();
            for(Bullet bullet:PeaPlant.bulletList)bullet.addToPane(paneWindow);
            for(Sun sun:Sun.sunList){
                sun.applyTimelineRemaining();
                sun.sunCollector();
                paneWindow.getChildren().add(sun.getImageView());
                sun.getImageView().setOnMouseClicked(event -> {
                    sun.addSun();
                    sun.removeSun();
                });
            }
        }

        //set the night or day.
        if(ModeController.getSelectedMode() == ModeController.Mode.NIGHT)paneWindow.setStyle("-fx-background-image: url('/Images/resources/graphics/Background/Night.jpg'); -fx-background-size: 1550px 865px;");
        else paneWindow.setStyle("-fx-background-image: url('/Images/resources/graphics/Background/Day1.jpg'); -fx-background-size: 1550px 865px;");

        paneWindow.getChildren().add(ChooseCardController.cards);
        ChooseCardController.cards.setLayoutX(50);
        cardPlants = ChooseCardController.cardPlants;

        mouseEvents();


        //main loop
        gameLoop = new Timeline(new KeyFrame(Duration.millis(100),e -> {
            if(!shovelUsed) paneWindow.setCursor(Cursor.DEFAULT);
            setOnMouseEntered();
            time += 100;
            if(time % 10000 == 0 && ModeController.getSelectedMode() == ModeController.Mode.DAY && time <= 120000)Sun.addToPane(paneWindow);
            GameMap.getInstance().checkWar();
            sunPoint.setText(Integer.toString(score));
            //apply zombies attack
            if(time % 1000 == 0 && time <= 120000){
                handleZombiesWave1();
            }
        }));
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        gameLoop.playFromStart();

        createGrid();

        MainMenuController.animateImage(menu);
        menu.setOnMouseClicked(event -> {
            if(PauseGameController.pauseStage == null ) {
                pause();
            }
            else{
                if(!PauseGameController.pauseStage.isShowing())pause();
            }
        });
        OptionController.clicked = -1;
    }

    //check what the card chosen from the user
    public Plant checkChosenCard(Plant plant){
        if(plant instanceof PeaShooter && PeaShooter.isReady)return new PeaShooter();
        else if(plant instanceof SnowPea && SnowPea.isReady)return new SnowPea();
        else if(plant instanceof SunFlower && SunFlower.isReady)return new SunFlower();
        else if(plant instanceof WallNut && WallNut.isReady)return new WallNut();
        else if(plant instanceof Jalapeno && Jalapeno.isReady)return new Jalapeno();
        else if(plant instanceof CherryBomb && CherryBomb.isReady)return new CherryBomb();
        else if(plant instanceof TallNut && TallNut.isReady)return new TallNut();
        else if (plant instanceof Repeater && Repeater.isReady)return new Repeater();
        else if(plant instanceof GraveBuster)return new GraveBuster();
        return null;
    }

    public void mouseEvents(){
        for(int i = 0; i < ChooseCardController.cards.getChildren().size(); i++){
            ImageView imageView = (ImageView) ChooseCardController.cards.getChildren().get(i);
            int finalI = i;
            imageView.setOnMouseClicked(e -> {
                if(score >= cardPlants.get(finalI).getCost() &&  checkChosenCard(cardPlants.get(finalI)) != null){
                    shovelUsed = false;
                    choosenPlant = checkChosenCard(cardPlants.get(finalI));
                }
            });
        }
        shovel.setOnMouseClicked(event -> {
            shovelUsed = true;
            cursor = new ImageCursor(new Image("/Images/resources/graphics/extentions/showel.gif"));
            paneWindow.setCursor(cursor);
            choosenPlant = null;
        });
    }


    public static void pause(){
        //stop timelines
        stopTheGame();
        gameLoop.stop();
        if(PauseGameController.isWin == 0) {
            try {
                PauseGameController.pauseStage = new Stage();
                FXMLLoader fxmlLoader = new FXMLLoader(MapController.class.getResource("Pause.fxml"));
                Parent root = fxmlLoader.load();
                Scene scene = new Scene(root);
                PauseGameController.pauseStage.setScene(scene);
                PauseGameController.pauseStage.setResizable(false);
                PauseGameController.pauseStage.initOwner(GameMain.mainStage);
                PauseGameController.pauseStage.initModality(Modality.APPLICATION_MODAL);
                PauseGameController.pauseStage.initStyle(StageStyle.UNDECORATED);
                PauseGameController.pauseStage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            try {
                PauseGameController.pauseStage = new Stage();
                FXMLLoader fxmlLoader = new FXMLLoader(MapController.class.getResource("winLose.fxml"));
                Parent root = fxmlLoader.load();
                Scene scene = new Scene(root);
                PauseGameController.pauseStage.setScene(scene);
                PauseGameController.pauseStage.setResizable(false);
                PauseGameController.pauseStage.initOwner(GameMain.mainStage);
                PauseGameController.pauseStage.initModality(Modality.APPLICATION_MODAL);
                PauseGameController.pauseStage.initStyle(StageStyle.UNDECORATED);
                PauseGameController.pauseStage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setOnMouseEntered(){
        for (Node node : gridPane.getChildren()) {
            node.setOnMouseEntered(event -> {
                if (choosenPlant != null) {
                    Pane cell = (Pane) node;

                    boolean hasImageView = false;
                    for (Node child : cell.getChildren()) {
                        if (child instanceof ImageView) {
                            hasImageView = true;
                            break;
                        }
                    }
                    //css styles
                    if (hasImageView) {
                        cell.setStyle("-fx-background-color: rgba(255, 0, 0, 0.5);");
                    } else {
                        cell.setStyle("-fx-background-color: rgba(255, 255, 100, 0.5);");
                    }
                }
            });

            node.setOnMouseExited(event -> {
                ((Pane) node).setStyle("");
            });
        }

    }


    public void createGrid(){
        final int rows = gridPane.getRowCount();
        final int cols = gridPane.getColumnCount();

        if(ModeController.getSelectedMode() == ModeController.Mode.NIGHT && OptionController.clicked == -1) {
            GameMap.getInstance().generateGrave();
            System.out.println("are you fucking keeding me !");
        }

            for(int i = 0 ; i < rows ; i++){
            for(int j = 0; j < cols; j++){
                Pane cell = new Pane();

                cell.setPrefSize(80, 80);

                    //adding graves!
                    for(Grave grave : Grave.graves){
                        if(grave.getCol() == j && grave.getRow() == i){
                            cell.getChildren().add(grave.getImageView());
                            GameMap.getInstance().setGraved(grave.getRow(), grave.getCol(), true);
                            Platform.runLater(() -> {
                                Bounds bounds = grave.getImageView().localToScene(grave.getImageView().getBoundsInLocal());
                                grave.setX(bounds.getMinX());
                                grave.setY(bounds.getMinY());
                                System.out.println(grave.getCol());
                                grave.getImageView().setLayoutX(5);
                                grave.getImageView().setLayoutY(25);
                            });

                        }
                    }

                    //this added when loading action
                    for(Plant plant : GameMap.getInstance().plants){
                        if(plant.getRow() == i && plant.getCol() == j){
                            cell.getChildren().add(plant.getImageView());
                            GameMap.getInstance().addPlant(plant, i, j);
                            plant.updateImageSituation(paneWindow);
                        }
                    }
                setOnCell(cell, i, j);


                gridPane.add(cell, j, i);
            }
        }
    }

    public void setOnCell(Pane cell,int row,int col){
        cell.setOnMouseClicked(event -> {
            if(!GameMap.getInstance().isCellEmpty(row, col) && shovelUsed){
                //using shovel
                shovel(row, col);
                System.out.println("cell is already not empty");
            } else if (!GameMap.getInstance().isCellEmpty(row, col)) {
                System.out.println("cell is already not empty");
            } else if (choosenPlant == null ){
                System.out.println("No plant selected");
            }
            else if(choosenPlant instanceof GraveBuster && !GameMap.getInstance().getGraved(row, col)){
                System.out.println("graveBooster must use on graves!");
            } else if (!(choosenPlant instanceof GraveBuster) && GameMap.getInstance().getGraved(row, col)){
                System.out.println("can't plant on graves!");
            } else{
                cell.getChildren().add(choosenPlant.getImageView());
                GameMap.getInstance().addPlant(choosenPlant, row, col);
                GameMap.getInstance().plants.add(choosenPlant);
                choosenPlant.setX(choosenPlant.getImageView().localToScreen(choosenPlant.getImageView().getBoundsInLocal()).getMinX());
                choosenPlant.setY(choosenPlant.getImageView().localToScreen(choosenPlant.getImageView().getBoundsInLocal()).getMinY());
                choosenPlant.setRow(row);
                choosenPlant.setRow(row);
                choosenPlant.setCol(col);
                choosenPlant.updateImageSituation(paneWindow);
                score -= choosenPlant.getCost();
                choosenPlant = null;
                shovelUsed = false;
            }
        });
    }


    public void shovel(int row, int col){
        choosenPlant = null;
        GameMap.getInstance().removePlant(row, col);
        shovelUsed = false;
    }

    //apply when pausing the game for stop the timelines!
    public static void stopTheGame(){
        for(Bullet bullet : PeaPlant.bulletList)if(bullet.getTimeline() != null){
            bullet.getTimeline().stop();
        }
        for(Sun sun : Sun.sunList){
            if(sun.getTimeline() != null)sun.getTimeline().stop();
            if(sun.getPause() != null)sun.getPause().stop();
        }
        for(Zombie zombie : ZombieFactory.zombies){
            if(zombie.getTimeline() != null)zombie.getTimeline().stop();
            if(zombie.getBiteTimeline() != null)zombie.getBiteTimeline().stop();
            if(zombie.getSlowTimer() != null)zombie.getSlowTimer().stop();
        }
        for(Plant plant : GameMap.getInstance().plants){
            if(plant.getTimeline() != null){
                plant.getTimeline().stop();
            }
        }
        //stop the cooldowns of each plant
        if(PeaShooter.cooldownTimeline != null) PeaShooter.cooldownTimeline.stop();
        if(Repeater.cooldownTimeline != null) Repeater.cooldownTimeline.stop();
        if(SnowPea.cooldownTimeline != null) SnowPea.cooldownTimeline.stop();
        if(SunFlower.cooldownTimeline != null) SunFlower.cooldownTimeline.stop();
        if(TallNut.cooldownTimeline != null) TallNut.cooldownTimeline.stop();
        if(WallNut.cooldownTimeline != null) WallNut.cooldownTimeline.stop();
        if(Jalapeno.cooldownTimeline != null) Jalapeno.cooldownTimeline.stop();
        if(CherryBomb.cooldownTimeline != null) CherryBomb.cooldownTimeline.stop();
    }

    private void handleZombiesWave1() {
        long current = time / 1000;
        ArrayList<Zombie> types;
        Random rand = new Random();

        // choose zombies depending on the time
        boolean normal = true;
        boolean conehead = current >= 20;
        boolean screendoor = current >= 60;
        boolean imp = current >= 80;
        // make an array for possible zombies
        types = possibleZombie(normal, conehead, screendoor, imp);

        boolean isBreakTime = (current >= 45 && current < 50) || (current >= 90 && current < 100);
        boolean isStrongAttack = (current >= 50 && current < 60) || (current >= 100 && current < 120);

        if (isBreakTime) {
            return;
        }
        int numberOfZombies;

        if (isStrongAttack) {
            if (current % 3 != 0) return;
            numberOfZombies = 2 + (int)(current / 35);
            if(ModeController.getSelectedMode() == ModeController.Mode.NIGHT)GameMap.getInstance().zombieGraveAttack();
        }
        else {
            if (current % 6 != 0) return;
            numberOfZombies = 1 + (int)(current / 60);

        }

        for (int i = 0; i < numberOfZombies; i++) {
            int index = rand.nextInt(types.size());
            int lane = rand.nextInt(5);
            zombieFactory.createZombie(types.get(index), lane);

        }
    }

    private ArrayList<Zombie> possibleZombie(boolean regular, boolean coneHead, boolean screenDoor, boolean imp) {
        ArrayList<Zombie> zombies = new ArrayList<>();
        if (regular) zombies.add(new Regular(1));
        if (coneHead) zombies.add(new ConeHead(1));
        if (screenDoor) zombies.add(new ScreenDoorZombie(1));
        if (imp) zombies.add(new IMPZombie(1));
        return zombies;
    }

}