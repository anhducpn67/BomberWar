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
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.media.AudioClip;
import main.BombermanGame;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class GameMap {

    private static GameMap gameMap;

    public int WIDTH, HEIGHT;

    public Entity[][] tiles;
    public ArrayList<Character> characters;
    public ArrayList<Item> items;
    public Bomber player_1;
    public ArrayList<Bomb> bombs;
    public int level = 0;
    public static AudioClip backgroundSound = Sound.playSound("Area1");
    public static AudioClip stageCleared = Sound.playSound("StageCleared");
    static {
        stageCleared.stop();
        backgroundSound.stop();
    }

    private GameMap() {
    }

    public static GameMap getGameMap() {
        if (gameMap == null) {
            gameMap = new GameMap();
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

    public void nextLevel() {
        gameMap.level += 1;
        if (stageCleared.isPlaying()) {
            stageCleared.stop();
        }
        if (gameMap.level % 2 == 1) {
            backgroundSound.stop();
            backgroundSound = Sound.playSound("Area1");
        } else {
            backgroundSound.stop();
            backgroundSound = Sound.playSound("Area2");
        }
        String levelPath = String.format("./res/levels/Level%d.txt", gameMap.level);
        try {
            gameMap.createMap(levelPath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BombermanGame.createStage();
    }
}
