package entities.still;

import entities.bean.Item;
import entities.character.Bomber;
import graphics.Sprite;

public class Portal extends Item {

    public Portal(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

    @Override
    public void powerUp(Bomber bomber) {
        gameMap.nextLevel();
    }

    @Override
    public void update() {

    }
}
