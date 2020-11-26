package entities.item;

import sprite.Sprite;

public class ItemFactory {

    private ItemFactory() {
    }

    public static void getItem(char ch, int i, int j) {
        switch (ch) {
            case 'f':
                new Flame(j, i, Sprite.powerUp_flames);
                break;
            case 'b':
                new Bomb(j, i, Sprite.powerUp_bombs);
                break;
            case 's':
                new Speed(j, i, Sprite.powerup_speed);
                break;
            case 'x':
                new Portal(j, i, Sprite.portal);
                break;
        }
    }
}
