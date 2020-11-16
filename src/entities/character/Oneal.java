package entities.character;

import entities.bean.Enemy;
import graphics.Sprite;

public class Oneal extends Enemy {

    public Oneal(int x, int y, int velocityX, int velocityY, Sprite sprite) {
        super(x, y, velocityX, velocityY, sprite);
        animatedSprites.put("LEFT", new Sprite[]{Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3});
        animatedSprites.put("RIGHT", new Sprite[]{Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3});
        animatedSprites.put("DESTROYED", new Sprite[]{Sprite.oneal_dead, Sprite.oneal_dead, Sprite.oneal_dead});
        currentAnimate = animatedSprites.get("RIGHT");
    }

    @Override
    public void delete() {
        super.delete();
    }
}