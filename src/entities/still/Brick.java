package entities.still;

import entities.bean.AnimateEntity;
import graphics.Sprite;

public class Brick extends AnimateEntity {

    public Brick(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        canBlock = true;
        isDangerous = false;
        isDestroyable = true;
        animatedSprites.put("DESTROYED", new Sprite[]{Sprite.brick_exploded, Sprite.brick_exploded1, Sprite.brick_exploded2});
    }

    @Override
    public void delete() {
        gameMap.map[this.x][this.y] = new Grass(this.x, this.y, Sprite.grass);
    }
}
