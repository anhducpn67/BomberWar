package map;

import input.KeyInput;
import javafx.animation.PauseTransition;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import main.BombermanGame;
import sprite.Sprite;

public class Message {

    public static Map gameMap = Map.getGameMap();

    public static Text stage = new Text();
    public static Text score = new Text();
    public static Text time = new Text();
    public static Text left = new Text();
    static {
        Font font = Font.loadFont("file:res/fonts/font3.otf", 20);
        stage.setFont(font);
        score.setFont(font);
        time.setFont(font);
        left.setFont(font);
    }

    public static void updateBoard() {
        Message.score.setText("SCORE " + Map.score);
        Message.stage.setText("STAGE " + Map.stage);
        Message.left.setText("LEFT " + Map.player_1.life);
        Message.time.setText("TIME " + 100);
    }

    public static HBox getBoard() {
        HBox hBox = new HBox(Message.stage, Message.score, Message.left, Message.time);
        hBox.setSpacing(100.0);
        hBox.setAlignment(Pos.CENTER);
        hBox.setBackground(new Background(new BackgroundFill(Color.rgb(185, 185, 185),null,null)));
        return hBox;
    }

    public static void showNextStageMessenger() {
        BombermanGame.isPause = true;
        Font font = Font.loadFont("file:res/fonts/font2.TTF", 30);
        Text text = new Text();
        text.setFont(font);
        text.setText("Stage " + Map.stage);
        text.setFill(Color.WHITE);
        StackPane root = new StackPane(text);
        root.setPrefSize(Sprite.SCALED_SIZE * gameMap.WIDTH, Sprite.SCALED_SIZE * gameMap.HEIGHT);
        root.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0),null,null)));
        Scene scene = new Scene(root);
        scene.setOnKeyPressed(e -> {
            BombermanGame.stage.close();
            BombermanGame.createStage();
        });
        BombermanGame.stage.setScene(scene);
        BombermanGame.stage.show();
    }

    public static void showDefeatMessage() {
        BombermanGame.isPause = true;
        Font font = Font.loadFont("file:res/fonts/font2.TTF", 30);
        Text text = new Text();
        text.setFont(font);
        text.setText("DEFEAT!");
        text.setFill(Color.WHITE);
        StackPane root = new StackPane(text);
        root.setPrefSize(Sprite.SCALED_SIZE * gameMap.WIDTH, Sprite.SCALED_SIZE * gameMap.HEIGHT);
        root.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0),null,null)));
        Scene scene = new Scene(root);
        scene.setOnKeyPressed(e -> {
            System.exit(0);
        });
        BombermanGame.stage.setScene(scene);
        BombermanGame.stage.show();
    }
}