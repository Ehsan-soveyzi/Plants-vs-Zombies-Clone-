package Map;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import Character.Zombie;
import Character.ZombieFactory;
import javafx.scene.layout.Pane;

public class MapController {

    @FXML
    private Pane paneWindow;
    @FXML
    private GridPane gridPane;

    @FXML
    public void initialize() {
        double cellWidth = gridPane.getPrefWidth() / gridPane.getColumnCount();
        double cellHeight = gridPane.getPrefHeight() / gridPane.getRowCount();
        Zombie zombie1 = ZombieFactory.createNormalZombie(1,1500,85);
        zombie1.getImageView().setFitWidth(cellWidth);
        zombie1.getImageView().setFitHeight(cellHeight);
        paneWindow.getChildren().add(zombie1.getImageView());
        zombie1.playWalkingAnimation();
    }

    public void createGrid(){



    }

}
