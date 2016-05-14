package net.Andrewcpu.tests.realgm.entities;

import net.Andrewcpu.engine.Engine;
import net.Andrewcpu.engine.display.*;
import net.Andrewcpu.engine.display.elements.Element;
import net.Andrewcpu.engine.display.elements.TextElement;
import net.Andrewcpu.tests.realgm.Main;

import java.awt.*;

/**
 * Created by stein on 5/14/2016.
 */
public class HUD extends Element {
    private Color healthBackground = new Color(255,0,0,50), healthColor = new Color(0,255,0,70);
    private Player player;
    private double width = Engine.getWIDTH()/4;
    private double height = 25;
    private TextElement element = new TextElement("",(int)width + 10, (int)(height / 1.5));
    public HUD(Player player){
        this.player = player;
        net.Andrewcpu.engine.display.Frame.getInstance().addElement(element);
        element.setFontSize(15);
        element.setTextColor(Color.WHITE);
    }
    @Override
    public void draw(Graphics g) {
        double drawableWidth = width * ((double)player.getHealth()/(double)player.getMaxHealth());
        g.setColor(healthBackground);
        g.fillRect(0,(int)0,(int)width,(int)height);
        g.setColor(healthColor);
        g.fillRect(0,(int)0,(int)drawableWidth,(int)height);
        element.setText(player.getHealth() + " / " + player.getMaxHealth());
    }
}
