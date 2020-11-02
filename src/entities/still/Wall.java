package entities.still;

import entities.bean.Entity;
import graphics.Sprite;

public class Wall extends Entity {

    public Wall(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        canBlock = true;
        isDangerous = false;
    }

    @Override
    public void destroy() {

    }

    @Override
    public void update() {

    }
}
