package entities.bean;

import entities.character.Bomber;
import entities.still.Grass;
import graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;

public abstract class Item extends Entity {

    private boolean isHidden;

    public Item(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        isDestroyable = true;
        isHidden = true;
    }

    @Override
    public void render(GraphicsContext gc) {
        if (!isHidden) {
            super.render(gc);
        }
    }

    @Override
    public void destroy() {
        if (isHidden) {
            isHidden = false;
            return;
        }
        gameMap.items.remove(this);
        gameMap.map[this.x][this.y] = new Grass(this.x, this.y, Sprite.grass);
    }

    abstract public void powerUp(Bomber bomber);
}
