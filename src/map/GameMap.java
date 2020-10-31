package map;

import entities.*;
import entities.bean.Entity;
import entities.bean.Character;
import entities.character.Balloom;
import entities.character.Bomber;
import entities.bomb.Bomb;
import entities.stillentity.Brick;
import entities.stillentity.Grass;
import entities.stillentity.Wall;
import graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class GameMap {
    private static GameMap gameMap;
    public Entity[][] map;
    public ArrayList<Character> characters = new ArrayList<>();
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
        map = new Entity[BombermanGame.WIDTH][BombermanGame.HEIGHT];
        scanner.nextLine();
        for (int i = 0; i < BombermanGame.HEIGHT; i++) {
            String string = scanner.nextLine();
            for (int j = 0; j < BombermanGame.WIDTH; j++) {
                char c = string.charAt(j);
                Entity object = new Grass(j, i, Sprite.grass);;
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
                    Character balloom = new Balloom(j, i, 0, 0, Sprite.balloom_right1);
                    characters.add(balloom);
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
    }
}
