package net.Andrewcpu.tests.world.entities;

import com.sun.corba.se.impl.orbutil.graph.Graph;
import net.Andrewcpu.engine.utils.Vector;
import net.Andrewcpu.engine.world.Entity;

import java.awt.*;

/**
 * Created by stein on 5/10/2016.
 */
public class Bullet extends Entity {
    private int x, y, width, height;
    private Vector slope = new Vector(0,0);

    public Bullet(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void setHeight(int height) {
        this.height = height;
    }

    public Vector getSlope() {
        return slope;
    }

    public void setSlope(Vector slope) {
        this.slope = slope;
    }

    @Override
    public void draw(Graphics g){
        g.setColor(Color.RED);
        g.fillRect(getX(),getY(),getWidth(),getHeight());
    }

    @Override
    public void tick(){
        setX(getX() + (int)slope.getX());
        setY(getY() + (int)slope.getY());
    }
}
