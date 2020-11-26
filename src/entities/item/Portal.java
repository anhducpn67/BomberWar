package entities.item;

import entities.bean.Character;
import entities.bean.Enemy;
import entities.bean.Item;
import entities.character.Bomber;
import sprite.Sprite;

public class Portal extends Item {

    public Portal(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

    @Override
    public void function(Bomber bomber) {
        if (countEnemies() == 0) {
            gameMap.nextLevel();
            destroy();
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        isDestroyable = false;
    }

    private int countEnemies() {
        int cntEnemies = 0;
        for (Character character: gameMap.characters) {
            if (character instanceof Enemy) {
                cntEnemies++;
            }
        }
        return cntEnemies;
    }
}
