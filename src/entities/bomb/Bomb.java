package entities.bomb;

import entities.bean.AnimateEntity;
import entities.bean.Entity;
import entities.still.Brick;
import entities.still.Wall;
import input.Sound;
import map.GameMap;
import sprite.Sprite;
import javafx.scene.canvas.GraphicsContext;

public class Bomb extends AnimateEntity {

    private Explosion[][] explosion;
    public int ticTac = 200;
    public boolean isExploded = false;

    public Bomb(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        animatedSprites.put("BOMB", new Sprite[]{Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2});
        animatedSprites.put("DESTROYED", new Sprite[]{Sprite.bomb_exploded, Sprite.bomb_exploded1, Sprite.bomb_exploded2});
        currentAnimate = animatedSprites.get("BOMB");
    }

    private void buildExplosion() {
        explosion = new Explosion[5][4];
        explosion[4][0] = new Explosion(tileX, tileY, Sprite.bomb_exploded, "CENTER");
        int[] dx = new int[]{0, 0, 1, -1};
        int[] dy = new int[]{1, -1, 0, 0};
        for (int direction = 0; direction < 4; direction++) {
            for (int i = 0; i < GameMap.player_1.lengthBomb; i++) {
                int tileX = this.tileX + (i + 1) * dx[direction];
                int tileY = this.tileY + (i + 1) * dy[direction];
                if (tileX < 0 || tileX >= gameMap.WIDTH || tileY < 0 || tileY >= gameMap.HEIGHT) {
                    break;
                }
                Entity entity = gameMap.tiles[tileX][tileY];
                if (entity instanceof Wall) {
                    break;
                }
                if (direction <= 1) {
                    explosion[direction][i] = new Explosion(tileX, tileY, Sprite.explosion_vertical, "VERTICAL");
                } else {
                    explosion[direction][i] = new Explosion(tileX, tileY, Sprite.explosion_horizontal, "HORIZONTAL");
                }
                if (i == GameMap.player_1.lengthBomb - 1) {
                    switch (direction) {
                        case 0:
                            explosion[direction][i] = new Explosion(tileX, tileY, Sprite.explosion_vertical_down_last, "DOWN_LAST");
                            break;
                        case 1:
                            explosion[direction][i] = new Explosion(tileX, tileY, Sprite.explosion_vertical_top_last, "TOP_LAST");
                            break;
                        case 2:
                            explosion[direction][i] = new Explosion(tileX, tileY, Sprite.explosion_horizontal_right_last, "RIGHT_LAST");
                            break;
                        case 3:
                            explosion[direction][i] = new Explosion(tileX, tileY, Sprite.explosion_horizontal_left_last, "LEFT_LAST");
                            break;
                    }
                }
                if (entity instanceof Brick) {
                    break;
                }
            }
        }
        Sound.playSound("Explosion");
    }

    @Override
    public void update() {
        updateAnimation();
        ticTac -= 1;
        if (ticTac == 0) {
            isExploded = true;
            boom();
            buildExplosion();
        }
        if (ticTac == -10) {
            delete();
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(img, pixelX, pixelY);
        if (isDestroyed) {
            for (int direction = 0; direction < 4; direction++) {
                for (int i = 0; i < GameMap.player_1.lengthBomb; i++) {
                    if (explosion[direction][i] != null) {
                        explosion[direction][i].update();
                        explosion[direction][i].render(gc);
                    }
                }
            }
            explosion[4][0].update();
        }
    }

    @Override
    public void delete() {
        gameMap.bombs.remove(this);
    }
}
