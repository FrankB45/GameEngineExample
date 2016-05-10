package net.Andrewcpu.engine.listeners;

import java.util.LinkedList;

/**
 * Created by stein on 5/10/2016.
 */
public class EventManager {
    private LinkedList<CollisionListener> collisionListeners = new LinkedList<>();
    public void registerCollisionListener(CollisionListener listener){
        collisionListeners.add(listener);
    }
    public void unregisterCollisionListener(CollisionListener listener){
        collisionListeners.remove(listener);
    }
    public boolean isCollisionDetectorRegistered(CollisionListener listener){
        return collisionListeners.contains(listener);
    }
    public LinkedList<CollisionListener> getCollisionListeners() {
        return collisionListeners;
    }
    public void setCollisionListeners(LinkedList<CollisionListener> collisionListeners) {
        this.collisionListeners = collisionListeners;
    }
}
