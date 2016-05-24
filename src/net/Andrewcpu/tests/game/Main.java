package net.Andrewcpu.tests.game;

import net.Andrewcpu.engine.Engine;
import net.Andrewcpu.engine.display.elements.TextElement;
import net.Andrewcpu.engine.listeners.KeyListener
import net.Andrewcpu.engine.display.Frame;
import net.Andrewcpu.engine.world.World;
import net.Andrewcpu.tests.game.entities.Ball;
import net.Andrewcpu.tests.game.entities.Plate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

/**
 * Created by stein on 5/12/2016.
 */
public class Main extends JFrame {
    public static void main(String[] args){
        new Main();
    }
    private Frame frame = null;
    private Plate plate = new Plate(500,650,200,50);
    private Random random = new Random();
    private TextElement scoreElement = new TextElement("", 50,50, 50);
    private int score = 0;
    public Main(){
        setLayout(null);
        setBounds(0,0,1000,700);
        Engine.setWIDTH(getWidth());
        Engine.setHEIGHT(getHeight());
        setupWorld();
        frame = new Frame();
        frame.setBounds(0,0,getWidth(),getHeight());
        add(frame);
        setupElements();
        setVisible(true);
        Engine.startTickLoop(30);
    }

    public void setupElements(){
        frame.addElement(scoreElement);
    }

    public void setupWorld(){
        World.getInstance().addEntity(plate);
        Engine.getEventManager().registerKeyListener(new KeyListener() {
            @Override
            public void keyPressed(int keyCode) {
                plate.pressKey(keyCode);
            }

            @Override
            public void keyReleased(int keyCode) {
                plate.releaseKey(keyCode);
            }
        });
        Engine.getEventManager().registerTickListener(()->tick());
        Engine.getEventManager().registerCollisionListener((e1,e2)->{
            if(e1 instanceof Plate && e2 instanceof Ball){
                World.getInstance().removeEntity(e2);
                score++;
                scoreElement.setText(score + "");
            }
        });
    }
    public void spawnBall(){
        int x = random.nextInt(Engine.getWIDTH());
        int y = -30;
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);
        Ball ball = new Ball(x,y,30,30);
        ball.setColor(new Color(r,g,b));
        World.getInstance().addEntity(ball);
    }
    public void tick(){
        if(random.nextInt(200)<=2){
            spawnBall();
        }
        if(plate.isPressing(KeyEvent.VK_RIGHT)){
            plate.right(plate.getSpeed());
        }
        if(plate.isPressing(KeyEvent.VK_LEFT)){
            plate.left(plate.getSpeed());
        }
        repaint();
        frame.requestFocus();
    }
}
