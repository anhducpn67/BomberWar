package entities.still;

import entities.bean.Entity;
import sprite.Sprite;

public class Grass extends Entity {

    public Grass(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        canBlock = false;
        gameMap.tiles[x][y] = this;
    }
}
