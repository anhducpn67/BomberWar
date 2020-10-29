package entities;

import graphics.Sprite;
import map.GameMap;

public abstract class Character extends Entity {
    protected static final double defaultVelocity = 1;

    protected double velocityX;
    protected double velocityY;
    protected String currentAnimate;
    {
        currentAnimate = "RIGHT";
    }

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
            x += velocityX * BombermanGame.elapsedTime;
            y += velocityY * BombermanGame.elapsedTime;
        }
    }

    public void checkCollisionForWall(GameMap gameMap) {
        x += this.velocityX * BombermanGame.elapsedTime;
        y += this.velocityY * BombermanGame.elapsedTime;
        isMovable = true;
        for (int i = 0; i < BombermanGame.HEIGHT; i++) {
            for (int j = 0; j < BombermanGame.WIDTH; j++) {
                Entity entity = gameMap.map[j][i];
                if (entity.canBlock && this.isCollision(entity)) {
                    isMovable = false;
                }
            }
        }
        x -= this.velocityX * BombermanGame.elapsedTime;
        y -= this.velocityY * BombermanGame.elapsedTime;
    }

    public void checkCollision(GameMap gameMap) {
        checkCollisionForWall(gameMap);
    }
    abstract void handleKeyInput();
}
