package entities.bean;

import entities.BombermanGame;
import entities.bomb.Bomb;
import graphics.Sprite;

public abstract class Character extends AnimateEntity {

    protected static double defaultVelocity = 1;
    protected double velocityX;
    protected double velocityY;

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

    public void checkCollision() {
        x += this.velocityX;
        y += this.velocityY;
        isMovable = true;
        for (int i = 0; i < BombermanGame.HEIGHT; i++) {
            for (int j = 0; j < BombermanGame.WIDTH; j++) {
                Entity entity = gameMap.map[j][i];
                if (entity.canBlock && this.isCollision(entity)) {
                    isMovable = false;
                }
            }
        }
        for (Bomb bomb: gameMap.bombs) {
            if (this.isCollision(bomb)) {
                if (bomb.isDestroyed) {
                    this.destroy();
                } else {
                    isMovable = false;
                }
            }
        }
        x -= this.velocityX;
        y -= this.velocityY;
    }

    @Override
    public void update() {
        if (isDestroyed) {
            super.update();
        } else {
            handleKeyInput();
            checkCollision();
            updateAnimated();
            if (isMovable) {
                move();
            }
        }
    }

    @Override
    public void delete() {
        gameMap.characters.remove(this);
    }

    abstract public void handleKeyInput();
}
