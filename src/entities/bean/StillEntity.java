package entities.bean;

import sprite.Sprite;

public abstract class StillEntity extends Entity {
    public StillEntity(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

    @Override
    public void update() {
    }
}
