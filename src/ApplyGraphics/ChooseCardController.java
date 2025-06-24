package ApplyGraphics;

import Character.KindsOfPlants.*;
import javafx.application.Platform;
import Character.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;

public class ChooseCardController {

    @FXML
    private GridPane cardList;
    @FXML
    private VBox playerCards;
    @FXML
    private Label cardLabel;
    @FXML
    private ImageView playButton;
    @FXML
    private ImageView SunFlower;
    @FXML
    private ImageView Peashooter;
    @FXML
    private ImageView Repeater;
    @FXML
    private ImageView Snowpea;
    @FXML
    private ImageView WallNut;
    @FXML
    private ImageView TallNut;
    @FXML
    private ImageView CherryBomb;
    @FXML
    private ImageView Jalapeno;
    @FXML
    private ImageView BackButton;

    public static VBox cards;
    public static ArrayList<Plant> cardPlants = new ArrayList<>();


    @FXML
    public void initialize() {
        cardPlants.clear();
        chooseCard();
        cards = playerCards;
        MainMenuController.animateImage(BackButton);

        BackButton.setOnMouseClicked(event -> {
            backButton();
        });
    }
    public void backButton(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Mode.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            GameMain.setFadeTransition(root);
            GameMain.mainStage.setScene(scene);
            GameMain.mainStage.setFullScreen(true);
            GameMain.mainStage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void chooseCard(){
        SunFlower sunFlower = new SunFlower();
        PeaShooter peaShooter = new PeaShooter();
        Repeater repeater = new Repeater();
        SnowPea snowPea = new SnowPea();
        WallNut wallNut = new WallNut();
        TallNut tallNut = new TallNut();
        CherryBomb cherryBomb = new CherryBomb();
        Jalapeno jalapeno = new Jalapeno();

        SunFlower.setOnMouseClicked(e -> {
            if(!playerCards.getChildren().contains(SunFlower)){
                cardPlants.add(sunFlower);
                selectCard(SunFlower);
            }
            else{
                cardPlants.remove(sunFlower);
                removeCard(SunFlower);
            }

        });
        Peashooter.setOnMouseClicked(e -> {
            if(!playerCards.getChildren().contains(Peashooter)){
                cardPlants.add(peaShooter);
                selectCard(Peashooter);
            }
            else{
                cardPlants.remove(peaShooter);
                removeCard(Peashooter);
            }
        });
        Repeater.setOnMouseClicked(e -> {
            if(!playerCards.getChildren().contains(Repeater)){
                cardPlants.add(repeater);
                selectCard(Repeater);
            }
            else{
                cardPlants.remove(repeater);
                removeCard(Repeater);
            }
        });
        Snowpea.setOnMouseClicked(e -> {
            if(!playerCards.getChildren().contains(Snowpea)){
                cardPlants.add(snowPea);
                selectCard(Snowpea);
            }
            else{
                cardPlants.remove(snowPea);
                removeCard(Snowpea);
            }
        });
        WallNut.setOnMouseClicked(e -> {
            if(!playerCards.getChildren().contains(WallNut)){
                cardPlants.add(wallNut);
                selectCard(WallNut);
            }
            else{
                cardPlants.remove(wallNut);
                removeCard(WallNut);
            }
        });
        TallNut.setOnMouseClicked(e -> {
            if(!playerCards.getChildren().contains(TallNut)){
                cardPlants.add(tallNut);
                selectCard(TallNut);
            }
            else{
                cardPlants.remove(tallNut);
                removeCard(TallNut);
            }
        });
        CherryBomb.setOnMouseClicked(e -> {
            if(!playerCards.getChildren().contains(CherryBomb)){
                cardPlants.add(cherryBomb);
                selectCard(CherryBomb);
            }
            else{
                cardPlants.remove(cherryBomb);
                removeCard(CherryBomb);
            }
        });
        Jalapeno.setOnMouseClicked(e -> {
            if(!playerCards.getChildren().contains(Jalapeno)){
                cardPlants.add(jalapeno);
                selectCard(Jalapeno);
            }
            else{
                cardPlants.remove(jalapeno);
                removeCard(Jalapeno);
            }
        });
    }


    public void selectCard(ImageView imageView) {
        System.out.println(playerCards.getChildren().size());
        if (!playerCards.getChildren().contains(imageView) && playerCards.getChildren().size() <= 5) {
            playerCards.getChildren().add(imageView);
//            cardList.getChildren().remove(imageView);
            cardLabel.setText(playerCards.getChildren().size() + " card chosen!");
        }
        if(playerCards.getChildren().size() == 6) {
            cardLabel.setText("you at most can choose 6 card! let's play ...");
            MainMenuController.animateImage(playButton);
            playButton.setOnMouseClicked(event -> {
                if(playerCards.getChildren().size() == 6)
                    enterGame();
            });
        }
    }
    public void removeCard(ImageView imageView) {
        System.out.println(playerCards.getChildren().size());
        playerCards.getChildren().remove(imageView);
        cardList.getChildren().add(imageView);
        cardLabel.setText(playerCards.getChildren().size() + " card chosen!");
    }

    public void enterGame() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Map.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            GameMain.setFadeTransition(root);
            GameMain.mainStage.setScene(scene);
            GameMain.mainStage.setFullScreen(true);
            GameMain.mainStage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
