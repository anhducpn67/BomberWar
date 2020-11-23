package entities.features;

public interface Movable {
    void setVelocity(int velocityX, int velocityY);

    void setSpeed(int speed);

    int getSpeed();

    void addVelocity(int velocityX, int velocityY);

    void move();
}
