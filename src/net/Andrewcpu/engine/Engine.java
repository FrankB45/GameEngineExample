package net.Andrewcpu.engine;

import net.Andrewcpu.engine.listeners.EventManager;
import net.Andrewcpu.engine.world.World;

/**
 * Created by stein on 5/10/2016.
 */
public class Engine {
    private static EventManager eventManager = new EventManager();
    private static boolean pauseTick = false;

    private static int WIDTH, HEIGHT;

    public static int getWIDTH() {
        return WIDTH;
    }

    public static void setWIDTH(int WIDTH) {
        Engine.WIDTH = WIDTH;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }

    public static void setHEIGHT(int HEIGHT) {
        Engine.HEIGHT = HEIGHT;
    }

    public static EventManager getEventManager() {
        return eventManager;
    }
    public static boolean isPauseTick() {
        return pauseTick;
    }

    public static void setPauseTick(boolean pauseTick) {
        Engine.pauseTick = pauseTick;
    }
    public static void setEventManager(EventManager eventManager) {
        Engine.eventManager = eventManager;
    }
    private static long lastStartTime = 0;
    public static void startTickLoop(int FPS){
        long maxWorkingTimePerFrame = 1000 / FPS;  //this is optional
        lastStartTime = System.currentTimeMillis();
        while(!pauseTick)
        {
            long elapsedTime = System.currentTimeMillis() - lastStartTime;
            lastStartTime = System.currentTimeMillis();
            World.getInstance().tick();
            getEventManager().getTickListeners().forEach((tickListener) -> tickListener.onTick());
            long processingTimeForCurrentFrame = System.currentTimeMillis() - lastStartTime;
            if(processingTimeForCurrentFrame  < maxWorkingTimePerFrame)
            {
                try
                {
                    Thread.sleep(maxWorkingTimePerFrame - processingTimeForCurrentFrame);
                }
                catch(Exception e)
                {
                    System.err.println("Engine :: run :: " + e);
                }
            }
        }
    }

}
