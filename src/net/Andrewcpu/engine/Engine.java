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
                worldTick();
                getEventManager().getTickListeners().forEach((tickListener) -> tickListener.onTick());
            }
        }, 20, 20, TimeUnit.MILLISECONDS);
    }
    private static void worldTick(){
        for(Entity entity : World.getInstance().getEntities()) {
            entity.tick();
            for(Entity e : World.getInstance().getEntities()){
                if(e==entity)
                    continue;
                if(e.getBounds().intersects(entity.getBounds())){
                    throwCollision(e,entity);
                }
            }
        }
    }
    private static void throwCollision(Entity entity, Entity entity2){
        for(CollisionListener collisionListener : getEventManager().getCollisionListeners()){
            collisionListener.onCollision(entity,entity2);
        }
    }
}
