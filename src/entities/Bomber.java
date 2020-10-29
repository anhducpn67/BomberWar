package entities;

import graphics.Sprite;
import input.KeyInput;
import map.GameMap;

import java.util.HashMap;


public class Bomber extends Character {
    private final HashMap<String, Sprite[]> animatedSprites = new HashMap<>();
    {
        animatedSprites.put("LEFT", new Sprite[]{Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2});
        animatedSprites.put("RIGHT", new Sprite[]{Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2});
        animatedSprites.put("UP", new Sprite[]{Sprite.player_up, Sprite.player_up_1, Sprite.player_up_2});
        animatedSprites.put("DOWN", new Sprite[]{Sprite.player_down, Sprite.player_down_1, Sprite.player_down_2});
    }

    public Bomber(int x, int y, double velocityX, double velocityY, Sprite sprite) {
        super( x, y, velocityX, velocityY, sprite);
    }

    @Override
    void handleKeyInput() {
        this.setVelocity(0,0);
        if (KeyInput.keyInput.get("LEFT")) {
            this.setVelocity(-defaultVelocity,0);
            currentAnimate = "LEFT";
        }
        if (KeyInput.keyInput.get("RIGHT")) {
            this.setVelocity(defaultVelocity, 0);
            currentAnimate = "RIGHT";
        }
        if (KeyInput.keyInput.get("UP")) {
            this.setVelocity(0, -defaultVelocity);
            currentAnimate = "UP";
        }
        if (KeyInput.keyInput.get("DOWN")) {
            this.setVelocity(0, defaultVelocity);
            currentAnimate = "DOWN";
        }
        if (KeyInput.keyInput.get("SPACE")) {
            createBomb();
        }
    }

    private void createBomb() {

    }

    @Override
    public void update(GameMap gameMap) {
        handleKeyInput();
        checkCollision(gameMap);
        updateAnimated();
        if (isMovable) {
            move();
        }
    }

    private void updateAnimated() {
        this.sprite = Sprite.movingSprite(animatedSprites.get(currentAnimate), 3, BombermanGame.time);
        this.img = this.sprite.getFxImage();
    }
}
