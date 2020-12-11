package trace;

import entities.bean.Enemy;
import entities.character.Bomber;
import entities.still.Grass;
import sprite.Sprite;

import java.util.LinkedList;
import java.util.Queue;

import static main.BombermanGame.gameMap;

public class BFSTrace implements TraceStrategy {

    private final int[] dx = {-1, 1, 0, 0, 0};
    private final int[] dy = {0, 0, -1, 1, 0};

    @Override
    public int trace(Enemy enemy, Bomber player) {

        if ((enemy.pixelX % Sprite.SCALED_SIZE) != 0 || (enemy.pixelY % Sprite.SCALED_SIZE != 0)) {
            return enemy.direction;
        }

        if (getDistance(enemy, player) <= 2 * (Sprite.SCALED_SIZE * Sprite.SCALED_SIZE)) {
            return new DistanceTrace().trace(enemy, player);
        }


        int enemyX = enemy.tileX, enemyY = enemy.tileY;
        Queue<Integer> direc = new LinkedList<>();
        Queue<Integer> tileX = new LinkedList<>();
        Queue<Integer> tileY = new LinkedList<>();
        boolean[][] checkPass = new boolean[gameMap.WIDTH][gameMap.HEIGHT];

        System.out.println("Start  " + enemyX + "  " + enemyY);
        System.out.print("pixel  " + enemy.pixelX + "  " + enemy.pixelY + "\n");

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
                System.out.println(player.tileX + " " + player.tileY + "  " + direcFirst + "\n");
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

    private int getDistance(Enemy enemy, Bomber player) {
        return (enemy.pixelX - player.pixelX) * (enemy.pixelX - player.pixelX)
                + (enemy.pixelY - player.pixelY) * (enemy.pixelY - player.pixelY);
    }
}
