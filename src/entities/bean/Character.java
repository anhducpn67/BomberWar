package entities.bean;

import entities.BombermanGame;
import entities.bomb.Bomb;
import entities.character.Bomber;
import graphics.Sprite;

public abstract class Character extends AnimateEntity {

    protected int defaultVelocity = 1;
    protected int velocityX;
    protected int velocityY;
    protected boolean isStand;
    protected int speed;

    public Character(int x, int y, int velocityX, int velocityY, Sprite sprite) {
        super( x, y, sprite);
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        speed = 1;
        isStand = true;
    }

    public void setVelocity(int velocityX, int velocityY) {
        this.velocityX = velocityX;
        this.velocityY = velocityY;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

    public void addVelocity(int velocityX, int velocityY) {
        this.velocityX += velocityX;
        this.velocityY += velocityY;
    }

    public void move() {
        pixelX += velocityX;
        pixelY += velocityY;
    }

    public void checkCollision() {
        isMovable = true;
        pixelX += this.velocityX;
        pixelY += this.velocityY;
        for (Bomb bomb: gameMap.bombs) {
            if (this.isCollision(bomb)) {
                if (bomb.isDestroyed) {
                    this.destroy();
                } else {
                    isMovable = false;
                }
                if (this instanceof Bomber && !bomb.canBlock) {
                    isMovable = true;
                }
            } else {
                if (this instanceof Bomber) {
                    bomb.canBlock = true;
                }
            }
        }
        for (int i = 0; i < BombermanGame.HEIGHT; i++) {
            for (int j = 0; j < BombermanGame.WIDTH; j++) {
                Entity entity = gameMap.map[j][i];
                if (entity.canBlock && this.isCollision(entity)) {
                    isMovable = false;
                }
            }
        }
        isStand = !isMovable || (velocityX == 0 && velocityY == 0);
        pixelX -= this.velocityX;
        pixelY -= this.velocityY;
    }

    public void smootherMove() {
        mapX = pixelX / Sprite.SCALED_SIZE;
        mapY = pixelY / Sprite.SCALED_SIZE;
    }

    @Override
    public void update() {
        if (isDestroyed) {
            super.update();
            return;
        }
        for (int i = 1; i <= speed; i++) {
            handleKeyInput();
            checkCollision();
            if (!isStand) {
                updateAnimated();
            }
            if (isMovable) {
                move();
                smootherMove();
            }
        }
    }

    @Override
    public void delete() {
        gameMap.characters.remove(this);
    }

    abstract public void handleKeyInput();
}
