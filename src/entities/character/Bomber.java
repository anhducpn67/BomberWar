package entities.character;

import entities.bean.Character;
import entities.bean.Enemy;
import entities.bean.Item;
import entities.bomb.Bomb;
import graphics.Sprite;
import input.KeyInput;

public class Bomber extends Character {

    public int maxBombs = 1;
    private int direction;
    private final int[] dx = new int[]{1, 0, 0, 1};
    private final int[] dy = new int[]{0, 1, 1, 0};

    public Bomber(int x, int y, int velocityX, int velocityY, Sprite sprite) {
        super( x, y, velocityX, velocityY, sprite);
        animatedSprites.put("LEFT", new Sprite[]{Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2});
        animatedSprites.put("RIGHT", new Sprite[]{Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2});
        animatedSprites.put("UP", new Sprite[]{Sprite.player_up, Sprite.player_up_1, Sprite.player_up_2});
        animatedSprites.put("DOWN", new Sprite[]{Sprite.player_down, Sprite.player_down_1, Sprite.player_down_2});
        animatedSprites.put("DESTROYED", new Sprite[]{Sprite.player_dead1, Sprite.player_dead2, Sprite.player_dead3});
        currentAnimate = animatedSprites.get("RIGHT");
        direction = 2;
    }

    @Override
    public void checkCollision() {
        for (Character character: gameMap.characters) {
            if (this.isCollision(character) && character instanceof Enemy) {
                destroy();
            }
        }
        for (Item item: gameMap.items) {
            if (this.isCollision(item)) {
                item.powerUp(this);
                item.destroy();
            }
        }
        super.checkCollision();
        if (!isMovable) {
            for (int i = -4; i <= 4; i++) {
                this.pixelX += i * dx[direction];
                this.pixelY += i * dy[direction];
                super.checkCollision();
                if (isMovable) {
                    break;
                } else {
                    this.pixelX -= i * dx[direction];
                    this.pixelY -= i * dy[direction];
                }
            }
        }
    }

    //TODO: Smoother Move
    @Override
    public void smootherMove() {
        mapX = pixelX / Sprite.SCALED_SIZE;
        mapY = pixelY / Sprite.SCALED_SIZE;
    }

    @Override
    public void handleKeyInput() {
        this.setVelocity(0, 0);
        if (KeyInput.keyInput.get("LEFT")) {
            this.setVelocity(-defaultVelocity,0);
            currentAnimate = animatedSprites.get("LEFT");
            direction = 1;
        }
        if (KeyInput.keyInput.get("RIGHT")) {
            this.setVelocity(defaultVelocity, 0);
            currentAnimate = animatedSprites.get("RIGHT");
            direction = 2;
        }
        if (KeyInput.keyInput.get("UP")) {
            this.setVelocity(0, -defaultVelocity);
            currentAnimate = animatedSprites.get("UP");
            direction = 3;
        }
        if (KeyInput.keyInput.get("DOWN")) {
            this.setVelocity(0, defaultVelocity);
            currentAnimate = animatedSprites.get("DOWN");
            direction = 0;
        }
        if (KeyInput.keyInput.get("SPACE")) {
            createBomb();
            KeyInput.keyInput.put("SPACE", false);
        }
    }

    private void createBomb() {
        if (gameMap.bombs.size() == maxBombs) {
            return;
        }
        Bomb bomb = new Bomb(this.mapX, this.mapY, Sprite.bomb);
        gameMap.bombs.add(bomb);
    }

    @Override
    public void delete() {
        super.delete();
    }
}
