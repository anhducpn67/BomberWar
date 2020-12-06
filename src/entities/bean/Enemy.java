package entities.bean;

import input.Sound;
import map.Map;
import sprite.Sprite;
import trace.TraceStrategy;

abstract public class Enemy extends Character {

    public TraceStrategy traceStrategy;
    public int score;
    public int life = 1;

    public Enemy(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        setSpeciality();
    }

    @Override
    public void getDirection() {
        direction =  traceStrategy.trace(this, Map.player_1);
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
            currentAnimate = animatedSprites.get("UP");
        }
        if (direction == 3) {
            this.setVelocity(0, defaultVelocity);
            currentAnimate = animatedSprites.get("DOWN");
        }
        if (direction == 4) {
            this.setVelocity(0, 0);
        }
    }

    @Override
    public void delete() {
        life--;
        if (life != 0) {
            return;
        }
        gameMap.characters.remove(this);
        if (gameMap.characters.size() == 1) {
            Sound.backgroundSound.stop();
            Sound.stageCleared.setCycleCount(999);
            Sound.stageCleared.play();
        }
        Map.score += score;
        Sound.playSound("EnemyDie");
    }

    abstract public void setSpeciality();
}
