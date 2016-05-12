package net.Andrewcpu.tests.game.entities;

import net.Andrewcpu.engine.Engine;
import net.Andrewcpu.engine.world.Entity;
import net.Andrewcpu.engine.world.World;

import java.awt.*;

/**
 * Created by stein on 5/12/2016.
 */
public class Ball extends Entity{
    private Color color = Color.BLACK;
    public Ball(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void tick(){
        down(5);
        if(getY() + getHeight() > Engine.getHEIGHT())
            setY(0 - getHeight());
    }
    @Override
    public void draw(Graphics g){
        g.setColor(getColor());
        g.fillOval(getX(),getY(),getWidth(),getHeight());
    }
}
