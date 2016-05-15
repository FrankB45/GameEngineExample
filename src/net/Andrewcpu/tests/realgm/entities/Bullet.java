package net.Andrewcpu.tests.realgm.entities;

import net.Andrewcpu.engine.Engine;
import net.Andrewcpu.engine.utils.audio.Sound;
import net.Andrewcpu.engine.world.Entity;
import net.Andrewcpu.engine.world.World;

import java.awt.*;

/**
 * Created by stein on 5/14/2016.
 */
public class Bullet extends Entity {
    private PlayerDirection direction;
    private Player shooter;
    private Sound shoot = new Sound("/net/Andrewcpu/tests/realgm/res/shoot.wav");
    public Bullet(int x, int y, int width, int height, PlayerDirection direction, Player shooter) {
        super(x, y, width, height);
        this.direction = direction;
        this.shooter = shooter;
        shoot.start();
    }

    public Player getShooter() {
        return shooter;
    }

    public void setShooter(Player shooter) {
        this.shooter = shooter;
    }

    @Override
    public void tick(){
        if(getDirection()==PlayerDirection.FORWARD){
            right(10);
        }
        if(getDirection()==PlayerDirection.BACKWARD){
            left(10);
        }
        if(getX()<0 || getX()> Engine.getWIDTH()){
            World.getInstance().removeEntity(this);
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(getX(),getY(),getWidth(),getHeight());
    }

    public PlayerDirection getDirection() {
        return direction;
    }

    public void setDirection(PlayerDirection direction) {
        this.direction = direction;
    }
}
