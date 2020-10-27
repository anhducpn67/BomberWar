package entities;

import graphics.Sprite;


public class Bomber extends Character {
    private final Sprite[] animated = new Sprite[]{Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2};

    public Bomber(int x, int y, double velocityX, double velocityY, Sprite sprite) {
        super( x, y, velocityX, velocityY, sprite);
    }

    @Override
    void checkCollision() {
        x += this.velocityX;
        y += this.velocityY;
        isMovable = true;
        for (Entity entity: BombermanGame.stillObjects) {
            if (entity instanceof Wall && this.isCollision(entity)) {
                isMovable = false;
            }
        }
        x -= this.velocityX;
        y -= this.velocityY;
    }

    @Override
    public void update() {
        System.out.println(x + " " + y);
        updateAnimated();
        this.direction = "LEFT";
        this.setVelocity(0,0);
        if (BombermanGame.input.contains("LEFT"))
            this.setVelocity(-defaultVelocity,0);
        if (BombermanGame.input.contains("RIGHT")) {
            this.setVelocity(defaultVelocity, 0);
        }
        if (BombermanGame.input.contains("UP"))
            this.setVelocity(0,-defaultVelocity);
        if (BombermanGame.input.contains("DOWN"))
            this.setVelocity(0,defaultVelocity);
        checkCollision();
        if (isMovable) {
            move();
        }
    }

    private void updateAnimated() {
        this.sprite = Sprite.movingSprite(animated, 3, BombermanGame.time);
        this.img = this.sprite.getFxImage();
    }
}
