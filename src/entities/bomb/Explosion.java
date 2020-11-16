package entities.bomb;

import entities.bean.AnimateEntity;
import entities.bean.Character;
import entities.bean.Entity;
import entities.bean.Item;
import graphics.Sprite;

public class Explosion extends AnimateEntity {

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
        animatedSprites.put("UP_LAST", new Sprite[]{Sprite.explosion_vertical_top_last,
                Sprite.explosion_vertical_top_last1, Sprite.explosion_vertical_top_last2});
        animatedSprites.put("DOWN_LAST", new Sprite[]{Sprite.explosion_vertical_down_last,
                Sprite.explosion_vertical_down_last1, Sprite.explosion_vertical_down_last2});
        currentAnimate = animatedSprites.get(animate);
    }

    @Override
    public void delete() {
        if (!isDestroyed) {
            isDestroyed = true;
            checkMap();
        }
    }

    @Override
    public void update() {
        updateAnimated();
        delete();
    }

    private void checkMap() {
        Entity entity = gameMap.map[this.mapX][this.mapY];
        if (entity.isDestroyable) {
            entity.destroy();
        }
        for (Character character: gameMap.characters) {
            if (this.isCollision(character)) {
                character.destroy();
            }
        }
        for (Item item: gameMap.items) {
            if (this.isCollision(item)) {
                item.destroy();
            }
        }
    }
}
