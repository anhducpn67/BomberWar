package entities.item;

import entities.bean.Item;
import entities.character.Bomber;
import sprite.Sprite;

public class Bomb extends Item {

    public Bomb(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

    @Override
    public void function(Bomber bomber) {
        if (bomber.maxBombs < 5) {
            bomber.maxBombs += 1;
        }
        destroy();
    }
}
