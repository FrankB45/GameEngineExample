package net.Andrewcpu.tests.realgm.entities;

import net.Andrewcpu.engine.world.Entity;
import net.Andrewcpu.tests.realgm.Main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

/**
 * Created by stein on 5/14/2016.
 */
public class Player extends Entity{
    private PlayerState direction = PlayerState.FORWARD;
    public Player(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public PlayerState getDirection() {
        return direction;
    }

    public void setDirection(PlayerState direction) {
        this.direction = direction;
    }

    @Override
    public void draw(Graphics g) {
        try {
            g.drawImage(ImageIO.read(Main.class.getResourceAsStream("res/fairy1.png")),getX(),getY(),null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
