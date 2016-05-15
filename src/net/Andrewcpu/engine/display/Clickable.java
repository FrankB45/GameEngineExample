package net.Andrewcpu.engine.display;

import java.awt.*;

/**
 * Created by stein on 5/15/2016.
 */
public interface Clickable {
    Rectangle getBounds();
    void onClick();
    Runnable getAction();
}
