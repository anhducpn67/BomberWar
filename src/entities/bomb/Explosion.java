package entities.bomb;

import entities.bean.AnimateEntity;
import entities.bean.Character;
import entities.bean.Entity;
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

    }

    @Override
    public void update() {
        updateAnimated();
        checkMap();
    }

    private void checkMap() {
        Entity entity = gameMap.map[this.x][this.y];
        if (entity.isDestroyable) {
            entity.destroy();
        }
        for (Character character: gameMap.characters) {
            if (character.getX() == this.x && character.getY() == this.y) {
                character.destroy();
            }
        }
    }
}
