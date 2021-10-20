package view;

import controller.EventListener;
import model.Direction;
import model.GameObject;
import model.GameObjects;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Field extends JPanel {
    private final View view;
    private EventListener eventListener;

    public Field(View view) {
        this.view = view;
        KeyHandler keyHandler = new KeyHandler();
        addKeyListener(keyHandler);
        setFocusable(true);
    }

    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        GameObjects gameObjects = view.getGameObjects();
        for (GameObject gameObject : gameObjects.getHomes()) {
            gameObject.draw(g);
        }
        for (GameObject gameObject : gameObjects.getWalls()) {
            gameObject.draw(g);
        }
        for (GameObject gameObject : gameObjects.getBoxes()) {
            gameObject.draw(g);
        }
        gameObjects.getPlayer().draw(g);
    }

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public class KeyHandler extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT -> eventListener.move(Direction.LEFT);
                case KeyEvent.VK_RIGHT -> eventListener.move(Direction.RIGHT);
                case KeyEvent.VK_UP -> eventListener.move(Direction.UP);
                case KeyEvent.VK_DOWN -> eventListener.move(Direction.DOWN);
                case KeyEvent.VK_R -> eventListener.restart();
            }
        }
    }
}
