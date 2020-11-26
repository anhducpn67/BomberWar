package entities.bean;

import sprite.Sprite;
import trace.TraceStrategy;

abstract public class Enemy extends Character {

    public TraceStrategy traceStrategy;
    public int direction;

    public Enemy(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

    @Override
    public void getDirection() {
        direction =  traceStrategy.trace(this, gameMap.player_1);
        if (direction == 0) {
            this.setVelocity(-defaultVelocity,0);
            currentAnimate = animatedSprites.get("LEFT");
        }
        if (direction == 1) {
            this.setVelocity(defaultVelocity, 0);
            currentAnimate = animatedSprites.get("RIGHT");
        }
        if (direction == 2) {
            this.setVelocity(0, -defaultVelocity);
            currentAnimate = animatedSprites.get("LEFT");
        }
        if (direction == 3) {
            this.setVelocity(0, defaultVelocity);
            currentAnimate = animatedSprites.get("RIGHT");
        }
        if (direction == 4) {
            this.setVelocity(0, 0);
        }
    }
}
