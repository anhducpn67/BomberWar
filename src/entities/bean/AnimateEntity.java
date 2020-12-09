package entities.bean;

import main.BombermanGame;
import sprite.Sprite;

import java.util.HashMap;

public abstract class AnimateEntity extends Entity {

    protected Sprite[] currentAnimate;
    private long timeDestroyed;
    public final HashMap<String, Sprite[]> animatedSprites = new HashMap<>();

    public AnimateEntity(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

    public void updateAnimation() {
        this.sprite = Sprite.movingSprite(currentAnimate, 3, BombermanGame.time);
        this.img = this.sprite.getFxImage();
    }

    public void destroy() {
        currentAnimate = animatedSprites.get("DESTROYED");
        timeDestroyed = 20;
        isDestroyed = true;
    }

    public void updateDestroyAnimation() {
        if (timeDestroyed-- >= 0) {
            updateAnimation();
        } else {
            delete();
        }
    }

    abstract public void delete();
}
