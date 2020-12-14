package trace;

import entities.bean.Enemy;
import entities.character.Bomber;
import sprite.Sprite;

public class DistanceTrace implements TraceStrategy {

    @Override
    public int trace(Enemy enemy, Bomber player) {
        if (getDistance(enemy, player) > distanceTarget) {
            return new RandomTrace().trace(enemy, player);
        }
        int currentDistanceFromBomb = getDistanceBomb(enemy);
        if (currentDistanceFromBomb < distanceBomb) {
            return new RandomTrace().trace(enemy, player);
        }
        int minDistance = getDistance(enemy, player);
        int rightDirection = -1;
        for (int direction = 0; direction < 4; direction++) {
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
}
