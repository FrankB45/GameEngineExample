package net.Andrewcpu.tests.world.entities;

import net.Andrewcpu.engine.world.Entity;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by stein on 5/10/2016.
 */
public class Player extends Entity {
    private Color color = Color.WHITE;
    private Point target = new Point(0,0);
    private int speed = 1;
    private int health = 500, maxHealth = 500;
    private java.util.List<Integer> keyCodes = new ArrayList<>();

    public void pressKey(int i){
        keyCodes.add(Integer.valueOf(i));
    }
    public void releaseKey(int i){
        keyCodes.remove(Integer.valueOf(i));
    }
    public boolean isPressing(int i){
        return keyCodes.contains(Integer.valueOf(i));
    }

    public List<Integer> getKeyCodes() {
        return keyCodes;
    }

    public void setKeyCodes(List<Integer> keyCodes) {
        this.keyCodes = keyCodes;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

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

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public void tick(){
        if(speed>0){
            if(getX()<target.getX()){
                setX(getX()+speed);
            }
            if(getX()>target.getX()){
                setX(getX()-speed);
            }
            if(getY()>target.getY()){
                setY(getY()-speed);
            }
            if(getY()<target.getY()){
                setY(getY()+speed);
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(getColor());
        super.draw(g);
    }
}
