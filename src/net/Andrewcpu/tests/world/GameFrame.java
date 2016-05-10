package net.Andrewcpu.tests.world;

import net.Andrewcpu.engine.world.Frame;
import net.Andrewcpu.tests.Main;

import java.awt.*;

/**
 * Created by stein on 5/10/2016.
 */
public class GameFrame extends Frame {
    @Override
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(0,0,getWidth()/20, Main.player.getHealth());
        super.draw(g);
    }
}

