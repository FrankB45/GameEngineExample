package net.Andrewcpu.engine.display.elements;

import net.Andrewcpu.engine.world.Renderable;

import java.awt.*;

/**
 * Created by stein on 5/12/2016.
 */
public class Element implements Renderable{
    private int x = 0, y = 0;
    private boolean hidden = false;

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
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

    @Override
    public void draw(Graphics g) {

    }

}
