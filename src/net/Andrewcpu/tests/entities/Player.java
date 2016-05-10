package net.Andrewcpu.tests.entities;

import net.Andrewcpu.engine.world.Entity;

import java.awt.*;

/**
 * Created by stein on 5/10/2016.
 */
public class Player extends Entity {
    private Color color = Color.WHITE;
    private Point target = new Point(0,0);
    public Player(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Point getTarget() {
        return target;
    }

    public void setTarget(Point target) {
        this.target = target;
    }
}
