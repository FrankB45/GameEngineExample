package net.Andrewcpu.tests.agar.world.frame;

import net.Andrewcpu.engine.Engine;
import net.Andrewcpu.engine.display.Frame;
import net.Andrewcpu.tests.agar.Main;

import java.awt.*;

/**
 * Created by stein on 5/12/2016.
 */
public class GameFrame extends Frame{
    @Override
    public void draw(Graphics g){
        if(Main.player != null){
            double width = Double.valueOf(Engine.getWIDTH()/50);
            double height = Double.valueOf(Engine.getHEIGHT() / 2);
            double drawHeight = (height * ((double)Main.player.getGunPower() / (double)Main.player.getGunPowerMax()));
            g.setColor(Color.WHITE);
            g.fillRect(0,Engine.getHEIGHT() / 4,(int)width,(int)height);
            g.setColor(Color.pink);
            g.fillRect(0,Engine.getHEIGHT() - (Engine.getHEIGHT() / 4),(int)width, -(int)drawHeight);
            g.setColor(Color.BLACK);
        }
    }

}
