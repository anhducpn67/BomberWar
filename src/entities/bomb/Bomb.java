package entities.bomb;

import entities.bean.AnimateEntity;
import graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;

public class Bomb extends AnimateEntity {
    private Explosion[] explosionRight, explosionLeft, explosionUp, explosionDown;
    private static int length = 3;
    final long timeCreate;

    public Bomb(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        this.timeCreate = System.nanoTime();
        animatedSprites.put("BOMB", new Sprite[]{Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2});
        animatedSprites.put("DESTROYED", new Sprite[]{Sprite.bomb_exploded, Sprite.bomb_exploded1, Sprite.bomb_exploded2});
        canBlock = true;
        isDangerous = true;
        currentAnimate = animatedSprites.get("BOMB");
        explosionRight = new Explosion[length];
        explosionLeft = new Explosion[length];
        explosionDown = new Explosion[length];
        explosionUp = new Explosion[length];
        for (int i = 0; i < length - 1; i++) {
            explosionRight[i] = new Explosion(this.x + i + 1, this.y,
                    Sprite.explosion_horizontal, "HORIZONTAL");
            explosionLeft[i] = new Explosion(this.x - i - 1, this.y,
                    Sprite.explosion_horizontal, "HORIZONTAL");
            explosionUp[i] = new Explosion(this.x, this.y - i - 1,
                    Sprite.explosion_vertical, "VERTICAL");
            explosionDown[i] = new Explosion(this.x, this.y + i + 1,
                    Sprite.explosion_vertical, "VERTICAL");
        }
        explosionRight[length - 1] = new Explosion(this.x + length, this.y,
                Sprite.explosion_horizontal_right_last1, "RIGHT_LAST");
        explosionLeft[length - 1] = new Explosion(this.x - length, this.y,
                Sprite.explosion_horizontal_right_last1, "LEFT_LAST");
        explosionUp[length - 1] = new Explosion(this.x, this.y - length,
                Sprite.explosion_horizontal_right_last1, "UP_LAST");
        explosionDown[length - 1] = new Explosion(this.x, this.y + length,
                Sprite.explosion_horizontal_right_last1, "DOWN_LAST");
    }

    @Override
    public void update() {
        updateAnimated();
        final long currentTime = System.nanoTime();
        double timeDuration = (currentTime - timeCreate) / 1000000000.0;
        if (timeDuration >= 2) {
            destroy();
        }
        if (timeDuration >= 2.3) {
            delete();
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(img, x * Sprite.SCALED_SIZE, y * Sprite.SCALED_SIZE);
        if (isDestroyed) {
            for (int i = 0; i < length; i++) {
                explosionRight[i].update();
                explosionLeft[i].update();
                explosionUp[i].update();
                explosionDown[i].update();
                explosionRight[i].render(gc);
                explosionLeft[i].render(gc);
                explosionUp[i].render(gc);
                explosionDown[i].render(gc);
            }
        }
    }

    @Override
    public void delete() {
        gameMap.bombs.remove(this);
    }
}
