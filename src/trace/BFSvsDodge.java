package trace;

import entities.bean.Enemy;
import entities.bomb.Bomb;
import entities.character.Bomber;
import entities.still.Grass;
import sprite.Sprite;

import java.util.LinkedList;
import java.util.Queue;

public class BFSvsDodge implements TraceStrategy {

    @Override
    public int trace(Enemy enemy, Bomber player) {

        if ((enemy.pixelX % Sprite.SCALED_SIZE) != 0 || (enemy.pixelY % Sprite.SCALED_SIZE != 0)) {
            return enemy.direction;
        }

        if (getDistance(enemy, player) <= 2 * (Sprite.SCALED_SIZE * Sprite.SCALED_SIZE)) {
            return new DistanceTrace().trace(enemy, player);
        }


        int lengthBomb = player.lengthBomb + 1;
        Queue<Integer> direc = new LinkedList<>();
        Queue<Integer> tileX = new LinkedList<>();
        Queue<Integer> tileY = new LinkedList<>();
        boolean[][] checkPass = new boolean[gameMap.WIDTH][gameMap.HEIGHT];
        boolean CheckFaceBooms = CheckFaceBoom(enemy.tileX, enemy.tileY, lengthBomb);
        int directionDodge = -1;

        for (int i = 0; i < gameMap.WIDTH; i++) {
            for (int j = 0; j < gameMap.HEIGHT; j++) {
                checkPass[i][j] = false;
            }
        }
        checkPass[enemy.tileX][enemy.tileY] = true;

        for (int direction = 0; direction < 5; ++direction) {
            enemy.tileX += dx[direction];
            enemy.tileY += dy[direction];

            if (enemy.tileX >= 0 && enemy.tileY >= 0 && (gameMap.tiles[enemy.tileX][enemy.tileY] instanceof Grass || gameMap.tiles[enemy.tileX][enemy.tileY] instanceof Bomber)) {
                direc.add(direction);
                tileX.add(enemy.tileX);
                tileY.add(enemy.tileY);
                checkPass[enemy.tileX][enemy.tileY] = true;
            }
            enemy.tileX -= dx[direction];
            enemy.tileY -= dy[direction];
        }

        while (!direc.isEmpty()) {
            int X = tileX.poll(), Y = tileY.poll(), direcFirst = direc.poll();

            if (directionDodge == -1 && !CheckFaceBoom(enemy.tileX + dx[direcFirst], enemy.tileY + dy[direcFirst], lengthBomb))
                directionDodge = direcFirst;

            if (CheckFaceBooms && !CheckFaceBoom(X, Y, lengthBomb)) {
                return direcFirst;
            }

            if ((X == player.tileX) && (Y == player.tileY) && !CheckFaceBoom(enemy.tileX + dx[direcFirst], enemy.tileY + dy[direcFirst], lengthBomb)) {
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

        if (directionDodge == -1)
            return new RandomTrace().trace(enemy, player);
        else return directionDodge;
    }

    private boolean CheckFaceBoom(int X, int Y, int lengthBomb) {
        for (Bomb bomb : gameMap.bombs) {
            if (bomb.tileX == X && Math.abs(bomb.tileY - Y) <= lengthBomb)
                return true;
            if (bomb.tileY == Y && Math.abs(bomb.tileX - X) <= lengthBomb)
                return true;
        }

        return false;
    }
}
