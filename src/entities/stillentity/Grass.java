package entities.stillentity;

import entities.bean.Entity;
import graphics.Sprite;

public class Grass extends Entity {

    public Grass(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        canBlock = false;
        isDangerous = false;
    }

    @Override
    public void destroy() {

    }

    @Override
    public void update() {

    }
}
