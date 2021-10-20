package model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player extends CollisionObject implements Movable{
    public Player(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics) {
        try {
            BufferedImage image = ImageIO.read(new File("src/res/player.png"));
            graphics.drawImage(image, getX(), getY(), getWidth(), getHeight(), Color.BLACK, null);
        } catch (IOException e) {
            graphics.setColor(Color.YELLOW);
            graphics.fillOval(getX(), getY(), getWidth(), getHeight());
        }
    }

    @Override
    public void move(int x, int y) {
        this.setX(this.getX() + x);
        this.setY(this.getY() + y);
    }
}
