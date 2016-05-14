package net.Andrewcpu.engine.world;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.*;
import java.util.List;

/**
 * Created by stein on 5/10/2016.
 */
public class Entity implements Renderable{
    private UUID uuid = UUID.randomUUID();
    private int x,y,width,height;
    private double rotation = 0;

    public Entity(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public double getAngle(){
        return Math.toRadians(getRotation());
    }

    public double getRotation() {
        return rotation;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
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


    public void left(int speed){
        setX(getX() - speed);
    }
    public void right(int speed){
        setX(getX() + speed);
    }

    public void up(int speed){
        setY(getY() - speed);
    }
    public void down(int speed){
        setY(getY() + speed);
    }

    public void transform(int x, int y, double rotation){
        setX(x);
        setY(y);
        setRotation(rotation);
    }

    public void moveForward(int speed) {
        double x = Math.cos(getAngle())*speed;
        double y = Math.sin(getAngle())*speed;
        setX(getX() + (int)x);
        setY(getY() + (int)y);
    }
    public void moveBackward(int speed) {
        double x = Math.cos(getAngle())*speed;
        double y = Math.sin(getAngle())*speed;
        setX(getX() - (int)x);
        setY(getY() - (int)y);
    }

    public void tick(){

    }

    public java.util.List<Entity> getEntitiesInLineOfSight(Point point){
        List<Entity> entities = new ArrayList<>();
        double pX = point.getX() - getX();
        double pY = point.getY() - getY();
        for(int i = getX(); i<=point.getX(); i+=pX){
            for(int j = getY(); j<=point.getY(); j+=pY){
                for(Entity entity : World.getInstance().getEntities()){
                    if(entity.getBounds().contains(i,j))
                        entities.add(entity);
                }
            }
        }
        return entities;
    }
    public java.util.List<Entity> getEntitiesInLineOfSight(Entity entity){
        List<Entity> list = getEntitiesInLineOfSight(entity.getLocation());
        if(list.contains(this))
            list.remove(this);
        if(list.contains(entity))
            list.remove(entity);
        return list;
    }

    public boolean hasLineOfSight(Entity entity){
        return getEntitiesInLineOfSight(entity).size()==0;
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        AffineTransform old = g2.getTransform();
        AffineTransform transform = new AffineTransform();
        transform.rotate(Math.toRadians(getRotation()), getX() + getWidth()/2, getY() + getHeight()/2);
        g2.setTransform(transform);
        g2.fillRect(getX(),getY(),getWidth(),getHeight());
        g2.setTransform(old);
    }
}
