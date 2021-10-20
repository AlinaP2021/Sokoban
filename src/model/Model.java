package model;

import controller.EventListener;

import java.nio.file.Paths;
import java.util.Set;

public class Model {
    public static final int FIELD_CELL_SIZE = 20;
    private EventListener eventListener;
    private GameObjects gameObjects;
    private int currentLevel = 1;
    private final LevelLoader levelLoader = new LevelLoader(Paths.get("src/res/levels.txt"));
    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public GameObjects getGameObjects() {
        return gameObjects;
    }

    public void restartLevel(int level) {
        gameObjects = levelLoader.getLevel(level);
    }

    public void restart() {
        restartLevel(currentLevel);
    }

    public void startNextLevel() {
        currentLevel++;
        restart();
    }

    public void move(Direction direction) {
        Player player = gameObjects.getPlayer();
        if (checkWallCollision(player, direction)) return;
        if (checkBoxCollisionAndMoveIfAvailable(direction)) return;
        switch (direction) {
            case RIGHT -> player.move(Model.FIELD_CELL_SIZE, 0);
            case LEFT -> player.move(-Model.FIELD_CELL_SIZE, 0);
            case UP -> player.move(0, -Model.FIELD_CELL_SIZE);
            case DOWN -> player.move(0, Model.FIELD_CELL_SIZE);
        }
        checkCompletion();
    }

    public boolean checkWallCollision(CollisionObject gameObject, Direction direction) {
        Set<Wall> walls = gameObjects.getWalls();
        for (Wall wall : walls) {
            if (gameObject.isCollision(wall, direction)) return true;
        }
        return false;
    }

    public boolean checkBoxCollisionAndMoveIfAvailable(Direction direction) {
        Player player = gameObjects.getPlayer();
        Set<Box> boxes = gameObjects.getBoxes();
        for (Box box : boxes) {
            if (player.isCollision(box, direction)) {
                if (checkWallCollision(box, direction)) return true;
                else {
                    for (Box box1 : boxes) {
                        if (box.isCollision(box1, direction)) return true;
                    }
                }
                switch (direction) {
                    case RIGHT -> box.move(Model.FIELD_CELL_SIZE, 0);
                    case LEFT -> box.move(-Model.FIELD_CELL_SIZE, 0);
                    case UP -> box.move(0, -Model.FIELD_CELL_SIZE);
                    case DOWN -> box.move(0, Model.FIELD_CELL_SIZE);
                }
                return false;
            }
        }
        return false;
    }

    public void checkCompletion() {
        Set<Box> boxes = gameObjects.getBoxes();
        Set<Home> homes = gameObjects.getHomes();
        boolean contained = false;
        for (Box box : boxes) {
            for (Home home : homes) {
                if (box.getX() == home.getX() && box.getY() == home.getY()) {
                    contained = true;
                    break;
                }
            }
            if (!contained) return;
            contained = false;
        }
        eventListener.levelCompleted(currentLevel);
    }
}
