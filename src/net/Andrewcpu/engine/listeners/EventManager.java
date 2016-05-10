package net.Andrewcpu.engine.listeners;

import java.util.LinkedList;

/**
 * Created by stein on 5/10/2016.
 */
public class EventManager {
    private LinkedList<CollisionListener> collisionListeners = new LinkedList<>();
    private LinkedList<TickListener> tickListeners = new LinkedList<>();
    private LinkedList<MouseListener> mouseListeners = new LinkedList<>();
    private LinkedList<KeyListener> keyListeners = new LinkedList<>();
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

    public void registerTickListener(TickListener listener){
        tickListeners.add(listener);
    }
    public void unregisterTickListener(TickListener listener){
        tickListeners.remove(listener);
    }
    public boolean isTickDetectorRegistered(TickListener listener){
        return tickListeners.contains(listener);
    }
    public LinkedList<TickListener> getTickListeners() {
        return tickListeners;
    }
    public void setTickListeners(LinkedList<TickListener> tickListeners) {
        this.tickListeners = tickListeners;
    }
    
    public void registerMouseListener(MouseListener listener){
        mouseListeners.add(listener);
    }
    public void unregisterMouseListener(MouseListener listener){
        mouseListeners.remove(listener);
    }
    public boolean isMouseDetectorRegistered(MouseListener listener){
        return mouseListeners.contains(listener);
    }
    public LinkedList<MouseListener> getMouseListeners() {
        return mouseListeners;
    }
    public void setMouseListeners(LinkedList<MouseListener> mouseListeners) {
        this.mouseListeners = mouseListeners;
    }    
    
    public void registerKeyListener(KeyListener listener){
        keyListeners.add(listener);
    }
    public void unregisterKeyListener(KeyListener listener){
        keyListeners.remove(listener);
    }
    public boolean isKeyDetectorRegistered(KeyListener listener){
        return keyListeners.contains(listener);
    }
    public LinkedList<KeyListener> getKeyListeners() {
        return keyListeners;
    }
    public void setKeyListeners(LinkedList<KeyListener> keyListeners) {
        this.keyListeners = keyListeners;
    }

}
