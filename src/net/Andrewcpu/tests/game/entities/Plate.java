package net.Andrewcpu.tests.game.entities;

import net.Andrewcpu.engine.world.Entity;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by stein on 5/12/2016.
 */
public class Plate extends Entity {
    private List<Integer> keys = new ArrayList<>();
    private int speed = 10;
    private Color color = Color.BLACK;
    public Plate(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void pressKey(int key){
        while(keys.contains(key)){
            keys.remove(Integer.valueOf(key));
        }
        keys.add(key);
    }
    public void releaseKey(int key){
        while(keys.contains(key)){
            keys.remove(Integer.valueOf(key));
        }
    }
    public boolean isPressing(int key){
        return keys.contains(key);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(getColor());
        super.draw(g);
    }
}
