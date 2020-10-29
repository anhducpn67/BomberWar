package entities;

import graphics.Sprite;
import map.GameMap;

public class Grass extends Entity {

    public Grass(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        canBlock = false;
        isDangerous = false;
    }

    @Override
    public void update(GameMap gameMap) {

    }
}
