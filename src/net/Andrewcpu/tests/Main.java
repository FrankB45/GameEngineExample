package net.Andrewcpu.tests;

import net.Andrewcpu.engine.Engine;
import net.Andrewcpu.engine.listeners.KeyListener;
import net.Andrewcpu.engine.listeners.MouseListener;
import net.Andrewcpu.engine.world.*;
import net.Andrewcpu.engine.world.Frame;
import net.Andrewcpu.tests.world.entities.Bullet;
import net.Andrewcpu.tests.world.entities.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.util.*;

/**
 * Created by stein on 5/10/2016.
 */
public class Main extends JFrame{
    public static void main(String[] args){
        new Main();
    }
    private Frame frame = null;
    public Main(){
        setLayout(null);
        setBounds(0,0,1000,700);
        Engine.setWIDTH(getWidth());
        Engine.setHEIGHT(getHeight());
        setTitle("Game");
        setupWorld();
        frame = new Frame();
        frame.setBounds(getBounds());
        add(frame);
        setVisible(true);
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                frame.setBounds(0,0,getWidth(),getHeight());
                repaint();
                Engine.setWIDTH(getWidth());
                Engine.setHEIGHT(getHeight());
            }
        });
        Engine.startTickLoop(30);
    }
    public static Player player = new Player(250,250,50,50);
    private java.util.List<Player> enemies = new ArrayList<>();
    private Random random = new Random();
    public void createEnemies(){
        for(int i = 0; i<=5; i++){
            int x = random.nextInt(Engine.getWIDTH());
            int y = random.nextInt(Engine.getHEIGHT());
            Player enemy = new Player(x,y,50,50, true);
            enemy.setRespawn(false);
            enemies.add(enemy);
        }
    }

    public void setupWorld(){
        createEnemies();
        World world = World.getInstance();
        world.addEntity(player);
        for(Player enemy : enemies){
            enemy.setColor(Color.RED);
            enemy.setSpeed(3);
            world.addEntity(enemy);
        }
        Engine.getEventManager().registerTickListener(()->{tick();});
        Engine.getEventManager().registerCollisionListener((entity1,entity2)->{
            if(entity1 instanceof Player && entity2 instanceof Bullet && ((Bullet)entity2).getShooter()!=entity1){
                ((Player)entity1).damage(10);
                World.getInstance().removeEntity(entity2);
            }
        });
        Engine.getEventManager().registerMouseListener(new MouseListener() {
            @Override
            public void mouseMoved(Point point) {
            }
            @Override
            public void mouseDragged(Point point) {
            }
            @Override
            public void mouseClicked(Point point){
                player.setAI(true);
                player.setTarget(point);
            }
        });
        Engine.getEventManager().registerKeyListener(new KeyListener() {
            @Override
            public void keyPressed(int keyCode) {
                if(keyCode== KeyEvent.VK_C){
                    for(Entity e : World.getInstance().getEntitiesByClass(Bullet.class)){
                        World.getInstance().removeEntity(e);
                    }
                }
                while(player.isPressing(keyCode))
                    player.releaseKey(keyCode);
                player.pressKey(keyCode);
            }

            @Override
            public void keyReleased(int keyCode) {
                while(player.isPressing(keyCode))
                    player.releaseKey(keyCode);
            }
        });
    }

    public void tick(){
        frame.requestFocus();
        for(Player enemy : enemies){
            enemy.setTarget(player.getLocation());
        }
        for(Integer keyCode: player.getKeyCodes()){
            if(keyCode==KeyEvent.VK_W){
                player.moveForward(10);
                player.setAI(false);
            }
            if(keyCode==KeyEvent.VK_S){
                player.moveBackward(10);
                player.setAI(false);
            }
            if(keyCode==KeyEvent.VK_F){
                player.fireBullet(player.moveForwardSteps(500));
            }
            if(keyCode==KeyEvent.VK_G){
                enemies.clear();
                createEnemies();
                player.setHealth(player.getMaxHealth());
            }
            if(keyCode==KeyEvent.VK_A){
                player.setRotation(player.getRotation()-5);
                if(player.getRotation()<=0){
                    player.setRotation(359);
                }
                player.setAI(false);
            }
            if(keyCode==KeyEvent.VK_D){
                player.setRotation(player.getRotation()+5);
                if(player.getRotation()>=360){
                    player.setRotation(0);
                }
                player.setAI(false);
            }
        }
        repaint();
    }
}
