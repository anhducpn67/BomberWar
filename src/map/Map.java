package map;

import entities.bean.Character;
import entities.bean.Entity;
import entities.bean.Item;
import entities.bomb.Bomb;
import entities.character.Bomber;
import entities.character.CharacterFactory;
import entities.item.ItemFactory;
import entities.still.StillFactory;
import input.Sound;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.scene.canvas.GraphicsContext;
import javafx.util.Duration;
import main.BombermanGame;
import sprite.Sprite;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;

public class Map {

    private static Map gameMap;

    public int WIDTH, HEIGHT;

    public Entity[][] tiles;
    public ArrayList<Character> characters;
    public ArrayList<Item> items;
    public static Bomber player_1;
    public ArrayList<Bomb> bombs;
    public static int stage = 0;
    public static int score = 0;

    private Map() {
    }

    public static Map getGameMap() {
        if (gameMap == null) {
            gameMap = new Map();
        }
        return gameMap;
    }

    private void resetEntities() {
        tiles = new Entity[WIDTH][HEIGHT];
        characters = new ArrayList<>();
        bombs = new ArrayList<>();
        items = new ArrayList<>();
    }

    public void createMap(String mapPath) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(mapPath));
        HEIGHT = scanner.nextInt(); WIDTH = scanner.nextInt(); scanner.nextLine();
        resetEntities();
        for (int i = 0; i < HEIGHT; i++) {
            String string = scanner.nextLine();
            for (int j = 0; j < WIDTH; j++) {
                char c = string.charAt(j);
                StillFactory.getStill(c, i, j);
                ItemFactory.getItem(c, i, j);
                CharacterFactory.getCharacter(c, i, j);
            }
        }
    }

    public void updateMap() {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                tiles[j][i].update();
            }
        }
        for (Bomb bomb: bombs) {
            bomb.update();
        }
        for (Character character: characters) {
            character.update();
        }
    }

    public void renderMap(GraphicsContext gc) {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                tiles[j][i].render(gc);
            }
        }
        for (Bomb bomb: bombs) {
            bomb.render(gc);
        }
        for (Item item: items) {
            item.render(gc);
        }
        for (Character character: characters) {
            character.render(gc);
        }
    }

    public void nextStage() {
        Map.stage += 1;
        if (Sound.stageCleared.isPlaying()) {
            Sound.stageCleared.stop();
        }
        if (Map.stage % 2 == 1) {
            Sound.backgroundSound.stop();
            Sound.backgroundSound = Sound.playSound("AreaBoss");
        } else {
            Sound.backgroundSound.stop();
            Sound.backgroundSound = Sound.playSound("Area2");
        }
        String stagePath = String.format("./res/levels/Level%d.txt", Map.stage);
        try {
            createMap(stagePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Message.showNextStageMessenger();
//        BombermanGame.createStage();
    }
}
