package entities.bomb;

import entities.bean.AnimateEntity;
import entities.bean.Character;
import entities.bean.Entity;
import entities.bean.Item;
import entities.still.Brick;
import javafx.scene.canvas.GraphicsContext;
import sprite.Sprite;

public class Explosion extends AnimateEntity {

    boolean isDestroyBrick = false;

    public Explosion(int x, int y, Sprite sprite, String animate) {
        super(x, y, sprite);
        animatedSprites.put("HORIZONTAL", new Sprite[]{Sprite.explosion_horizontal,
                Sprite.explosion_horizontal1, Sprite.explosion_horizontal2});
        animatedSprites.put("RIGHT_LAST", new Sprite[]{Sprite.explosion_horizontal_right_last,
                Sprite.explosion_horizontal_right_last1, Sprite.explosion_horizontal_right_last2});
        animatedSprites.put("VERTICAL", new Sprite[]{Sprite.explosion_vertical,
                Sprite.explosion_vertical1, Sprite.explosion_vertical2});
        animatedSprites.put("LEFT_LAST", new Sprite[]{Sprite.explosion_horizontal_left_last,
                Sprite.explosion_horizontal_left_last1, Sprite.explosion_horizontal_left_last2});
        animatedSprites.put("TOP_LAST", new Sprite[]{Sprite.explosion_vertical_top_last,
                Sprite.explosion_vertical_top_last1, Sprite.explosion_vertical_top_last2});
        animatedSprites.put("DOWN_LAST", new Sprite[]{Sprite.explosion_vertical_down_last,
                Sprite.explosion_vertical_down_last1, Sprite.explosion_vertical_down_last2});
        animatedSprites.put("CENTER", new Sprite[]{Sprite.bomb_exploded, Sprite.bomb_exploded1, Sprite.bomb_exploded2});
        currentAnimate = animatedSprites.get(animate);
    }

    @Override
    public void delete() {
        if (!isDestroyed) {
            isDestroyed = true;
            isCollision();
        }
    }

    @Override
    public void update() {
        updateAnimation();
        delete();
    }

    private void isCollision() {
        Entity entity = gameMap.tiles[this.tileX][this.tileY];
        if (entity instanceof Brick) {
            isDestroyBrick = true;
            ((Brick) entity).destroy();
        }
        for (Character character: gameMap.characters) {
            if (this.isCollision(character) && !character.isFlamePass) {
                character.destroy();
            }
        }
        for (Item item: gameMap.items) {
            if (this.isCollision(item) && item.isDestroyable) {
                item.destroy();
            }
        }
        for (Bomb bomb: gameMap.bombs) {
            if (this.isCollision(bomb) && !bomb.isExploded) {
                bomb.timeBeforeExplode = 1;
            }
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        if (!isDestroyBrick) {
            super.render(gc);
        }
    }
}
