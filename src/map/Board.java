package map;

import javafx.scene.text.Text;

public class Board {
    public static Text stage = new Text();
    public static Text score = new Text();
    public static Text time = new Text();
    public static Text left = new Text();
    static {
        stage.setStyle("-fx-font-size: 30;");
        score.setStyle("-fx-font-size: 30;");
        time.setStyle("-fx-font-size: 30;");
        left.setStyle("-fx-font-size: 30;");
    }

    public static void updateBoard() {
        Board.score.setText("SCORE " + GameMap.score);
        Board.stage.setText("STAGE " + GameMap.stage);
        Board.left.setText("LEFT " + GameMap.player_1.life);
        Board.time.setText("TIME " + 100);
    }
}
