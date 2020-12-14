package trace;

import entities.bean.Enemy;
import entities.character.Bomber;
import sprite.Sprite;

public class SpeedTrace implements TraceStrategy {
    private static int time = 0;

    @Override
    public int trace(Enemy enemy, Bomber player) {
        if ((enemy.pixelX % Sprite.SCALED_SIZE) != 0 || (enemy.pixelY % Sprite.SCALED_SIZE != 0)) {
            return enemy.direction;
        }
        ++time;
        if (time <= 7) {
            enemy.setSpeed(2);
            return new BFSvsDodge().trace(enemy, player);
        } else {
            enemy.setSpeed(1);
            time = time % 20;
            return new RandomTrace().trace(enemy, player);
        }
    }
}
