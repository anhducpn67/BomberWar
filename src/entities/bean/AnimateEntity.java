package entities.bean;

import entities.BombermanGame;
import graphics.Sprite;

import java.util.HashMap;

public abstract class AnimateEntity extends Entity {

    protected Sprite[] currentAnimate;
    protected long timeDestroy;
    public final HashMap<String, Sprite[]> animatedSprites = new HashMap<>();

    public AnimateEntity(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

    public void updateAnimated() {
        this.sprite = Sprite.movingSprite(currentAnimate, 3, BombermanGame.time);
        this.img = this.sprite.getFxImage();
    }

    @Override
    public void destroy() {
        currentAnimate = animatedSprites.get("DESTROYED");
        timeDestroy = System.nanoTime();
        isDestroyed = true;
    }

    @Override
    public void update() {
        if (isDestroyed) {
            final long currentTime = System.nanoTime();
            double timeDuration = (currentTime - timeDestroy) / 1000000000.0;
            if (timeDuration <= 0.3) {
                updateAnimated();
            } else {
                delete();
            }
        }
    }

    abstract public void delete();
}
