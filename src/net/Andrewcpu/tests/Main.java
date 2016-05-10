package net.Andrewcpu.tests;

import net.Andrewcpu.engine.Engine;
import net.Andrewcpu.engine.listeners.MouseListener;
import net.Andrewcpu.engine.world.Entity;
import net.Andrewcpu.engine.world.Frame;
import net.Andrewcpu.engine.world.World;
import net.Andrewcpu.tests.entities.Player;

import javax.swing.*;
import java.awt.*;

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
        Frame frame = new Frame();
        frame.setBounds(getBounds());
        add(frame);
        setVisible(true);
    }
    public void setupWorld(){
        Player player = new Player(250,250,50,50);
        World world = World.getInstance();
        world.addEntity(player);
        Engine.getEventManager().registerTickListener(()->{tick();});
        Engine.getEventManager().registerMouseListener(new MouseListener() {
            @Override
            public void mouseMoved(Point point) {

            }

            @Override
            public void mouseDragged(Point point) {

            }
        });
    }
    public void tick(){
        repaint();
    }
}
