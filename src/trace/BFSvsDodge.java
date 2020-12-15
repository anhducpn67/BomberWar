package trace;

import entities.bomb.Bomb;

public class BFSvsDodge extends BFSTrace {
    @Override
    public boolean CheckFaceBoom(int X, int Y, int lengthBomb) {
        for (Bomb bomb : gameMap.bombs) {
            if (bomb.tileX == X && Math.abs(bomb.tileY - Y) <= lengthBomb)
                return true;
            if (bomb.tileY == Y && Math.abs(bomb.tileX - X) <= lengthBomb)
                return true;
        }

        return false;
    }
}
