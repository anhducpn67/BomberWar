package entities.item;

import entities.bean.Item;
import entities.character.Bomber;
import graphics.Sprite;

public class Portal extends Item {

    public Portal(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

    @Override
    public void function(Bomber bomber) {
        gameMap.nextLevel();
    }
}
