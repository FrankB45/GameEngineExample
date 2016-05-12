package net.Andrewcpu.engine.world;

import java.awt.*;
import java.util.UUID;

/**
 * Created by stein on 5/10/2016.
 */
public class Entity implements Renderable{
    private UUID uuid = UUID.randomUUID();
    private int x,y,width,height;

    public Entity(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public UUID getUUID() {
        return uuid;
    }
    public void setUUID(UUID uuid) {
        this.uuid = uuid;
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

    public Point getLocation(){
        return new Point(getX(),getY());
    }

    public Rectangle getBounds(){
        return new Rectangle(getX(),getY(),getWidth(),getHeight());
    }

    public void setLocation(int x, int y){
        setX(x);
        setY(y);
    }

    public void setLocation(Point point){
        setX((int)point.getX());
        setY((int)point.getY());
    }

    public void tick(){

    }

    @Override
    public void draw(Graphics g) {
        g.fillRect(getX(),getY(),getWidth(),getHeight());
    }
}
