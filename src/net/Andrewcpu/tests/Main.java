package net.Andrewcpu.tests;

import net.Andrewcpu.engine.Engine;
import net.Andrewcpu.engine.listeners.MouseListener;
import net.Andrewcpu.engine.utils.Vector;
import net.Andrewcpu.engine.world.Entity;
import net.Andrewcpu.engine.world.Frame;
import net.Andrewcpu.engine.world.World;
import net.Andrewcpu.tests.world.GameFrame;
import net.Andrewcpu.tests.world.entities.Bullet;
import net.Andrewcpu.tests.world.entities.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by stein on 5/10/2016.
 */
public class Main extends JFrame{
    public static void main(String[] args){
        new Main();
    }
    public Main(){
        setLayout(null);
        setBounds(0,0,500,500);
        setTitle("Game");
        setupWorld();
        GameFrame frame = new GameFrame();
        frame.setBounds(getBounds());
        add(frame);
        setVisible(true);
    }
    public static Player player = new Player(250,250,50,50);
    Player enemy = new Player(0,0,50,50);
    public void setupWorld(){

        enemy.setColor(Color.RED);

        enemy.setSpeed(1);
        player.setSpeed(2);

        World world = World.getInstance();
        world.addEntity(player);
        world.addEntity(enemy);
        Engine.getEventManager().registerTickListener(()->{tick();});
        Engine.getEventManager().registerCollisionListener((entity1,entity2)->{
            System.out.println(entity1.getUUID().toString() + " has collided with " + entity2.getUUID().toString());
            if(entity1 == player){
                player.setHealth(player.getHealth() - 1);
            }
        });
        Engine.getEventManager().registerMouseListener(new MouseListener() {
            @Override
            public void mouseMoved(Point point) {
                player.setTarget(point);
            }

            @Override
            public void mouseDragged(Point point) {

            }

            @Override
            public void mouseClicked(Point point){
                fireBullet(point);
            }
        });
        Engine.getEventManager().registerKeyListener((keyCode)->{
            if(keyCode== KeyEvent.VK_C){
                for(Entity e : World.getInstance().getEntitiesByClass(Bullet.class)){
                    World.getInstance().removeEntity(e);
                }
            }
        });
    }
    public void fireBullet(Point point){
        Point start = player.getLocation();
        Vector vector = new Vector((point.getY() - start.getY()) , (point.getX() - start.getX()));
        Bullet bullet = new Bullet((int)start.getX(),(int)start.getY(),5,5);
        bullet.setSlope(vector);
        World.getInstance().addEntity(bullet);
    }
    public void tick(){
        repaint();
        enemy.setTarget(player.getLocation());
    }
}
