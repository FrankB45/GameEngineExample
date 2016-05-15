package net.Andrewcpu.tests.realgm;

import net.Andrewcpu.engine.Engine;
import net.Andrewcpu.engine.display.Frame;
import net.Andrewcpu.engine.listeners.CollisionListener;
import net.Andrewcpu.engine.listeners.KeyListener;
import net.Andrewcpu.engine.listeners.MouseListener;
import net.Andrewcpu.engine.utils.Log;
import net.Andrewcpu.engine.utils.audio.Sound;
import net.Andrewcpu.engine.utils.audio.SoundManager;
import net.Andrewcpu.engine.utils.image.SpriteSheet;
import net.Andrewcpu.engine.world.Entity;
import net.Andrewcpu.engine.world.World;
import net.Andrewcpu.engine.world.physics.Solid;
import net.Andrewcpu.tests.game.entities.Plate;
import net.Andrewcpu.tests.realgm.entities.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.util.Random;

/**
 * Created by stein on 5/13/2016.
 */
public class Main extends JFrame{
    public static void main(String[] args){
        new Main();
    }
    private Frame frame = null;
    public Main(){
        setLayout(null);
        setResizable(false);
        setBounds(0,0,1000,700);
        setTitle("Real Life");
        frame = new Frame();
        Engine.setWIDTH(getWidth());
        Engine.setHEIGHT(getHeight());
        setupWorld();
//        addComponentListener(new ComponentAdapter() {
//            @Override
//            public void componentResized(ComponentEvent e) {
//                Engine.setWIDTH(getWidth());
//                Engine.setHEIGHT(getHeight());
//            }
//        });
        add(frame);
        setVisible(true);
        Engine.startTickLoop(30);
    }
    private Random random = new Random();
    public void spawnEnemy(){

        Player enemy = new Player(random.nextInt(Engine.getWIDTH()),0,50,50);
        enemy.setAI(true);
        enemy.setTarget(player);

        World.getInstance().addEntity(enemy);
    }
    private Player player = new Player(500,250,50,50);
    public void setupWorld(){
        frame.addElement(new HUD(player));
        World.getInstance().addSolidObject(new Solid(0,Engine.getHEIGHT() - 120,getWidth(),200));
        World.getInstance().addEntity(player);

        for(int i =0; i<=2; i++){
            spawnEnemy();
        }


        World.getInstance().setBackgroundImage(SpriteSheet.loadImage(getClass().getClassLoader().getResource("net/Andrewcpu/tests/realgm/res/background.png")));
        Engine.getEventManager().registerTickListener(()->tick());
        Engine.getEventManager().registerKeyListener(new KeyListener() {
            @Override
            public void keyPressed(int keyCode) {
                player.pressKey(keyCode);
            }

            @Override
            public void keyReleased(int keyCode) {
                player.releaseKey(keyCode);
                if(keyCode==(KeyEvent.VK_D)){
                    player.setState(PlayerState.STILL);
                    player.frame();
                }
                if(keyCode==(KeyEvent.VK_A)) {
                    player.setState(PlayerState.STILL);
                    player.frame();
                }
                if(keyCode==KeyEvent.VK_F){
                    player.reload();
                }
            }
        });
        Engine.getEventManager().registerCollisionListener(new CollisionListener() {
            @Override
            public void onCollision(Entity e1, Entity e2) {
                if(e1 instanceof Player && e2 instanceof Bullet){
                    if(((Bullet)e2).getShooter()!=e1) {
                        ((Player) e1).damage(1);
                        ((Player)e1).hit();
                        World.getInstance().removeEntity(e2);
                    }
                }
            }
        });
//        Engine.getEventManager().registerMouseListener(new MouseListener() {
//            @Override
//            public void mouseMoved(Point point) {
//
//            }
//
//            @Override
//            public void mouseDragged(Point point) {
//
//            }
//
//            @Override
//            public void mouseClicked(Point point) {
//
//            }
//        });
        //new SoundManager().playSound("/net/Andrewcpu/tests/realgm/res/background-sound.mp3");
        Sound sound = new Sound("/net/Andrewcpu/tests/realgm/res/background-sound.wav");
        sound.setLoop(true);
        sound.start();
    }
    public void tick(){
        if(player.isPressing(KeyEvent.VK_D)){
            if(player.getDirection()!=PlayerDirection.FORWARD){
                player.setDirection(PlayerDirection.FORWARD);
                player.frame();
            }
            if(player.getState()!=PlayerState.RUNNING){
                player.setState(PlayerState.RUNNING);
                player.frame();
            }
            player.right((player.isPressing(KeyEvent.VK_SHIFT) ? 10 : 5));
        }
        if(player.isPressing(KeyEvent.VK_A)){
            if(player.getDirection()!=PlayerDirection.BACKWARD){
                player.setDirection(PlayerDirection.BACKWARD);
                player.frame();
            }
            if(player.getState()!=PlayerState.RUNNING){
                player.setState(PlayerState.RUNNING);
                player.frame();
            }
            player.left((player.isPressing(KeyEvent.VK_SHIFT) ? 10 : 5));
        }
        if(player.isPressing(KeyEvent.VK_SPACE)){
            if(player.isJumping() && player.getJumpHeight()<=0) {
                player.jump(15);

            }

        }
        if(player.isPressing(KeyEvent.VK_F)){
            player.shootBullet();
        }
        frame.setBounds(0,0,Engine.getWIDTH(),Engine.getHEIGHT());
        frame.requestFocus();
        repaint();
    }
}
