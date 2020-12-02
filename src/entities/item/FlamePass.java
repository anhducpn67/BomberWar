package entities.item;

import entities.bean.Item;
import entities.character.Bomber;
import sprite.Sprite;

public class FlamePass extends Item {

    public FlamePass(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

    @Override
    public void function(Bomber bomber) {
        bomber.isFlamePass = true;
        destroy();
    }
}
