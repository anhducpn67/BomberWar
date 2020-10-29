package entities;

import graphics.Sprite;
import map.GameMap;

public class Wall extends Entity {

    public Wall(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        canBlock = true;
        isDangerous = false;
    }

    @Override
    public void update(GameMap gameMap) {

    }
}
