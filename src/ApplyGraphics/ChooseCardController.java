package ApplyGraphics;

import Character.KindsOfPlants.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.util.ArrayList;

public class ChooseCardController {

    @FXML
    private GridPane gridPane;
    @FXML
    private VBox playerCards;
    @FXML
    private Label cardLabel;
    @FXML
    private ImageView playButton;
    @FXML
    private ImageView BackButton;

    //the reason for using these static vbox and gridPane fields for transferring data to another class.
    public static VBox cards = new VBox();
    public static GridPane cardList = new GridPane();

    public static ArrayList<Plant> cardPlants = new ArrayList<>();

    //this use to get the cardView images for each plant.
    public static final Plant[] plants = {new SunFlower(),new PeaShooter(),new Repeater(),new SnowPea(),new CherryBomb()
            ,new Jalapeno(),new TallNut(),new WallNut(),new PuffShroom(),new IceShroom(),new ScaredyShroom()};


    @FXML
    public void initialize() {
        cardList = gridPane;
        cards = playerCards;

        createGrid();

        chooseCard();

        MainMenuController.animateImage(BackButton);

        BackButton.setOnMouseClicked(event -> {
            backButton();
        });
    }
    //adding imageViews to the grid
    public void createGrid(){
        int counter = 0;
            for (int i = 0; i < cardList.getRowCount(); i++) {
                for (int j = 0; j < cardList.getColumnCount() && plants.length > counter; j++) {
                    double width = cardList.getWidth();
                    double height = cardList.getHeight();
                    plants[counter].getCardView().setFitWidth(width);
                    plants[counter].getCardView().setFitHeight(height);
                    cardList.add(plants[counter].getCardView(), i, j);
                    counter++;
                }
            }
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
        //define these objects for use their images and ...
        SunFlower sunFlower = (SunFlower) plants[0];
        PeaShooter peaShooter = (PeaShooter) plants[1];
        Repeater repeater = (Repeater) plants[2];
        SnowPea snowPea = (SnowPea) plants[3];
        WallNut wallNut = (WallNut) plants[7];
        TallNut tallNut = (TallNut) plants[6];
        CherryBomb cherryBomb = (CherryBomb) plants[4];
        Jalapeno jalapeno = (Jalapeno) plants[5];
        PuffShroom puffShroom = (PuffShroom) plants[8];
        IceShroom iceShroom = (IceShroom) plants[9];
        ScaredyShroom scaredyShroom = (ScaredyShroom) plants[10];

        sunFlower.getCardView().setOnMouseClicked(e -> {
            if(!playerCards.getChildren().contains(sunFlower.getCardView())){
                cardPlants.add(sunFlower);
                selectCard(sunFlower.getCardView());
            }
            else{
                cardPlants.remove(sunFlower);
                removeCard(sunFlower.getCardView());
            }
        });
        peaShooter.getCardView().setOnMouseClicked(e -> {
            if(!playerCards.getChildren().contains(peaShooter.getCardView())){
                cardPlants.add(peaShooter);
                selectCard(peaShooter.getCardView());
            }
            else{
                cardPlants.remove(peaShooter);
                removeCard(peaShooter.getCardView());
            }
        });
        repeater.getCardView().setOnMouseClicked(e -> {
            if(!playerCards.getChildren().contains(repeater.getCardView())){
                cardPlants.add(repeater);
                selectCard(repeater.getCardView());
            }
            else{
                cardPlants.remove(repeater);
                removeCard(repeater.getCardView());
            }
        });
        snowPea.getCardView().setOnMouseClicked(e -> {
            if(!playerCards.getChildren().contains(snowPea.getCardView())){
                cardPlants.add(snowPea);
                selectCard(snowPea.getCardView());
            }
            else{
                cardPlants.remove(snowPea);
                removeCard(snowPea.getCardView());
            }
        });
        wallNut.getCardView().setOnMouseClicked(e -> {
            if(!playerCards.getChildren().contains(wallNut.getCardView())){
                cardPlants.add(wallNut);
                selectCard(wallNut.getCardView());
            }
            else{
                cardPlants.remove(wallNut);
                removeCard(wallNut.getCardView());
            }
        });
        tallNut.getCardView().setOnMouseClicked(e -> {
            if(!playerCards.getChildren().contains(tallNut.getCardView())){
                cardPlants.add(tallNut);
                selectCard(tallNut.getCardView());
            }
            else{
                cardPlants.remove(tallNut);
                removeCard(tallNut.getCardView());
            }
        });
        cherryBomb.getCardView().setOnMouseClicked(e -> {
            if(!playerCards.getChildren().contains(cherryBomb.getCardView())){
                cardPlants.add(cherryBomb);
                selectCard(cherryBomb.getCardView());
            }
            else{
                cardPlants.remove(cherryBomb);
                removeCard(cherryBomb.getCardView());
            }
        });
        jalapeno.getCardView().setOnMouseClicked(e -> {
            if(!playerCards.getChildren().contains(jalapeno.getCardView())){
                cardPlants.add(jalapeno);
                selectCard(jalapeno.getCardView());
            }
            else{
                cardPlants.remove(jalapeno);
                removeCard(jalapeno.getCardView());
            }
        });
        puffShroom.getCardView().setOnMouseClicked(e -> {
            if(!playerCards.getChildren().contains(puffShroom.getCardView())){
                cardPlants.add(puffShroom);
                selectCard(puffShroom.getCardView());
            }
            else{
                cardPlants.remove(puffShroom);
                removeCard(puffShroom.getCardView());
            }
        });
        iceShroom.getCardView().setOnMouseClicked(e -> {
            if(!playerCards.getChildren().contains(iceShroom.getCardView())){
                cardPlants.add(iceShroom);
                selectCard(iceShroom.getCardView());
            }
            else{
                cardPlants.remove(iceShroom);
                removeCard(iceShroom.getCardView());
            }
        });
        scaredyShroom.getCardView().setOnMouseClicked(e -> {
            if(!playerCards.getChildren().contains(scaredyShroom.getCardView())){
                cardPlants.add(scaredyShroom);
                selectCard(scaredyShroom.getCardView());
            }
            else{
                cardPlants.remove(scaredyShroom);
                removeCard(scaredyShroom.getCardView());
            }
        });

    }


    public void selectCard(ImageView imageView) {
        if (!playerCards.getChildren().contains(imageView) && playerCards.getChildren().size() <= 5) {
            playerCards.getChildren().add(imageView);
            cardLabel.setText(playerCards.getChildren().size() + " card chosen!");
        }
        if(playerCards.getChildren().size() == 6) {
            cardLabel.setText("you at most can choose 6 card! let's play ...");
            MainMenuController.animateImage(playButton);
            //set the button clickable.
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
