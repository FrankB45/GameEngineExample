package net.Andrewcpu.engine.world;

import net.Andrewcpu.engine.Engine;
import net.Andrewcpu.engine.listeners.MouseListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 * Created by stein on 5/10/2016.
 */
public class Frame extends JComponent implements MouseMotionListener,Renderable,KeyListener, java.awt.event.MouseListener{
    public Frame(){
        addKeyListener(this);
        addMouseMotionListener(this);
        addMouseListener(this);
    }
    @Override
    public void mouseDragged(MouseEvent e) {
        for(MouseListener mouseListener : Engine.getEventManager().getMouseListeners())
            mouseListener.mouseDragged(e.getPoint());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        for(MouseListener mouseListener : Engine.getEventManager().getMouseListeners())
            mouseListener.mouseMoved(e.getPoint());
    }

    @Override
    public void draw(Graphics g) {

    }

    @Override
    public void paint(Graphics g) {
        World.getInstance().draw(g);
        draw(g);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        for(net.Andrewcpu.engine.listeners.KeyListener keyListener : Engine.getEventManager().getKeyListeners()){
            keyListener.keyPressed(e.getKeyCode());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        for(net.Andrewcpu.engine.listeners.KeyListener keyListener : Engine.getEventManager().getKeyListeners()){
            keyListener.keyReleased(e.getKeyCode());
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        for(MouseListener mouseListener : Engine.getEventManager().getMouseListeners()){
            mouseListener.mouseClicked(e.getPoint());
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
