package net.Andrewcpu.engine.world;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 * Created by stein on 5/10/2016.
 */
public class Frame extends JComponent implements MouseMotionListener{
    public Frame(){
        addMouseMotionListener(this);
    }
    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void paint(Graphics g) {
        World.getInstance().draw(g);
    }
}
