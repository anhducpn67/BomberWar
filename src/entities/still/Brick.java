package entities.still;

import entities.bean.AnimateEntity;
import sprite.Sprite;

public class Brick extends AnimateEntity {

    public Brick(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        canBlock = true;
        isDestroyable = true;
        animatedSprites.put("DESTROYED", new Sprite[]{Sprite.brick_exploded, Sprite.brick_exploded1, Sprite.brick_exploded2});
        gameMap.tiles[x][y] = this;
    }

    @Override
    public void delete() {
        gameMap.tiles[this.tileX][this.tileY] = new Grass(this.tileX, this.tileY, Sprite.grass);
    }
}
