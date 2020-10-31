package entities.bean;

import graphics.Sprite;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import map.GameMap;

public abstract class Entity {
    public GameMap gameMap = GameMap.getGameMap();

    protected int x;
    protected int y;
    protected Sprite sprite;
    protected Image img;
    public boolean isDestroyable = false;
    public boolean isMovable = false;
    public boolean canBlock = false;
    public boolean isDangerous = false;
    public boolean isDestroyed = false;

    public Rectangle2D getBoundary() {
        return new Rectangle2D(x * Sprite.SCALED_SIZE, y * Sprite.SCALED_SIZE,
                Sprite.SCALED_SIZE, Sprite.SCALED_SIZE);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isCollision(Entity other)
    {
        return this.getBoundary().intersects( other.getBoundary() );
    }

    public Entity(int x, int y, Sprite sprite) {
        this.x = x;
        this.y = y;
        this.sprite = sprite;
        this.img = sprite.getFxImage();
    }

    abstract public void destroy();

    public void render(GraphicsContext gc) {
        gc.drawImage(img, x * Sprite.SCALED_SIZE, y * Sprite.SCALED_SIZE);
    }
    public abstract void update();
}
