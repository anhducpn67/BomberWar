package trace;

import entities.bean.Enemy;
import entities.character.Bomber;
import sprite.Sprite;

public class DistanceTrace implements TraceStrategy {

    private final int[] dx = {-1, 1, 0, 0, 0};
    private final int[] dy = {0, 0, -1, 1, 0};

    @Override
    public int trace(Enemy enemy, Bomber player) {
        if (getDistance(enemy, player) > 50000) {
            return new RandomTrace().trace(enemy, player);
        }
        int minDistance = 999999999;
        int rightDirection = -1;
        for (int direction = 0; direction < 5; direction++) {
            enemy.pixelX = enemy.pixelX + dx[direction];
            enemy.pixelY = enemy.pixelY + dy[direction];
            enemy.setVelocity(0, 0);
            enemy.checkCollision();
            if (!enemy.isCollision && (enemy.pixelX % Sprite.SCALED_SIZE == 0 || enemy.pixelY % Sprite.SCALED_SIZE == 0)) {
                int distance = getDistance(enemy, player);
                if (distance < minDistance) {
                    minDistance = distance;
                    rightDirection = direction;
                }
            }
            enemy.pixelX = enemy.pixelX - dx[direction];
            enemy.pixelY = enemy.pixelY - dy[direction];
        }
        if (rightDirection == -1) {
            rightDirection = new RandomTrace().trace(enemy, player);
        }
        return rightDirection;
    }

    private int getDistance(Enemy enemy, Bomber player) {
        return (enemy.pixelX - player.pixelX) * (enemy.pixelX - player.pixelX)
                + (enemy.pixelY - player.pixelY) * (enemy.pixelY - player.pixelY);
    }
}
