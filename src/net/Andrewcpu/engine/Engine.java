package net.Andrewcpu.engine;

import net.Andrewcpu.engine.listeners.EventManager;

/**
 * Created by stein on 5/10/2016.
 */
public class Engine {
    private static EventManager eventManager = new EventManager();

    public static EventManager getEventManager() {
        return eventManager;
    }

    public static void setEventManager(EventManager eventManager) {
        Engine.eventManager = eventManager;
    }
}
