package entities.bean;

import graphics.Sprite;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import map.GameMap;

public abstract class Entity {

    public GameMap gameMap = GameMap.getGameMap();

    protected int pixelX, pixelY;
    protected int tileX, tileY;
    protected Sprite sprite;
    protected Image img;
    public boolean isDestroyable = false;
    public boolean canBlock = false;
    public boolean isDestroyed = false;

    public Entity(int x, int y, Sprite sprite) {
        tileX = x;   tileY = y;
        this.pixelX = x * Sprite.SCALED_SIZE;
        this.pixelY = y * Sprite.SCALED_SIZE;
        this.sprite = sprite;
        this.img = sprite.getFxImage();
    }

    public int getPixelX() {
        return pixelX;
    }

    public int getPixelY() {
        return pixelY;
    }

    public Rectangle2D getBoundary() {
        return new Rectangle2D(pixelX, pixelY, Sprite.SCALED_SIZE, Sprite.SCALED_SIZE);
    }

    public boolean isCollision(Entity other) {
        return this.getBoundary().intersects(other.getBoundary());
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, pixelX, pixelY);
    }

    public void update() {}
}
