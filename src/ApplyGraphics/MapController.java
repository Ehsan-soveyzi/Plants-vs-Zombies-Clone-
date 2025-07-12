package ApplyGraphics;

import Character.KindsOfPlants.*;
import Character.KindsOfPlants.IceShroom;
import Character.KindsOfZombie.Zombie;
import GameServer.Client;
import GameServer.Server;
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
import javafx.scene.control.ProgressBar;
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

import java.io.IOException;
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
    @FXML
    private ProgressBar gameProgressBar;


    //static fields used between ui and backend.
    public static ZombieFactory zombieFactory;
    public static int waveCount = 1;
    public static Timeline gameLoop;
    public static long time = 0;
    public static int score = 10000;
    private final ImageView[][] fogView = new ImageView[5][5];


    ImageCursor cursor;
    Plant choosenPlant;
    boolean shovelUsed  = false;
    ArrayList<Plant> cardPlants;
    private final Pane[][] gridPanes = new Pane[5][9];

    @FXML
    public void initialize() {
        gameProgressBar.setScaleX(-1);
        GameMap.getInstance().initializeFog();
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
        createGrid();


        //main loop
        gameLoop = new Timeline(new KeyFrame(Duration.millis(100),e -> {
            gameProgressBar.setProgress(Zombie.NumberOfTotalZombies/57.0);
            if((gameProgressBar.getProgress() >= 1 && ZombieFactory.zombies.isEmpty()) || GameMain.winner) {
                try {
                    Server.sendEndMessage(1);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                PauseGameController.isWin = 1;
                pause();
            }
            if(!shovelUsed) paneWindow.setCursor(Cursor.DEFAULT);
            setOnMouseEntered();
            if (ModeController.getSelectedMode() == ModeController.Mode.NIGHT)Platform.runLater(this::applyFog);
            time += 100;
            if(time % 10000 == 0 && ModeController.getSelectedMode() == ModeController.Mode.DAY && time <= 120000) {
                try {
                    Sun.addToPane(paneWindow);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            GameMap.getInstance().checkWar();
            sunPoint.setText(Integer.toString(score));
            //apply zombies attack
            if(time % 1000 == 0 && time <= 120000){
                try {
                    handleZombiesWave1();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }

            try {
                int number = Server.winTheGame();
                if(number == 1){
                    GameMain.winner = true;
                }
                if (number == -1) {
                    GameMain.loser = true;
                }

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }));
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        gameLoop.playFromStart();


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
        else if(plant instanceof Repeater && Repeater.isReady)return new Repeater();
        else if(plant instanceof GraveBuster)return new GraveBuster();
        else if(plant instanceof Blover && Blover.isReady)return new Blover();
        else if(plant instanceof Plantern && Plantern.isReady)return new Plantern();
        else if(plant instanceof DoomShroom && DoomShroom.isReady){return new DoomShroom();}
        else if(plant instanceof HypnoShroom && HypnoShroom.isReady){return new HypnoShroom();}
        else if(plant instanceof ScaredyShroom && ScaredyShroom.isReady){return new ScaredyShroom();}
        else if(plant instanceof PuffShroom && PuffShroom.isReady){return new PuffShroom();}
        else if(plant instanceof IceShroom && IceShroom.isReady){return new IceShroom();}
        else if(plant instanceof CoffeeBean && CoffeeBean.isReady)return new CoffeeBean();
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
//                PauseGameController.pauseStage.initModality(Modality.APPLICATION_MODAL);
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
//                PauseGameController.pauseStage.initModality(Modality.APPLICATION_MODAL);
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
        }

            for(int i = 0 ; i < rows ; i++){
            for(int j = 0; j < cols; j++){
                Pane cell = new Pane();
                gridPanes[i][j] = cell;

                cell.setPrefSize(80, 80);
                if(j < 5)fogView[i][j] = new ImageView(new Image("/Images/resources/graphics/extentions/fog0.png"));

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

                    for(Plant plant : DoomShroom.explodeArea){
                        if(plant.getCol() == j && plant.getRow() == i){
                            cell.getChildren().add(new ImageView(new Image("/Images/resources/graphics/Plants/DoomShroom/square bomb.png")));
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
                System.out.println("shovel used!");
            }else if (choosenPlant instanceof CoffeeBean && GameMap.getInstance().isCellEmpty(row, col)) {
                System.out.println("coffeeBean can only used on Plants!");
            }else if(choosenPlant instanceof CoffeeBean && !GameMap.getInstance().getPlant(row, col).isShroom()){
                System.out.println("coffeeBean can only used on shrooms!");
            }
            else if(choosenPlant instanceof CoffeeBean && GameMap.getInstance().getPlant(row, col).isShroom() && !GameMap.getInstance().getPlant(row, col).isDay()){
                System.out.println("coffeeBean can used only on asleep shrooms!");
            }
            else if (!GameMap.getInstance().isCellEmpty(row, col) && !(choosenPlant instanceof CoffeeBean)) {
                System.out.println("cell is already not empty");
            } else if (choosenPlant == null){
                System.out.println("No plant selected");
            }
            else if(choosenPlant instanceof GraveBuster && !GameMap.getInstance().getGraved(row, col)){
                System.out.println("graveBooster must use on graves!");
            } else if (!(choosenPlant instanceof GraveBuster) && GameMap.getInstance().getGraved(row, col)){
                System.out.println("can't plant on graves!");
            } else if (!cell.getChildren().isEmpty() && GameMap.getInstance().isCellEmpty(row, col) && !GameMap.getInstance().getGraved(row, col)) {
                System.out.println("can't plant on exploded cell!");
            } else{
                cell.getChildren().add(choosenPlant.getImageView());
                GameMap.getInstance().addPlant(choosenPlant, row, col);
                GameMap.plants.add(choosenPlant);
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
            bullet.getTimeline().pause();
        }
        for(Sun sun : Sun.sunList){
            if(sun.getTimeline() != null)sun.getTimeline().pause();
            if(sun.getPause() != null)sun.getPause().pause();
        }
        for(Zombie zombie : ZombieFactory.zombies){
            if(zombie.getTimeline() != null)zombie.getTimeline().pause();
            if(zombie.getFreezeTimer() != null)zombie.getFreezeTimer().pause();
            if(zombie.getBiteTimeline() != null)zombie.getBiteTimeline().pause();
            if(zombie.getSlowTimer() != null)zombie.getSlowTimer().pause();
        }
        for(Zombie zombie : Zombie.hypnoZombie){
            if(zombie.getTimeline() != null)zombie.getTimeline().pause();
            if(zombie.getFreezeTimer() != null)zombie.getFreezeTimer().pause();
            if(zombie.getBiteTimeline() != null)zombie.getBiteTimeline().pause();
            if(zombie.getSlowTimer() != null)zombie.getSlowTimer().pause();
        }
        for(Plant plant : GameMap.getInstance().plants){
            if(plant.getTimeline() != null){
                plant.getTimeline().pause();
            }
        }
        //stop the cooldowns of each plant
        if(PeaShooter.cooldownTimeline != null) PeaShooter.cooldownTimeline.pause();
        if(Repeater.cooldownTimeline != null) Repeater.cooldownTimeline.pause();
        if(SnowPea.cooldownTimeline != null) SnowPea.cooldownTimeline.pause();
        if(SunFlower.cooldownTimeline != null) SunFlower.cooldownTimeline.pause();
        if(TallNut.cooldownTimeline != null) TallNut.cooldownTimeline.pause();
        if(WallNut.cooldownTimeline != null) WallNut.cooldownTimeline.pause();
        if(Jalapeno.cooldownTimeline != null) Jalapeno.cooldownTimeline.pause();
        if(CherryBomb.cooldownTimeline != null) CherryBomb.cooldownTimeline.pause();
        if(GraveBuster.cooldownTimeline != null) GraveBuster.cooldownTimeline.pause();
        if(ScaredyShroom.cooldownTimeline != null) ScaredyShroom.cooldownTimeline.pause();
        if(PuffShroom.cooldownTimeline != null) PuffShroom.cooldownTimeline.pause();
        if(IceShroom.cooldownTimeline != null) IceShroom.cooldownTimeline.pause();
        if(Blover.cooldownTimeline != null) Blover.cooldownTimeline.pause();
        if(Plantern.cooldownTimeline != null) Plantern.cooldownTimeline.pause();
        if(DoomShroom.cooldownTimeline != null)DoomShroom.cooldownTimeline.pause();
        if(HypnoShroom.cooldownTimeline != null)HypnoShroom.cooldownTimeline.pause();
    }

    private void handleZombiesWave1() throws IOException {
        long current = time / 1000;
        ArrayList<Zombie> types;

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
            int index = Server.generateRandom(types.size());
            int lane = Server.generateRandom(5);
            zombieFactory.createZombie(types.get(index), lane);

        }
    }
    public void applyFog(){
        for(int i = 0; i < 5;i++){
            for(int j = 5; j < 9;j++){
                fogView[i][j - 5].setMouseTransparent(true);
                fogView[i][j - 5].setFitWidth(400);
                fogView[i][j - 5].setFitHeight(400);
                if(GameMap.getInstance().getFog(i,j) && !paneWindow.getChildren().contains(fogView[i][j - 5])) {
                    fogView[i][j - 5].setOpacity(0.8);
                    paneWindow.getChildren().add(fogView[i][j - 5]);
                    fogView[i][j - 5].setLayoutX(gridPanes[i][j].localToScreen(gridPanes[i][j].getBoundsInLocal()).getMinX());
                    fogView[i][j - 5].setLayoutY(gridPanes[i][j].localToScreen(gridPanes[i][j].getBoundsInLocal()).getMinY() - 200);
                    fogView[i][j - 5].toFront();
                    fogView[i][j - 5].setViewOrder(-1);
                }
                else if(!GameMap.getInstance().getFog(i,j)) {
                    paneWindow.getChildren().remove(fogView[i][j - 5]);
                }
            }
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