package trace;

import entities.bean.Enemy;
import entities.character.Bomber;
import entities.still.Grass;
import sprite.Sprite;

import java.util.LinkedList;
import java.util.Queue;

public class BFSTrace implements TraceStrategy {

    @Override
    public int trace(Enemy enemy, Bomber player) {
        if (getDistance(enemy, player) > distanceTarget) {
            return new RandomTrace().trace(enemy, player);
        }
        if (getDistance(enemy, player) <= 10 * (Sprite.SCALED_SIZE * Sprite.SCALED_SIZE)) {
            return new DistanceTrace().trace(enemy, player);
        }
        if ((enemy.pixelX % Sprite.SCALED_SIZE) != 0 || (enemy.pixelY % Sprite.SCALED_SIZE != 0)) {
            return enemy.direction;
        }
        int enemyX = enemy.tileX, enemyY = enemy.tileY;
        Queue<Integer> direc = new LinkedList<>();
        Queue<Integer> tileX = new LinkedList<>();
        Queue<Integer> tileY = new LinkedList<>();
        boolean[][] checkPass = new boolean[gameMap.WIDTH][gameMap.HEIGHT];
        for (int i = 0; i < gameMap.WIDTH; i++) {
            for (int j = 0; j < gameMap.HEIGHT; j++) {
                checkPass[i][j] = false;
            }
        }
        checkPass[enemyX][enemyY] = true;
        for (int direction = 0; direction < 4; ++direction) {
            enemyX += dx[direction];
            enemyY += dy[direction];
            if (enemyX >= 0 && enemyY >= 0 && (gameMap.tiles[enemyX][enemyY] instanceof Grass || gameMap.tiles[enemyX][enemyY] instanceof Bomber)) {
                direc.add(direction);
                tileX.add(enemyX);
                tileY.add(enemyY);
                checkPass[enemyX][enemyY] = true;
            }
            enemyX -= dx[direction];
            enemyY -= dy[direction];
        }
        while (!direc.isEmpty()) {
            int X = tileX.poll(), Y = tileY.poll(), direcFirst = direc.poll();
            if ((X == player.tileX) && (Y == player.tileY)) {
                return direcFirst;
            }
            for (int direction = 0; direction < 5; ++direction) {
                X += dx[direction];
                Y += dy[direction];
                if (X >= 0 && Y >= 0 && !checkPass[X][Y] && (gameMap.tiles[X][Y] instanceof Grass || gameMap.tiles[X][Y] instanceof Bomber)) {
                    direc.add(direcFirst);
                    tileX.add(X);
                    tileY.add(Y);
                    checkPass[X][Y] = true;
                }
                X -= dx[direction];
                Y -= dy[direction];
            }
        }
        return new RandomTrace().trace(enemy, player);
    }
}
