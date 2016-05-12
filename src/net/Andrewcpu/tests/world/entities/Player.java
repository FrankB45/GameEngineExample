package net.Andrewcpu.tests.world.entities;

import net.Andrewcpu.engine.Engine;
import net.Andrewcpu.engine.utils.*;
import net.Andrewcpu.engine.utils.Vector;
import net.Andrewcpu.engine.world.Entity;
import net.Andrewcpu.engine.world.World;

import java.awt.*;
import java.awt.geom.AffineTransform;
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
    private double rotation = 0;
    private net.Andrewcpu.engine.utils.Vector velocity = new Vector(0,0);
    private boolean AI = false, respawn = true;

    public boolean isRespawn() {
        return respawn;
    }

    public void setRespawn(boolean respawn) {
        this.respawn = respawn;
    }

    public boolean isAI() {
        return AI;
    }

    public void setAI(boolean AI) {
        this.AI = AI;
    }

    public float getAngleToPoint(Point target) {
        float angle = (float) Math.toDegrees(Math.atan2(target.y - getY(), target.x - getX()));

        if(angle < 0){
            angle += 360;
        }

        return angle;
    }

    public Vector getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector velocity) {
        this.velocity = velocity;
    }

    public double getAngle() {
        return Math.toRadians(rotation);
    }

    public double getRotation() {
        return rotation;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

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

    public void damage(int damage){
        setHealth(getHealth()-damage);
        if(getHealth()<0){
            if(isRespawn()){
                setHealth(getMaxHealth());
                setLocation(Engine.getWIDTH()/2,Engine.getHEIGHT()/2);
            }
            else
                World.getInstance().removeEntity(this);
        }
    }

    public Player(int x, int y, int width, int height) {
        super(x, y, width, height);
    }
    public Player(int x, int y, int width, int height, boolean AI) {
        super(x, y, width, height);
        setAI(AI);
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

    public void moveX(int x){
        setX((int)(getX() + (x * speed)));
    }
    public void moveY(int y){
        setY((int)(getY() + (y * speed)));
    }

    public Point moveForwardSteps(int steps){
        Point point = getLocation();
        double x = Math.cos(getAngle())*steps;
        double y = Math.sin(getAngle())*steps;
        point.setLocation(point.getX() + x, point.getY() + y);
        return point;
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
    @Override
    public void tick(){
        if(isAI()){
            double dirX = Math.cos(getAngle());
            double dirY = Math.sin(getAngle());


            if((getX() - target.getX()) * dirY > (getY() - target.getY()) * dirX){
                setRotation(getRotation()+1);
            }
            else if ((getX() - target.getX()) * dirY < (getY() - target.getY()) * dirX){
                setRotation(getRotation()-1);
            }
            moveForward(getSpeed());
            if(getLocation().distance(getTarget())<=200){
                  fireBullet(target);
            }
        }
    }

    public void fireBullet(Point point){
        Point start = getLocation();
        start.translate(getWidth()/2,getHeight()/2);
        Vector vector = new Vector(-(start.getY() - point.getY()) , (start.getX() - point.getX()));
        Bullet bullet = new Bullet((int)start.getX(),(int)start.getY(),5,5, this);
        bullet.setAngle(getRotation());
        bullet.setSlope(vector);
        World.getInstance().addEntity(bullet);
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        AffineTransform old = g2.getTransform();
        AffineTransform transform = new AffineTransform();
        transform.rotate(Math.toRadians(getRotation()), getX() + getWidth()/2, getY() + getHeight()/2);
        g2.setTransform(transform);
        g2.setColor(getColor());
        g2.fillRect(getX(),getY(),getWidth(),getHeight());
        g2.setTransform(old);
        double width = getWidth();
        double calcWid = width * ((double)getHealth()/(double)getMaxHealth());
        g.setColor(Color.WHITE);
        g.fillRect(getX(),getY()-getHeight()-5,getWidth(),10);
        g.setColor(Color.RED);
        g.fillRect(getX(),getY()-getHeight()-5,(int)calcWid,10);
    }
}
