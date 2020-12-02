package entities.character.enemy;

import entities.bean.Enemy;
import sprite.Sprite;
import trace.DistanceTrace;

public class Minvo extends Enemy {
    public Minvo(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        animatedSprites.put("LEFT", new Sprite[]{Sprite.minvo_left1, Sprite.minvo_left2, Sprite.minvo_left3});
        animatedSprites.put("RIGHT", new Sprite[]{Sprite.minvo_right1, Sprite.minvo_right2, Sprite.minvo_right3});
        animatedSprites.put("DESTROYED", new Sprite[]{Sprite.minvo_dead, Sprite.minvo_dead, Sprite.minvo_dead});
        currentAnimate = animatedSprites.get("RIGHT");
    }

    @Override
    public void setSpeciality() {
        traceStrategy = new DistanceTrace();
        score = 800;
        speed = 2;
    }
}
