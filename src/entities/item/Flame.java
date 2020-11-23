package entities.item;

import entities.bean.Item;
import entities.bomb.Bomb;
import entities.character.Bomber;
import graphics.Sprite;

public class Flame extends Item {

    public Flame(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

    @Override
    public void function(Bomber bomber) {
        Bomb.length += 1;
    }
}
