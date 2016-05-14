package net.Andrewcpu.tests.realgm.entities;

import net.Andrewcpu.engine.Engine;
import net.Andrewcpu.engine.utils.Log;
import net.Andrewcpu.engine.utils.image.SpriteSheet;
import net.Andrewcpu.engine.world.Entity;
import net.Andrewcpu.engine.world.World;
import net.Andrewcpu.engine.world.physics.Solid;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by stein on 5/14/2016.
 */
public class Player extends Entity{
    private int jumpHeight = 0;
    private PlayerDirection direction = PlayerDirection.FORWARD;
    private PlayerState state = PlayerState.STILL;
    private BufferedImage[] sprites = SpriteSheet.getSprites(SpriteSheet.loadImage(getClass().getClassLoader().getResource("net/Andrewcpu/tests/realgm/res/character.png")),3,2);
    private int playerFrame = 2;
    private boolean onSolid = false;
    private int health = 20, maxHealth = 20;
    private boolean AI = false;
    private Entity target = null;
    private boolean reloading = false;
    public Player(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public void reload(){
        reloading = false;
    }

    public Entity getTarget() {
        return target;
    }

    public void setTarget(Entity target) {
        this.target = target;
    }

    public boolean isAI() {
        return AI;
    }

    public void setAI(boolean AI) {
        this.AI = AI;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public boolean isOnSolid() {
        return onSolid;
    }

    public void setOnSolid(boolean onSolid) {
        this.onSolid = onSolid;
    }

    public PlayerState getState() {
        return state;
    }

    public void setState(PlayerState state) {
        this.state = state;
    }

    public PlayerDirection getDirection() {
        return direction;
    }

    public void setDirection(PlayerDirection direction) {
        this.direction = direction;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(sprites[playerFrame],getX(),getY(),null);
        if(isAI()){
            double w = getWidth();
            double h = 5;
            double dWidth = w * ((double)getHealth()/(double)getMaxHealth());
            g.setColor(Color.RED);
            g.fillRect(getX() + (int)(w/8),getY() - 5,(int) w,(int)h);
            g.setColor(Color.GREEN);
            g.fillRect(getX() + (int)(w/8),getY()-5,(int)dWidth,(int)h);
        }
    }
    public boolean isJumping(){
        return isOnSolid();
    }
    public void jump( int height ){
        jumpHeight = height;
        setOnSolid(false);
    }
    public void respawn(){
        setHealth(getMaxHealth());
        setLocation(100,0);
        reload();
        setOnSolid(false);
    }
    public void damage(int i){
        setHealth(getHealth()- i);
        if(getHealth()<=0){
            respawn();
        }
    }
    public int getJumpHeight() {
        return jumpHeight;
    }

    public void setJumpHeight(int jumpHeight) {
        this.jumpHeight = jumpHeight;
    }
    private int reloadTime = 0, maxReloadTime = 10;
    @Override
    public void tick() {
        if(isAI()){
            if(reloading)
            {
                reloadTime++;
                if(reloadTime==maxReloadTime) {
                    reload();
                    reloadTime = 0;
                }
            }
            if(target.getX()>getX()){
                if(getDirection()!=PlayerDirection.FORWARD){
                    setDirection(PlayerDirection.FORWARD);
                    frame();
                }
                if(getState()!=PlayerState.RUNNING){
                    setState(PlayerState.RUNNING);
                }
                if(getTarget().getLocation().distance(getLocation())>=50)
                    right(3);
                else
                    shootBullet();
            }
            else if(target.getX()<getX()){
                if(getDirection()!=PlayerDirection.BACKWARD){
                    setDirection(PlayerDirection.BACKWARD);
                    frame();
                }
                if(getState()!=PlayerState.RUNNING){
                    setState(PlayerState.RUNNING);
                }
                if(getTarget().getLocation().distance(getLocation())>=50)
                    left(3);
                else
                    shootBullet();
            }
            else{
                setState(PlayerState.STILL);
            }
            if(target.getY()<getY()){
                if(getJumpHeight()<=0)
                    jump(15);
            }
        }
        for(Solid solid : World.getInstance().getSolidObjects()){
            while(solid.getBounds().contains(getX(),getY())){
                setY(getY()-1);
                setOnSolid(true);
            }
        }
        if(!isOnSolid()){
            setY(getY() + -jumpHeight);
            if(jumpHeight>=-5)
                jumpHeight--;
        }
        super.tick();
    }

    @Override
    public void frame(){
        if(getState()==PlayerState.RUNNING){
            if(getDirection()==PlayerDirection.BACKWARD){
                if(playerFrame==1)
                    playerFrame = 2;
                else
                    playerFrame = 1;
            }
            else{
                if(playerFrame==4)
                    playerFrame = 5;
                else
                    playerFrame = 4;
            }
        } else if(getState()==PlayerState.STILL){
            if(getDirection()==PlayerDirection.BACKWARD){
                playerFrame = 0;
            }
            else{

                playerFrame = 3;
            }
        }
    }

    public void shootBullet(){
        if(!reloading) {
            Bullet bullet = new Bullet(getX() + getWidth() / 2, getY() + getHeight() / 2, 6, 2, getDirection(),this);
            World.getInstance().addEntity(bullet);
            reloading = true;
        }
    }
}
