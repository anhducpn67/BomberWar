package entities.character.enemy;

import entities.bean.Enemy;
import sprite.Sprite;
import trace.DistanceTrace;

public class Kondoria extends Enemy {
    public Kondoria(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        animatedSprites.put("LEFT", new Sprite[]{Sprite.kondoria_left1, Sprite.kondoria_left2, Sprite.kondoria_left3});
        animatedSprites.put("UP", new Sprite[]{Sprite.kondoria_left1, Sprite.kondoria_left2, Sprite.kondoria_left3});
        animatedSprites.put("RIGHT", new Sprite[]{Sprite.kondoria_right1, Sprite.kondoria_right2, Sprite.kondoria_right3});
        animatedSprites.put("DOWN", new Sprite[]{Sprite.kondoria_right1, Sprite.kondoria_right2, Sprite.kondoria_right3});
        animatedSprites.put("DESTROYED", new Sprite[]{Sprite.kondoria_dead, Sprite.mob_dead1, Sprite.mob_dead2});
        currentAnimate = animatedSprites.get("RIGHT");
    }

    @Override
    public void setSpeciality() {
        traceStrategy = new DistanceTrace();
        score = 1000;
        isWallPass = true;
    }
}
