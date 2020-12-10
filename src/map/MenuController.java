package map;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import main.BombermanGame;

import java.io.IOException;

public class MenuController extends BombermanGame {

    public Map gameMap = Map.getGameMap();

    public void campaignMode() {
        gameMap.mode = "campaign";
        gameMap.nextStage();
    }

    public void twoPlayerMode() {
        gameMap.mode = "twoplayer";
        gameMap.nextStage();
    }

    public void exit() {
        System.exit(0);
    }

    public static Scene getScene() throws IOException {
        Font.loadFont(MenuController.class.getResourceAsStream("/fonts/font1.ttf"), 30);
        Font.loadFont(MenuController.class.getResourceAsStream("/fonts/font2.ttf"), 30);
        Font.loadFont(MenuController.class.getResourceAsStream("/fonts/font3.otf"), 25);
        Parent root = FXMLLoader.load(MenuController.class.getResource("/scene/menu.fxml"));
        return new Scene(root);
    }
}
