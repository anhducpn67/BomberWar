package map;

import entities.*;
import entities.bean.Entity;
import entities.bean.Character;
import entities.bean.Item;
import entities.character.Balloon;
import entities.character.Bomber;
import entities.bomb.Bomb;
import entities.character.Oneal;
import entities.item.Flame;
import entities.still.Brick;
import entities.still.Grass;
import entities.still.Wall;
import graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class GameMap {
    private static GameMap gameMap;
    public Entity[][] map;
    public ArrayList<Character> characters;
    public ArrayList<Item> items;
    public ArrayList<Bomb> bombs = new ArrayList<>();
    public long score;

    private GameMap() {

    }

    public static GameMap getGameMap() {
        if (gameMap == null) {
            gameMap = new GameMap();
        }
        return gameMap;
    }

    public void createMap(String mapPath) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(mapPath));
        BombermanGame.HEIGHT = scanner.nextInt();
        BombermanGame.WIDTH = scanner.nextInt();
        scanner.nextLine();
        map = new Entity[BombermanGame.WIDTH][BombermanGame.HEIGHT];
        characters = new ArrayList<>();
        bombs = new ArrayList<>();
        items = new ArrayList<>();
        for (int i = 0; i < BombermanGame.HEIGHT; i++) {
            String string = scanner.nextLine();
            for (int j = 0; j < BombermanGame.WIDTH; j++) {
                char c = string.charAt(j);
                Entity object = new Grass(j, i, Sprite.grass);
                if (c == '#') {
                    object = new Wall(j, i, Sprite.wall);
                }
                if (c == '*') {
                    object = new Brick(j, i, Sprite.brick);
                }
                if (c == 'p') {
                    Character bomberman = new Bomber(j, i, 0, 0, Sprite.player_right);
                    characters.add(bomberman);
                }
                if (c == '1') {
                    Character balloom = new Balloon(j, i, 0, 0, Sprite.balloon_right1);
                    characters.add(balloom);
                }
                if (c == '2') {
                    Character oneal = new Oneal(j, i, 0, 0, Sprite.oneal_right1);
                    characters.add(oneal);
                }
                if (c == 'f') {
                    object = new Brick(j, i, Sprite.brick);
                    Item flame = new Flame(j, i, Sprite.powerup_flames);
                    items.add(flame);
                }
                if (c == 'b') {
                    object = new Brick(j, i, Sprite.brick);
                    Item bomb = new entities.item.Bomb(j, i, Sprite.powerup_bombs);
                    items.add(bomb);
                }
                map[j][i] = object;
            }
        }
    }

    public void updateMap() {
        for (int i = 0; i < BombermanGame.HEIGHT; i++) {
            for (int j = 0; j < BombermanGame.WIDTH; j++) {
                map[j][i].update();
            }
        }
        for (Character character: characters) {
            character.update();
        }
        for (Bomb bomb: bombs) {
            bomb.update();
        }
        for (Item item: items) {
            item.update();
        }
    }

    public void renderMap(GraphicsContext gc) {
        for (int i = 0; i < BombermanGame.HEIGHT; i++) {
            for (int j = 0; j < BombermanGame.WIDTH; j++) {
                map[j][i].render(gc);
            }
        }
        for (Character character: characters) {
            character.render(gc);
        }
        for (Bomb bomb: bombs) {
            bomb.render(gc);
        }
        for (Item item: items) {
            item.render(gc);
        }
    }
}
