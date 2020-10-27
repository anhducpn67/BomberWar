package entities;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import graphics.Sprite;

public abstract class Entity {
    protected double x;
    protected double y;
    protected Sprite sprite;
    protected Image img;
    protected boolean isDestroyable = false;
    protected boolean isMovable = false;

    public Rectangle2D getBoundary() {
        return new Rectangle2D(x * Sprite.DEFAULT_SIZE, y * Sprite.DEFAULT_SIZE,
                Sprite.DEFAULT_SIZE, Sprite.DEFAULT_SIZE);
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

    public void setDestroyable(boolean destroyable) {
        isDestroyable = destroyable;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, x * Sprite.SCALED_SIZE, y * Sprite.SCALED_SIZE);
    }
    public abstract void update();
}
