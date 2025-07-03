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

    public static ArrayList<Plant> cardPlants;

    //this use to get the cardView images for each plant.
    public static final Plant[] plants = {new SunFlower(),new PeaShooter(),new Repeater(),new SnowPea(),new CherryBomb()
            ,new Jalapeno(),new TallNut(),new WallNut(),new PuffShroom(),new IceShroom(),new ScaredyShroom(),new GraveBuster(),
            new Blover(),new Plantern(),new DoomShroom(),new HypnoShroom(),new CoffeeBean()};


    @FXML
    public void initialize() {
        cardPlants = new ArrayList<>();
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
        for(Plant plant : plants){
            plant.getCardView().setOnMouseClicked(e -> {
               if (!playerCards.getChildren().contains(plant.getCardView())) {
                   cardPlants.add(plant);
                   selectCard(plant.getCardView());
               }
               else{
                   cardPlants.remove(plant);
                   removeCard(plant.getCardView());
               }
            });
        }

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
