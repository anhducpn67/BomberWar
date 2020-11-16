package entities.item;

import entities.bean.Item;
import entities.character.Bomber;
import graphics.Sprite;

public class Speed extends Item {

    public Speed(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

    @Override
    public void powerUp(Bomber bomber) {
        bomber.setSpeed(bomber.getSpeed() + 1);
    }

    @Override
    public void update() {

    }
}