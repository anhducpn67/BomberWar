package entities.bean;

import graphics.Sprite;

import java.util.Random;

abstract public class Enemy extends Character {

    private int direction;

    public Enemy(int x, int y, double velocityX, double velocityY, Sprite sprite) {
        super(x, y, velocityX, velocityY, sprite);
    }

    @Override
    public void handleKeyInput() {
        if (!isMovable) {
            Random rand = new Random();
            direction = rand.nextInt(4);
        }
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
    }
}
