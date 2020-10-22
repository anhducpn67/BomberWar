package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;


public class Bomber extends Entity {

    private int velocityX;
    private int velocityY;

    public Bomber(int x, int y, Image img) {
        super( x, y, img);
    }

    void setVelocity(int velocityX, int velocityY) {
        this.velocityX = velocityX;
        this.velocityY = velocityY;
    }

    @Override
    public void update() {
        this.setVelocity(0,0);
        if (BombermanGame.input.contains("LEFT"))
            this.setVelocity(-1,0);
        if (BombermanGame.input.contains("RIGHT"))
            this.setVelocity(1,0);
        if (BombermanGame.input.contains("UP"))
            this.setVelocity(0,-1);
        if (BombermanGame.input.contains("DOWN"))
            this.setVelocity(0,1);
        this.x += velocityX;
        this.y += velocityY;
    }
}
