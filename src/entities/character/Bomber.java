package entities.character;

import entities.bean.Character;
import entities.bean.Enemy;
import entities.bean.Item;
import entities.bomb.Bomb;
import graphics.Sprite;
import input.KeyInput;

public class Bomber extends Character {

    public int maxBombs = 3;

    public Bomber(int x, int y, double velocityX, double velocityY, Sprite sprite) {
        super( x, y, velocityX, velocityY, sprite);
        animatedSprites.put("LEFT", new Sprite[]{Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2});
        animatedSprites.put("RIGHT", new Sprite[]{Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2});
        animatedSprites.put("UP", new Sprite[]{Sprite.player_up, Sprite.player_up_1, Sprite.player_up_2});
        animatedSprites.put("DOWN", new Sprite[]{Sprite.player_down, Sprite.player_down_1, Sprite.player_down_2});
        animatedSprites.put("DESTROYED", new Sprite[]{Sprite.player_dead1, Sprite.player_dead2, Sprite.player_dead3});
        currentAnimate = animatedSprites.get("RIGHT");
    }

    @Override
    public void checkCollision() {
        super.checkCollision();
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
    }

    @Override
    public void handleKeyInput() {
        this.setVelocity(0,0);
        if (KeyInput.keyInput.get("LEFT")) {
            this.setVelocity(-defaultVelocity,0);
            currentAnimate = animatedSprites.get("LEFT");
            KeyInput.keyInput.put("LEFT", false);
        }
        if (KeyInput.keyInput.get("RIGHT")) {
            this.setVelocity(defaultVelocity, 0);
            currentAnimate = animatedSprites.get("RIGHT");
            KeyInput.keyInput.put("RIGHT", false);
        }
        if (KeyInput.keyInput.get("UP")) {
            this.setVelocity(0, -defaultVelocity);
            currentAnimate = animatedSprites.get("UP");
            KeyInput.keyInput.put("UP", false);
        }
        if (KeyInput.keyInput.get("DOWN")) {
            this.setVelocity(0, defaultVelocity);
            currentAnimate = animatedSprites.get("DOWN");
            KeyInput.keyInput.put("DOWN", false);
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
        Bomb bomb = new Bomb(this.x, this.y, Sprite.bomb);
        gameMap.bombs.add(bomb);
    }

    @Override
    public void delete() {
        super.delete();
    }
}
