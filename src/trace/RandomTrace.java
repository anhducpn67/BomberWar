package trace;

import entities.bean.Enemy;
import entities.character.Bomber;

public class RandomTrace implements TraceStrategy {
    @Override
    public int trace(Enemy enemy, Bomber player) {
        if (enemy.isCollision) {
            java.util.Random rand = new java.util.Random();
            return rand.nextInt(4);
        } else {
            return enemy.direction;
        }
    }
}
