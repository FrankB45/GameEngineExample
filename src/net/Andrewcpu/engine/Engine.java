package net.Andrewcpu.engine;

import net.Andrewcpu.engine.listeners.CollisionListener;
import net.Andrewcpu.engine.listeners.EventManager;
import net.Andrewcpu.engine.world.Entity;
import net.Andrewcpu.engine.world.World;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

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
    static {
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(()->{
            if(!pauseTick) {
                World.getInstance().tick();
                getEventManager().getTickListeners().forEach((tickListener) -> tickListener.onTick());
            }
        }, 20, 20, TimeUnit.MILLISECONDS);
    }

}
