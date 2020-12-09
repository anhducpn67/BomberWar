package entities.still;

import entities.bean.Entity;
import sprite.Sprite;

public class Wall extends Entity {
    public Wall(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        gameMap.tiles[x][y] = this;
        canBlock = true;
    }
}
