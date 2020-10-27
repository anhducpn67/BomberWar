package entities;

import graphics.Sprite;

public abstract class Character extends Entity {
    protected static final double defaultVelocity = 0.1;

    protected double velocityX;
    protected double velocityY;
    protected String direction;

    public Character(int x, int y, double velocityX, double velocityY, Sprite sprite) {
        super( x, y, sprite);
        this.velocityX = velocityX;
        this.velocityY = velocityY;
    }

    public void setVelocity(double velocityX, double velocityY) {
        this.velocityX = velocityX;
        this.velocityY = velocityY;
    }

    public void addVelocity(double velocityX, double velocityY) {
        this.velocityX += velocityX;
        this.velocityY += velocityY;
    }

    public void move() {
        if (isMovable) {
            x += velocityX;
            y += velocityY;
        }
    }

    abstract void checkCollision();
}
