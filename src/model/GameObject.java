package model;

import java.awt.*;

public abstract class GameObject {
    private int x;
    private int y;
    private final int width;
    private final int height;

    public GameObject(int x, int y) {
        this.x = x;
        this.y = y;
        width = Model.FIELD_CELL_SIZE;
        height = Model.FIELD_CELL_SIZE;
    }

    public abstract void draw(Graphics graphics);

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
