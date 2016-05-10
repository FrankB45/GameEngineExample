package net.Andrewcpu.engine;

import net.Andrewcpu.engine.listeners.EventManager;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by stein on 5/10/2016.
 */
public class Engine {
    private static EventManager eventManager = new EventManager();
    private static boolean pauseTick = false;

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
            if(!pauseTick)
                getEventManager().getTickListeners().forEach((tickListener)->tickListener.onTick());
        }, 20, 20, TimeUnit.MILLISECONDS);
    }
}
