package model;

public abstract class CollisionObject extends GameObject {
    public CollisionObject(int x, int y) {
        super(x, y);
    }

    public boolean isCollision(GameObject gameObject, Direction direction) {
        if (direction == Direction.UP) {
            if (this.getX() == gameObject.getX() && this.getY() - Model.FIELD_CELL_SIZE == gameObject.getY()) return true;
        } else if (direction == Direction.RIGHT) {
            if (this.getX() + Model.FIELD_CELL_SIZE == gameObject.getX() && this.getY() == gameObject.getY()) return true;
        } else if (direction == Direction.LEFT) {
            if (this.getX() - Model.FIELD_CELL_SIZE == gameObject.getX() && this.getY() == gameObject.getY()) return true;
        } else if (direction == Direction.DOWN) {
            if (this.getX() == gameObject.getX() && this.getY() + Model.FIELD_CELL_SIZE == gameObject.getY()) return true;
        }
        return false;
    }
}
