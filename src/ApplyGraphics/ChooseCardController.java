package ApplyGraphics;

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

import java.awt.*;
import java.io.IOException;

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
    public void initialize() {
        createGridPane();
    }

    private void createGridPane() {
        for (Node node : cardList.getChildren()) {
            if (node instanceof ImageView) {
                ImageView iv = (ImageView) node;
                iv.setOnMouseClicked(event -> {
                    selectCard(iv);
                });
            }
        }
    }

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

    public void enterGame(MouseEvent mouseEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Map.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            MainMenuController.stage.setScene(scene);
            MainMenuController.stage.setFullScreen(true);
            MainMenuController.stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
