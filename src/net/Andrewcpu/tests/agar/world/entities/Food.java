package net.Andrewcpu.tests.agar.world.entities;

import net.Andrewcpu.engine.world.Entity;

import java.awt.*;

/**
 * Created by stein on 5/12/2016.
 */
public class Food extends Entity {
    private int value = 0;
    public Food(int x, int y, int width, int height, int value) {
        super(x, y, width, height);
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.CYAN);
        g.fillOval(getX(),getY(),getWidth(),getHeight());
    }
}
