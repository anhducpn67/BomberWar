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
                new Speed(j, i, Sprite.powerUp_speed);
                break;
            case 'x':
                new Portal(j, i, Sprite.portal);
                break;
            case 'w':
                new WallPass(j, i, Sprite.powerUp_wallPass);
                break;
            case 'l':
                new BombPass(j, i, Sprite.powerUp_bombPass);
                break;
            case 'k':
                new FlamePass(j, i, Sprite.powerUp_flamePass);
                break;
        }
    }
}
