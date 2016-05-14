package net.Andrewcpu.tests.realgm;

import net.Andrewcpu.engine.Engine;
import net.Andrewcpu.engine.display.Frame;
import net.Andrewcpu.engine.world.World;
import net.Andrewcpu.tests.realgm.entities.Player;

import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

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
        setBounds(0,0,1000,700);
        setTitle("Real Life");
        frame = new Frame();
        setupWorld();
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                Engine.setWIDTH(getWidth());
                Engine.setHEIGHT(getHeight());
            }
        });
        add(frame);
        setVisible(true);
        Engine.startTickLoop(30);
    }
    public void setupWorld(){
        Player player = new Player(500,250,50,50);
        World.getInstance().addEntity(player);
        Engine.getEventManager().registerTickListener(()->tick());

    }
    public void tick(){
        frame.setBounds(0,0,Engine.getWIDTH(),Engine.getHEIGHT());
        repaint();
    }
}
