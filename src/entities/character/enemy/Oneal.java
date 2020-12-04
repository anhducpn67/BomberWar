package entities.character.enemy;

import entities.bean.Enemy;
import sprite.Sprite;
import trace.DistanceTrace;

public class Oneal extends Enemy {
    public Oneal(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        animatedSprites.put("LEFT", new Sprite[]{Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3});
        animatedSprites.put("UP", new Sprite[]{Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3});
        animatedSprites.put("RIGHT", new Sprite[]{Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3});
        animatedSprites.put("DOWN", new Sprite[]{Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3});
        animatedSprites.put("DESTROYED", new Sprite[]{Sprite.oneal_dead, Sprite.mob_dead1, Sprite.mob_dead3});
        currentAnimate = animatedSprites.get("RIGHT");
    }

    @Override
    public void setSpeciality() {
        traceStrategy = new DistanceTrace();
        score = 300;
    }
}