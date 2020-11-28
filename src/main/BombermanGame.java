package main;

import input.KeyInput;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import map.GameMap;
import sprite.Sprite;

public class BombermanGame extends Application {

    public static int time;
    public static GameMap gameMap = GameMap.getGameMap();

    public static GraphicsContext gc;
    public static Canvas canvas;
    public static Stage stage;

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        BombermanGame.stage = stage;
        gameMap.nextLevel();
        final long startNanoTime = System.nanoTime();
        new AnimationTimer() {
            @Override
            public void handle(long currentNanoTime) {
                time = (int) ((currentNanoTime - startNanoTime) / 60000000) + 1;
                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                gameMap.updateMap();
                gameMap.renderMap(gc);
            }
        }.start();
    }

    public static void createStage() {
        canvas = new Canvas(Sprite.SCALED_SIZE * gameMap.WIDTH, Sprite.SCALED_SIZE * gameMap.HEIGHT);
        gc = canvas.getGraphicsContext2D();
        Group root = new Group();
        root.getChildren().add(canvas);
        Scene scene = new Scene(root);
        scene.setOnKeyPressed(
                e -> {
                    String code = e.getCode().toString();
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
