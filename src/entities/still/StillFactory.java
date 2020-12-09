package entities.still;

import sprite.Sprite;

public class StillFactory {
    private StillFactory() {
    }

    public static void getStill(char ch, int i, int j) {
        switch (ch) {
            case '#':
                new Wall(j, i, Sprite.wall);
                break;
            case '*':
                new Brick(j, i, Sprite.brick);
                break;
            default:
                new Grass(j, i, Sprite.grass);
                break;
        }
    }
}
