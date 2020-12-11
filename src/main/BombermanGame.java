package main;

import input.KeyInput;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import map.Map;
import map.Message;
import sprite.Sprite;

import java.io.IOException;

public class BombermanGame extends Application {

    public static int time;
    public static boolean isPause = true;
    public static Map gameMap = Map.getGameMap();

    public static Canvas canvas = new Canvas();
    public static GraphicsContext gc = canvas.getGraphicsContext2D();
    public static Stage stage;

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        BombermanGame.stage = primaryStage;
        BombermanGame.stage.setTitle("Bomberman");
        Message.showMenu();

        final long startNanoTime = System.nanoTime();
        new AnimationTimer() {
            @Override
            public void handle(long currentNanoTime) {
                time = (int) ((currentNanoTime - startNanoTime) / 60000000) + 1;
                if (!isPause) {
                    gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                    gameMap.updateMap();
                    Message.updateBoard();
                    gameMap.renderMap(gc);
                }
            }
        }.start();
    }

    public static void createStage() {
        isPause = false;
        canvas = new Canvas(Sprite.SCALED_SIZE * gameMap.WIDTH, Sprite.SCALED_SIZE * gameMap.HEIGHT);
        gc = canvas.getGraphicsContext2D();
        VBox root = new VBox(Message.getBoard(), canvas);
        Scene scene = new Scene(root);
        scene.setOnKeyPressed(
                e -> {
                    String code = e.getCode().toString();
                    if (code.equals("P")) {
                        Message.showPauseMessage();
                    }
                    KeyInput.keyInput.put(code, true);
                });
        scene.setOnKeyReleased(
                e -> {
                    String code = e.getCode().toString();
                    KeyInput.keyInput.put(code, false);
                });
        stage.setScene(scene);
        stage.show();
    }
}
