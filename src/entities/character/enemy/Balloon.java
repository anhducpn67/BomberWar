package entities.character.enemy;

import entities.bean.Enemy;
import graphics.Sprite;
import trace.RandomTrace;

public class Balloon extends Enemy {

    public Balloon(int x, int y, int velocityX, int velocityY, Sprite sprite) {
        super(x, y, velocityX, velocityY, sprite);
        animatedSprites.put("LEFT", new Sprite[]{Sprite.balloon_left1, Sprite.balloon_left2, Sprite.balloon_left3});
        animatedSprites.put("RIGHT", new Sprite[]{Sprite.balloon_right1, Sprite.balloon_right2, Sprite.balloon_right3});
        animatedSprites.put("DESTROYED", new Sprite[]{Sprite.balloon_dead, Sprite.balloon_dead, Sprite.balloon_dead});
        currentAnimate = animatedSprites.get("RIGHT");
        traceStrategy = new RandomTrace();
    }

    @Override
    public void delete() {
        super.delete();
    }
}