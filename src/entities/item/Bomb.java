package entities.item;

import entities.bean.Item;
import entities.character.Bomber;
import graphics.Sprite;

public class Bomb extends Item {

    public Bomb(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

    @Override
    public void function(Bomber bomber) {
        bomber.maxBombs += 1;
        destroy();
    }
}
