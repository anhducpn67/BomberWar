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

    public void destroy() {
        if (isHidden) {
            isHidden = false;
            return;
        }
        gameMap.items.remove(this);
        gameMap.tiles[this.tileX][this.tileY] = new Grass(this.tileX, this.tileY, Sprite.grass);
    }

    abstract public void function(Bomber bomber);
}
