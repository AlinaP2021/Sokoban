package model;

import java.awt.*;

public class Box extends CollisionObject implements Movable {
    public Box(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.ORANGE);
        graphics.fill3DRect(getX(), getY(), getWidth(), getHeight(), true);
        graphics.setColor(Color.GRAY);
        graphics.drawLine(getX() + 1, getY() + 1, getX() + Model.FIELD_CELL_SIZE - 1, getY() + Model.FIELD_CELL_SIZE - 1);
        graphics.drawLine(getX() + 1, getY() + Model.FIELD_CELL_SIZE - 1, getX() + Model.FIELD_CELL_SIZE - 1, getY() + 1);
    }

    @Override
    public void move(int x, int y) {
        this.setX(this.getX() + x);
        this.setY(this.getY() + y);
    }
}
