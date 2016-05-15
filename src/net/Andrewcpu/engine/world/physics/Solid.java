package net.Andrewcpu.engine.world.physics;

import net.Andrewcpu.engine.world.Renderable;

import java.awt.*;

/**
 * Created by stein on 5/14/2016.
 */
public class Solid implements Renderable{
    private int x, y, width, height;

    public Solid(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public Rectangle getBounds(){
        return new Rectangle(getX(),getY(),getWidth(),getHeight());
    }

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

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public void draw(Graphics g) {

    }
}
