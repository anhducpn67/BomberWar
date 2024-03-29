package entities.bean;

import entities.character.Bomber;
import entities.still.Brick;
import entities.still.Grass;
import input.Sound;
import javafx.scene.canvas.GraphicsContext;
import sprite.Sprite;

public abstract class Item extends StillEntity {

    public boolean isHidden = true;

    public Item(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        isDestroyable = true;
        gameMap.tiles[x][y] = new Brick(x, y, Sprite.brick);
        gameMap.items.add(this);
    }

    @Override
    public void render(GraphicsContext gc) {
        if (!isHidden) {
            gc.drawImage(img, pixelX, pixelY);
        }
    }

    public void destroy() {
        if (isHidden) {
            isHidden = false;
            Sound.playSound("ItemAppears");
            return;
        }
        gameMap.items.remove(this);
        gameMap.tiles[this.tileX][this.tileY] = new Grass(this.tileX, this.tileY, Sprite.grass);
    }

    abstract public void function(Bomber bomber);
}
