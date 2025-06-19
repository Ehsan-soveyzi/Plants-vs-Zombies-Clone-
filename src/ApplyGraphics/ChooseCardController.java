package ApplyGraphics;

import Character.KindsOfPlants.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.Node;
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

    public static VBox cards;
    public static ArrayList<Plant> cardPlants = new ArrayList<>();


    @FXML
    public void initialize() {
        chooseCard();
        cards = playerCards;

    }

    public void chooseCard(){
        SunFlower.setOnMouseClicked(e -> {
            cardPlants.add(new SunFlower());
            selectCard(SunFlower);
        });
        Peashooter.setOnMouseClicked(e -> {
            cardPlants.add(new PeaShooter());
            selectCard(Peashooter);
        });
        Repeater.setOnMouseClicked(e -> {
            cardPlants.add(new Repeater());
            selectCard(Repeater);
        });
        Snowpea.setOnMouseClicked(e -> {
            cardPlants.add(new SnowPea());
            selectCard(Snowpea);
        });
        WallNut.setOnMouseClicked(e -> {
            cardPlants.add(new WallNut());
            selectCard(WallNut);
        });
        TallNut.setOnMouseClicked(e -> {
            cardPlants.add(new TallNut());
            selectCard(TallNut);
        });
        CherryBomb.setOnMouseClicked(e -> {
            cardPlants.add(new CherryBomb());
            selectCard(CherryBomb);
        });
        Jalapeno.setOnMouseClicked(e -> {
            cardPlants.add(new Jalapeno());
            selectCard(Jalapeno);
        });
    }


//    private void createGridPane() {
//        for (Node node : cardList.getChildren()) {
//            if (node instanceof ImageView) {
//                ImageView iv = (ImageView) node;
//                iv.setOnMouseClicked(event -> {
//                    selectCard(iv);
//                });
//            }
//        }
//    }

    public void selectCard(ImageView imageView) {
        if (!playerCards.getChildren().contains(imageView) && playerCards.getChildren().size() <= 5) {
            playerCards.getChildren().add(imageView);
            cardList.getChildren().remove(imageView);
        }
        if (playerCards.getChildren().size() <= 5)cardLabel.setText(playerCards.getChildren().size() + " card chosen!");
        else {
            cardLabel.setText("you at most can choose 6 card! let's play ...");
            MainMenuController.animateImage(playButton);
            playButton.setOnMouseClicked(event -> {
                    enterGame(event);
            });
        }
    }
    public static VBox getVBox() {
        return cards;
    }

    public void enterGame(MouseEvent mouseEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Map.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            GameMain.setFaceTransition(root);
            MainMenuController.stage.setScene(scene);
            MainMenuController.stage.setFullScreen(true);
            MainMenuController.stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
