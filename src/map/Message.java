package map;

import input.Sound;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import main.BombermanGame;
import sprite.Sprite;

import java.io.IOException;

public class Message {

    public static Map gameMap = Map.getGameMap();

    public static Text stage = new Text();
    public static Text score = new Text();
    public static Text time = new Text();
    public static Text left = new Text();
    static {
        Font font = Font.loadFont("file:resources/fonts/font3.otf", 20);
        stage.setFont(font);
        score.setFont(font);
        time.setFont(font);
        left.setFont(font);
    }

    public static Text createText(String string, int size) {
        Font font = Font.loadFont("file:resources/fonts/font2.TTF", size);
        Text text = new Text(string);
        text.setFont(font);
        text.setFill(Color.WHITE);
        return text;
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
        Text text = createText("Stage " + Map.stage, 30);
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
        Text text1 = createText("DEFEAT!", 30);
        Text text2 = createText("Your score is " + Map.score, 20);
        VBox root = new VBox(text1, text2);
        root.setAlignment(Pos.CENTER);  root.setSpacing(10);
        root.setPrefSize(Sprite.SCALED_SIZE * gameMap.WIDTH, Sprite.SCALED_SIZE * gameMap.HEIGHT);
        root.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0),null,null)));
        Scene scene = new Scene(root);
        scene.setOnKeyPressed(e -> System.exit(0));
        BombermanGame.stage.setScene(scene);
        BombermanGame.stage.show();
    }

    public static void showMenu() throws IOException {
        Sound.backgroundSound = Sound.playSound("Title");
        BombermanGame.stage.setScene(MenuController.getScene());
        BombermanGame.stage.show();
    }
}