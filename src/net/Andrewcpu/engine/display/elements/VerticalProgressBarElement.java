package net.Andrewcpu.engine.display.elements;

import net.Andrewcpu.engine.display.Renderable;

import java.awt.*;

/**
 * Created by stein on 5/14/2016.
 */
public class VerticalProgressBarElement extends Element implements Renderable {
    private int width, height;
    private double value = 0;
    private Color background = Color.WHITE, foreground = Color.GREEN;
    public VerticalProgressBarElement(int x, int y, int width, int height) {
        setX(x);
        setY(y);
        this.width = width;
        this.height = height;
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

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Color getBackground() {
        return background;
    }

    public void setBackground(Color background) {
        this.background = background;
    }

    public Color getForeground() {
        return foreground;
    }

    public void setForeground(Color foreground) {
        this.foreground = foreground;
    }

    @Override
    public void draw(Graphics g) {
        double w = (double)width;
        double h = (double)height;
        double d = h * value;
        g.setColor(background);
        g.fillRect(getX(),getY(),getWidth(),getHeight());
        g.setColor(foreground);
        g.fillRect(getX(),getY(),getWidth(),(int)d);
    }
}
