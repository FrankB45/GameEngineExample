package net.Andrewcpu.tests.agar.world.entities;

import net.Andrewcpu.engine.Engine;
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
    private Entity target = null;
    private int speed = 1;
    private double size = -1;
    private int health, maxHealth;
    private java.util.List<Integer> keyCodes = Collections.synchronizedList(new ArrayList<>());
    private net.Andrewcpu.engine.utils.Vector velocity = new Vector(0,0);
    private boolean AI = false, respawn = true;

    private int gunPower = 0,gunPowerMax = 100;

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        if(this.size==-1){
            this.size = size;
            setMaxHealth((int)size * 50);
            //setHealth(getMaxHealth());
            setWidth((int)size * 10);
            setHeight((int)size * 10);
        }
    }

    public int getGunPower() {
        return gunPower;
    }

    public void setGunPower(int gunPower) {
        this.gunPower = gunPower;
    }

    public int getGunPowerMax() {
        return gunPowerMax;
    }

    public void setGunPowerMax(int gunPowerMax) {
        this.gunPowerMax = gunPowerMax;
    }

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

    public Entity getTarget() {
        return target;
    }

    public void setTarget(Entity target) {
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
    public Point moveForwardSteps(int steps, Point point){
        double x = Math.cos(getAngle())*steps;
        double y = Math.sin(getAngle())*steps;
        point.setLocation(point.getX() + x, point.getY() + y);
        return point;
    }


    public void heal(int health){
        if(health + getHealth() > getMaxHealth()){
            setHealth(getMaxHealth());
        }
        else{
            setHealth(getHealth() + health);
        }
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

            if(getAngleToPoint(target.getLocation())!=getAngle()){
                moveForward(getSpeed());
            }
            if(getLocation().distance(getTarget().getLocation())<=300 && getGunPower()>0){
                fireBullet(target.getLocation());
                setGunPower(getGunPower()-2);
            }
            if(getGunPower() < getGunPowerMax())
                setGunPower(getGunPower()+1);
        }
    }
    public void eat(Food food){
        int value = food.getValue();
        setSize(getSize() + 0.01);
        heal(value);
        World.getInstance().removeEntity(food);
    }
    public void fireBullet(Point point){
        if(getGunPower()<=5 ){
            return;
        }
            Point start = moveForwardSteps(10, new Point(getX() + getWidth()/2, getY() + getHeight()/2));
         //   start.translate(getWidth() / 2, getHeight() / 2);
            Vector vector = new Vector(-(start.getY() - point.getY()), (start.getX() - point.getX()));
            Bullet bullet = new Bullet((int) start.getX(), (int) start.getY(), 5, 5, this);
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
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(getX(),getY()-40,getWidth(),10);
        g.setColor(Color.RED);
        g.fillRect(getX(),getY()-40,(int)calcWid,10);

        g.setColor(Color.CYAN);
        Point center = getLocation();
        center.translate(getWidth()/2,(getHealth()/2));
        Point start = moveForwardSteps(40, new Point(getX() + getWidth()/2, getY() + getHeight()/2));
        g.fillRect((int)start.getX(),(int)start.getY(),10,10);
//        if(isAI())
//            g.drawLine(getX(),getY(),(int)target.getX(),(int)target.getY());

    }
}
