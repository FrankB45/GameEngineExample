package net.Andrewcpu.engine.display.elements;

import net.Andrewcpu.engine.display.Clickable;

import java.awt.*;

/**
 * Created by stein on 5/15/2016.
 */
public class ButtonElement extends Element implements Clickable {
    private Color foreground = Color.GREEN, textColor = Color.WHITE;
    private int width, height;
    private String text;
    private Runnable runnable;
    public ButtonElement(int x, int y, int width, int height, String text, Runnable action) {
        setX(x);
        setY(y);
        setWidth(width);
        setHeight(height);
        setText(text);
        runnable = action;
    }

    public Color getForeground() {
        return foreground;
    }

    public void setForeground(Color foreground) {
        this.foreground = foreground;
    }

    public Color getTextColor() {
        return textColor;
    }

    public void setTextColor(Color textColor) {
        this.textColor = textColor;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(getX(),getY(),getWidth(),getHeight());
    }

    @Override
    public void onClick() {
        getAction().run();
    }

    @Override
    public Runnable getAction() {
        return runnable;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(getForeground());
        g.fill3DRect(getX(),getY(),getWidth(),getHeight(),true);
        g.setColor(getTextColor());
        g.drawString(getText(),getX() + 1, getY() + (int)((double)getHeight()/1.5));
    }
}
