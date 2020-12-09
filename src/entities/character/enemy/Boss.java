package entities.character.enemy;

import entities.bean.Enemy;
import sprite.Sprite;
import trace.DistanceTrace;
import trace.RandomTrace;

public class Boss extends Enemy {

    public Boss(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        animatedSprites.put("LEFT", new Sprite[]{Sprite.boss_left1, Sprite.boss_left2, Sprite.boss_left3});
        animatedSprites.put("UP", new Sprite[]{Sprite.boss_up1, Sprite.boss_up2, Sprite.boss_up3});
        animatedSprites.put("RIGHT", new Sprite[]{Sprite.boss_right1, Sprite.boss_right2, Sprite.boss_right3});
        animatedSprites.put("DOWN", new Sprite[]{Sprite.boss_down1, Sprite.boss_down2, Sprite.boss_down3});
        animatedSprites.put("DESTROYED", new Sprite[]{Sprite.mob_dead1, Sprite.mob_dead2, Sprite.mob_dead3});
        currentAnimate = animatedSprites.get("RIGHT");
    }

    @Override
    public void setSpeciality() {
        traceStrategy = new DistanceTrace();
        score = 10000;
        isWallPass = true;
        life = 3;
    }

    @Override
    public void update() {
        super.update();
        java.util.Random rand = new java.util.Random();
        int random = rand.nextInt(101);
        if (random == 100) {
            fire();
        }
    }

    private void fire() {
        new Fire(this.tileX, this.tileY, Sprite.fire_down, this.direction);
    }

    @Override
    public void delete() {
        life -= 1;
        if (life == 0) {
            super.delete();
        }
    }
}

class Fire extends Enemy {
    public Fire(int x, int y, Sprite sprite, int direction) {
        super(x, y, sprite);
        this.direction = direction;
        animatedSprites.put("LEFT", new Sprite[]{Sprite.fire_left, Sprite.fire_left, Sprite.fire_left});
        animatedSprites.put("UP", new Sprite[]{Sprite.fire_up, Sprite.fire_up, Sprite.fire_up});
        animatedSprites.put("RIGHT", new Sprite[]{Sprite.fire_right, Sprite.fire_right, Sprite.fire_right});
        animatedSprites.put("DOWN", new Sprite[]{Sprite.fire_down, Sprite.fire_down, Sprite.fire_down});
    }

    @Override
    public void setSpeciality() {
        traceStrategy = new RandomTrace();
        speed = 2;
    }

    @Override
    public void delete() {
        gameMap.characters.remove(this);
    }

    @Override
    public void checkCollision() {
        super.checkCollision();
        if (isCollision) {
            delete();
        }
    }
}
