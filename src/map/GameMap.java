package map;

import entities.*;
import entities.Character;
import graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class GameMap {
    public Entity[][] map;
    public ArrayList<Character> characters = new ArrayList<>();

    public GameMap(String mapPath) throws FileNotFoundException {
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
                    Character bomberman = new Bomber(1, 1, 0, 0, Sprite.player_right);
                    characters.add(bomberman);
                }
                map[j][i] = object;
            }
        }
    }

    public void updateMap() {
        for (int i = 0; i < BombermanGame.HEIGHT; i++) {
            for (int j = 0; j < BombermanGame.WIDTH; j++) {
                map[j][i].update(this);
            }
        }
        for (Character character: characters) {
            character.update(this);
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
    }
}
