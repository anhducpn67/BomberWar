package entities.character;

import entities.bean.Monster;
import graphics.Sprite;

public class Balloom extends Monster {

    public Balloom(int x, int y, double velocityX, double velocityY, Sprite sprite) {
        super(x, y, velocityX, velocityY, sprite);
        animatedSprites.put("LEFT", new Sprite[]{Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3});
        animatedSprites.put("RIGHT", new Sprite[]{Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3});
        animatedSprites.put("DESTROYED", new Sprite[]{Sprite.balloom_dead, Sprite.balloom_dead, Sprite.balloom_dead});
        currentAnimate = animatedSprites.get("RIGHT");
    }

    @Override
    public void delete() {
        super.delete();
    }
}