package entities.item;

import entities.bean.Item;
import entities.character.Bomber;
import sprite.Sprite;

public class BombPass extends Item {
    public BombPass(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

    @Override
    public void function(Bomber bomber) {
        bomber.isBombPass = true;
        destroy();
    }
}
