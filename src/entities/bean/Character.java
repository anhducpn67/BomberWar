package entities.bean;

import entities.bomb.Bomb;
import entities.character.Bomber;
import entities.features.Movable;
import entities.still.Brick;
import sprite.Sprite;

public abstract class Character extends AnimateEntity implements Movable {

    protected int defaultVelocity = 1;
    protected int velocityX = 0;
    protected int velocityY = 0;
    protected boolean isStand;
    protected int speed = 1;
    public boolean isCollision;
    public int direction = 2;
    public boolean isWallPass = false;
    public boolean isBombPass = false;

    public Character(int x, int y, Sprite sprite) {
        super( x, y, sprite);
        gameMap.characters.add(this);
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
        isCollision = false;
        pixelX += this.velocityX;
        pixelY += this.velocityY;
        for (Bomb bomb: gameMap.bombs) {
            if (this.isCollision(bomb)) {
                if (bomb.isDestroyed) {
                    this.boom();
                } else {
                    if (isBombPass) {
                        continue;
                    }
                    isCollision = true;
                }
                if (this instanceof Bomber && !bomb.canBlock) {
                    isCollision = false;
                }
            } else {
                if (this instanceof Bomber) {
                    bomb.canBlock = true;
                }
            }
        }
        for (int i = 0; i < gameMap.HEIGHT; i++) {
            for (int j = 0; j < gameMap.WIDTH; j++) {
                Entity entity = gameMap.tiles[j][i];
                if (entity instanceof Brick && isWallPass) {
                    continue;
                }
                if (entity.canBlock && this.isCollision(entity)) {
                    isCollision = true;
                }
            }
        }
        isStand = isCollision || (velocityX == 0 && velocityY == 0);
        pixelX -= this.velocityX;
        pixelY -= this.velocityY;
    }

    @Override
    public void update() {
        if (isDestroyed) {
            super.update();
            return;
        }
        for (int i = 1; i <= speed; i++) {
            getDirection();
            checkCollision();
            if (!isStand) {
                updateAnimation();
            }
            if (!isCollision) {
                move();
            }
        }
    }

    abstract public void getDirection();
}
