package entities.still;

import entities.bean.StillEntity;
import sprite.Sprite;

public class Wall extends StillEntity {
    public Wall(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        gameMap.tiles[x][y] = this;
        canBlock = true;
    }
}
