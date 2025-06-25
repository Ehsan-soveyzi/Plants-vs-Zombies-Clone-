package ApplyGraphics;

import Character.KindsOfPlants.*;
import Character.KindsOfZombie.Zombie;
import Map.GameMap;
import Map.ZombieFactory;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import Character.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import Character.Sun;
import java.util.ArrayList;
import java.util.Random;

public class MapController {

    @FXML
    public Pane paneWindow;
    @FXML
    private GridPane gridPane;
    @FXML
    private ImageView shovel;
    @FXML
    private Label sunPoint;
    @FXML
    private ImageView menu;




    public static GameMap map = new GameMap();
    public static ZombieFactory zombieFactory;
    static int waveCount = 1;
    static Timeline gameLoop;
    long time;
    ImageCursor cursor;
    Plant choosenPlant;
    private final VBox playerCards = ChooseCardController.cards;
    boolean shovelUsed  = false;

    public static int score;

    ArrayList<Plant> cardPlants = ChooseCardController.cardPlants;

    @FXML
    public void initialize() {
        if(ModeController.getSelectedMode() == ModeController.Mode.NIGHT)paneWindow.setStyle("-fx-background-image: url('/Images/resources/graphics/Items/Background/Night.jpg'); -fx-background-size: 1550px 865px;");
        else paneWindow.setStyle("-fx-background-image: url('/Images/resources/graphics/Items/Background/Day1.jpg'); -fx-background-size: 1550px 865px;");
        score = 1000;
        waveCount = 1;
        time = 0;
        MainMenuController.animateImage(menu);
        menu.setOnMouseClicked(event -> {
            if(PauseGameController.pauseStage == null ) {
                pause();
            }
            else{
                if(!PauseGameController.pauseStage.isShowing())pause();
            }
        });
        paneWindow.getChildren().add(playerCards);
        playerCards.setLayoutX(50);
        mouseEvents();

        zombieFactory = new ZombieFactory(paneWindow);

        for(Plant plant : GameMap.plants) {
            map.addPlant(plant, plant.getRow(), plant.getCol());
            plant.updateImageSituation(paneWindow);
            if(!paneWindow.getChildren().contains(plant.getImageView())) {
                for(int i = 0;i < gridPane.getRowCount();i++){
                    for(int j = 0;j < gridPane.getColumnCount();j++){
                        if(plant.getRow() == i && plant.getCol() == j) {
                            gridPane.getChildren().add(plant.getImageView());
                        }
                    }
                }
            }
        }


        gameLoop = new Timeline(new KeyFrame(Duration.millis(100),e -> {
            if(choosenPlant == null && !shovelUsed) paneWindow.setCursor(Cursor.DEFAULT);
            setOnMouseEntered();
            time += 100;
            if(time % 10000 == 0 && ModeController.getSelectedMode() == ModeController.Mode.DAY)Sun.addToPane(paneWindow);
            map.checkWar();
            sunPoint.setText(Integer.toString(score));
            if(time % 10000 == 0)attackOne();
            if(time % 20000 == 0)waveCount++;
        }));

        gameLoop.setCycleCount(Timeline.INDEFINITE);
        gameLoop.playFromStart();

        createGrid();
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
        return null;
    }

    public void mouseEvents(){
        for(int i = 0; i < playerCards.getChildren().size(); i++){
            ImageView imageView = (ImageView) playerCards.getChildren().get(i);
            int finalI = i;
            imageView.setOnMouseClicked(e -> {
                if(score >= cardPlants.get(finalI).getCost() &&  checkChosenCard(cardPlants.get(finalI)) != null){
                    shovelUsed = false;
                    choosenPlant = checkChosenCard(cardPlants.get(finalI));
                    if (choosenPlant != null)setCursorImage(choosenPlant);
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
        try{
            stopTheGame();
            gameLoop.stop();
            PauseGameController.pauseStage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(MapController.class.getResource("Pause.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            PauseGameController.pauseStage.setScene(scene);
            PauseGameController.pauseStage.setResizable(false);
            PauseGameController.pauseStage.initOwner(GameMain.mainStage);
            PauseGameController.pauseStage.initStyle(StageStyle.UNDECORATED);
            PauseGameController.pauseStage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void setCursorImage(Plant plant) {
        cursor = new ImageCursor(plant.getImageView().getImage());
        paneWindow.setCursor(cursor);
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

        for(int i = 0 ; i < rows ; i++){
            for(int j = 0; j < cols; j++){
                Pane cell = new Pane();

                cell.setPrefSize(80, 80);

                setOnCell(cell, i, j);

                gridPane.add(cell, j, i);
            }
        }
    }


    public void setOnCell(Pane cell,int row,int col){
        cell.setOnMouseClicked(event -> {
            if(!map.isCellEmpty(row, col) && shovelUsed){
                //using shovel
                shovel(row, col);
                System.out.println("cell is already not empty");
            } else if (!map.isCellEmpty(row, col)) {
                System.out.println("cell is already not empty");
            } else if (choosenPlant == null ){
                System.out.println("No plant selected");
            }
            else{
                cell.getChildren().add(choosenPlant.getImageView());
                map.addPlant(choosenPlant, row, col);
                choosenPlant.setX(choosenPlant.getImageView().localToScreen(choosenPlant.getImageView().getBoundsInLocal()).getMinX());
                choosenPlant.setY(choosenPlant.getImageView().localToScreen(choosenPlant.getImageView().getBoundsInLocal()).getMinY());
                choosenPlant.setRow(row);
                System.out.println(choosenPlant.getRow());
                System.out.println(choosenPlant.getX() + " ..." + choosenPlant.getY());
                choosenPlant.updateImageSituation(paneWindow);
                choosenPlant.setRow(row);
                choosenPlant.setCol(col);
                score += choosenPlant.getCost();
                choosenPlant = null;

                shovelUsed = false;
            }
        });
    }

    public void attackOne(){
        for (int i = 1; i <= waveCount; i++) {
            chooseRandomZombie(i);
        }
    }


    public void shovel(int row, int col){
        choosenPlant = null;
        map.removePlant(row, col);
        shovelUsed = false;
    }

    public void chooseRandomZombie(int wave){
        Random rand = new Random();
        int randomRow = rand.nextInt(5);
        int number = rand.nextInt(wave);
        if(number <= 4)zombieFactory.createRegularZombie(randomRow,1500);
        else if(number <= 7)zombieFactory.createConeHeadZombie(randomRow,1500);
        else if(number <= 9)zombieFactory.createScreenDoorZombie(randomRow,1500);
        else zombieFactory.createIMPZombie(randomRow,1500);
    }

    //apply when pausing the game fot stop the timelines!
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
        for(Plant plant : GameMap.plants){
            if(plant.getTimeline() != null){
                plant.getTimeline().stop();
            }
        }
        if(PeaShooter.cooldownTimeline != null) PeaShooter.cooldownTimeline.stop();
        if(Repeater.cooldownTimeline != null) Repeater.cooldownTimeline.stop();
        if(SnowPea.cooldownTimeline != null) SnowPea.cooldownTimeline.stop();
        if(SunFlower.cooldownTimeline != null) SunFlower.cooldownTimeline.stop();
        if(TallNut.cooldownTimeline != null) TallNut.cooldownTimeline.stop();
        if(WallNut.cooldownTimeline != null) WallNut.cooldownTimeline.stop();
        if(Jalapeno.cooldownTimeline != null) Jalapeno.cooldownTimeline.stop();
        if(CherryBomb.cooldownTimeline != null) CherryBomb.cooldownTimeline.stop();
    }

}