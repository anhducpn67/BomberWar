package entities.item;

import entities.bean.Item;
import entities.character.Bomber;
import sprite.Sprite;

public class Flame extends Item {

    public Flame(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

    @Override
    public void function(Bomber player) {
        player.lengthBomb += 1;
        destroy();
    }
}
