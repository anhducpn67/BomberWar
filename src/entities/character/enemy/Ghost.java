package entities.character.enemy;

import entities.bean.Enemy;
import javafx.scene.canvas.GraphicsContext;
import sprite.Sprite;
import trace.SpeedTrace;

public class Ghost extends Enemy {

    boolean isInvisible = true;

    public Ghost(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        animatedSprites.put("LEFT", new Sprite[]{Sprite.ghost_left1, Sprite.ghost_left2, Sprite.ghost_left3});
        animatedSprites.put("UP", new Sprite[]{Sprite.ghost_left1, Sprite.ghost_left2, Sprite.ghost_left3});
        animatedSprites.put("RIGHT", new Sprite[]{Sprite.ghost_right1, Sprite.ghost_right2, Sprite.ghost_right3});
        animatedSprites.put("DOWN", new Sprite[]{Sprite.ghost_right1, Sprite.ghost_right2, Sprite.ghost_right3});
        animatedSprites.put("DESTROYED", new Sprite[]{Sprite.mob_dead1, Sprite.mob_dead2, Sprite.mob_dead3});
        currentAnimate = animatedSprites.get("RIGHT");
    }

    @Override
    public void setSpeciality() {
        score = 5000;
        traceStrategy = new SpeedTrace();
        isWallPass = true;
    }

    @Override
    public void update() {
        super.update();
        java.util.Random rand = new java.util.Random();
        int random = rand.nextInt(500);
        isInvisible = random <= 300;
    }

    @Override
    public void render(GraphicsContext gc) {
        if (!isInvisible) {
            super.render(gc);
        }
    }
}
