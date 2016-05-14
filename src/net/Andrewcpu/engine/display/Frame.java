package net.Andrewcpu.engine.display;

import net.Andrewcpu.engine.Engine;
import net.Andrewcpu.engine.display.elements.Element;
import net.Andrewcpu.engine.listeners.MouseListener;
import net.Andrewcpu.engine.world.Renderable;
import net.Andrewcpu.engine.world.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.*;

/**
 * Created by stein on 5/10/2016.
 */
public class Frame extends JComponent implements MouseMotionListener,Renderable,KeyListener, java.awt.event.MouseListener{
    private java.util.List<Element> elements = new ArrayList<>();
    public Frame(){
        addKeyListener(this);
        addMouseMotionListener(this);
        addMouseListener(this);
    }

    public void addElement(Element element){
        elements.add(element);
    }
    public void removeElement(Element element){
        elements.remove(element);
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
        elements.forEach(element -> {if(!element.isHidden())element.draw(g);});
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
