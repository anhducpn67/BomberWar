package entities;

import graphics.Sprite;
import map.GameMap;

public class Bomb extends Entity {


    public Bomb(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        canBlock = true;
        isDangerous = true;
    }

    @Override
    public void update(GameMap gameMap) {

    }
}
