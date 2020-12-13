package entities.still;

import entities.bean.StillEntity;
import sprite.Sprite;

public class Grass extends StillEntity {
    public Grass(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        canBlock = false;
        gameMap.tiles[x][y] = this;
    }
}
