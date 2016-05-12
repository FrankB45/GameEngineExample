package net.Andrewcpu.tests.world.entities;

import com.sun.corba.se.impl.orbutil.graph.Graph;
import net.Andrewcpu.engine.Engine;
import net.Andrewcpu.engine.utils.Vector;
import net.Andrewcpu.engine.world.Entity;
import net.Andrewcpu.engine.world.World;

import java.awt.*;

/**
 * Created by stein on 5/10/2016.
 */
public class Bullet extends Entity {
    private int x, y, width, height, age = 0,maxAge = 2500;
    private Vector slope = new Vector(0,0);
    private double angle = 0;
    private Entity shooter = null;
    public Bullet(int x, int y, int width, int height, Entity shooter) {
        super(x, y, width, height);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.shooter = shooter;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

    public Entity getShooter() {
        return shooter;
    }

    public void setShooter(Entity shooter) {
        this.shooter = shooter;
    }

    public double getAngle() {
        return Math.toRadians(angle);
    }

    public void setAngle(double angle) {
        this.angle = angle;
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
        if(getX()<0 || getY()<0 || getX()> Engine.getWIDTH() || getY()> Engine.getHEIGHT()){

        }
        else{
            g.setColor(Color.RED);
            g.fillRect(getX(),getY(),getWidth(),getHeight());
        }
    }

    @Override
    public void tick(){
        moveForward(5);
        setAge(getAge()+1);
        if(getAge()==getMaxAge()){
            World.getInstance().removeEntity(this);
        }
//        setX(getX() + (int)slope.getX());
//        setY(getY() + (int)slope.getY());
    }
    public void moveForward(int speed) {
        x += Math.cos(getAngle())*speed;
        y += Math.sin(getAngle())*speed;
    }
}
