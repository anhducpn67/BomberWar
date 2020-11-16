package entities.bomb;

import entities.BombermanGame;
import entities.bean.AnimateEntity;
import entities.bean.Entity;
import entities.still.Brick;
import entities.still.Wall;
import graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;

public class Bomb extends AnimateEntity {
    private Explosion[] explosionRight, explosionLeft, explosionUp, explosionDown;
    public static int length = 1;
    final long timeCreate;

    public Bomb(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        this.timeCreate = System.nanoTime();
        animatedSprites.put("BOMB", new Sprite[]{Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2});
        animatedSprites.put("DESTROYED", new Sprite[]{Sprite.bomb_exploded, Sprite.bomb_exploded1, Sprite.bomb_exploded2});
        canBlock = false;
        isDangerous = true;
        currentAnimate = animatedSprites.get("BOMB");
        explosionRight = new Explosion[5];
        explosionLeft = new Explosion[5];
        explosionDown = new Explosion[5];
        explosionUp = new Explosion[5];
        for (int i = 0; i < length; i++) {
            if (this.mapX + i + 1 >= BombermanGame.WIDTH) {
                break;
            }
            Entity entity = gameMap.map[this.mapX + i + 1][this.mapY];
            if (entity instanceof Wall) {
                break;
            }
            explosionRight[i] = new Explosion(this.mapX + i + 1, this.mapY,
                    Sprite.explosion_horizontal, "HORIZONTAL");
            if (entity instanceof Brick) {
                break;
            }
            if (i == length - 1) {
                explosionRight[length - 1] = new Explosion(this.mapX + length, this.mapY,
                        Sprite.explosion_horizontal_right_last1, "RIGHT_LAST");
            }
        }
        for (int i = 0; i < length; i++) {
            if (this.mapX - i - 1 >= 0 && !(gameMap.map[this.mapX - i - 1][this.mapY] instanceof Wall)) {
                explosionLeft[i] = new Explosion(this.mapX - i - 1, this.mapY,
                        Sprite.explosion_horizontal, "HORIZONTAL");
                if (gameMap.map[this.mapX - i - 1][this.mapY] instanceof Brick) {
                    break;
                }
            } else break;
            if (i == length - 1) {
                if (this.mapX - length >= 0 && !(gameMap.map[this.mapX - length][this.mapY] instanceof Wall)) {
                    explosionLeft[length - 1] = new Explosion(this.mapX - length, this.mapY,
                            Sprite.explosion_horizontal_right_last1, "LEFT_LAST");
                }
            }
        }
        for (int i = 0; i < length; i++) {
            if (this.mapY - i - 1 >= 0 && !(gameMap.map[this.mapX][this.mapY - i - 1] instanceof Wall)) {
                explosionUp[i] = new Explosion(this.mapX, this.mapY - i - 1,
                        Sprite.explosion_vertical, "VERTICAL");
                if (gameMap.map[this.mapX][this.mapY - i - 1] instanceof Brick) {
                    break;
                }
            } else break;
            if (i == length - 1) {
                if (this.mapY - length >= 0 && !(gameMap.map[this.mapX][this.mapY - length] instanceof Wall)) {
                    explosionUp[length - 1] = new Explosion(this.mapX, this.mapY - length,
                            Sprite.explosion_horizontal_right_last1, "UP_LAST");
                }
            }
        }
        for (int i = 0; i < length; i++) {
            if (this.mapY + i + 1 < BombermanGame.HEIGHT && !(gameMap.map[this.mapX][this.mapY + i + 1] instanceof Wall)) {
                explosionDown[i] = new Explosion(this.mapX, this.mapY + i + 1,
                        Sprite.explosion_vertical, "VERTICAL");
                if (gameMap.map[this.mapX][this.mapY + i + 1] instanceof Brick) {
                    break;
                }
            } else break;
            if (i == length - 1) {
                if (this.mapY + length < BombermanGame.HEIGHT && !(gameMap.map[this.mapX][this.mapY + length] instanceof Wall)) {
                    explosionDown[length - 1] = new Explosion(this.mapX, this.mapY + length,
                            Sprite.explosion_horizontal_right_last1, "DOWN_LAST");
                }
            }
        }
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
        gc.drawImage(img, pixelX, pixelY);
        if (isDestroyed) {
            for (int i = 0; i < length; i++) {
                if (explosionRight[i] != null) {
                    explosionRight[i].update();
                    explosionRight[i].render(gc);
                }
                if (explosionLeft[i] != null) {
                    explosionLeft[i].update();
                    explosionLeft[i].render(gc);
                }
                if (explosionDown[i] != null) {
                    explosionDown[i].update();
                    explosionDown[i].render(gc);
                }
                if (explosionUp[i] != null) {
                    explosionUp[i].update();
                    explosionUp[i].render(gc);
                }
            }
        }
    }

    @Override
    public void delete() {
        gameMap.bombs.remove(this);
    }
}
