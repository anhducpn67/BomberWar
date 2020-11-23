package entities.item;

import entities.bean.Item;
import graphics.Sprite;

public class ItemFactory {

    private ItemFactory() {
    }

    public static Item getItem(char ch, int i, int j) {
        switch (ch) {
            case 'f':
                return new Flame(j, i, Sprite.powerUp_flames);
            case 'b':
                return new entities.item.Bomb(j, i, Sprite.powerUp_bombs);
            case 's':
                return new entities.item.Speed(j, i, Sprite.powerup_speed);
            default:
                throw new IllegalArgumentException("Item is invalid!");
        }
    }
}
