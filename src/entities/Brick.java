package entities;

import graphics.Sprite;
import map.GameMap;

public class Brick extends Entity {

    public Brick(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        isDestroyable = true;
        canBlock = true;
        isDangerous = false;
    }


    @Override
    public void update(GameMap gameMap) {

    }
}
