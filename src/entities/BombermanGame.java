package entities;

import input.KeyInput;
import input.LongValue;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import graphics.Sprite;
import map.GameMap;

import java.io.*;

public class BombermanGame extends Application {
    
    public static int WIDTH;
    public static int HEIGHT;
    public static int time;
    public static GameMap gameMap = GameMap.getGameMap();

    private GraphicsContext gc;
    private Canvas canvas;

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) throws IOException {
        gameMap.createMap("res/levels/Level1.txt");
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();
        Group root = new Group();
        root.getChildren().add(canvas);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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
        final long startNanoTime = System.nanoTime();
        LongValue lastNanoTime = new LongValue( System.nanoTime() );
        new AnimationTimer() {
            @Override
            public void handle(long currentNanoTime) {
                time = (int) ((currentNanoTime - startNanoTime) / 60000000) + 1;
                lastNanoTime.value = currentNanoTime;
                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                gameMap.updateMap();
                gameMap.renderMap(gc);
            }
        }.start();
    }
}
