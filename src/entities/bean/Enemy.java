package entities.bean;

import map.GameMap;
import sprite.Sprite;
import trace.TraceStrategy;

abstract public class Enemy extends Character {

    public TraceStrategy traceStrategy;

    public Enemy(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

    @Override
    public void getDirection() {
        direction =  traceStrategy.trace(this, gameMap.player_1);
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
        if (direction == 4) {
            this.setVelocity(0, 0);
        }
    }

    @Override
    public void delete() {
        gameMap.characters.remove(this);
        if (gameMap.characters.size() == 1) {
            GameMap.backgroundSound.stop();
            GameMap.stageCleared.setCycleCount(999);
            GameMap.stageCleared.play();
        }
    }
}
