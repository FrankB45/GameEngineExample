package net.Andrewcpu.engine.world;

import net.Andrewcpu.engine.Engine;
import net.Andrewcpu.engine.listeners.CollisionListener;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by stein on 5/10/2016.
 */
public class World implements Renderable{
    private static World instance = null;
    private Color color = Color.WHITE;
    public World(){
        instance = this;
    }
    public static World getInstance() {
        if (instance == null)
            instance = new World();
        return instance;
    }
    private LinkedList<Entity> entities = new LinkedList<>();
    private LinkedList<Entity> addQueue = new LinkedList<>();
    private LinkedList<Entity> removeQueue = new LinkedList<>();
    public void addEntity(Entity entity){
        addQueue.add(entity);
    }
    public void removeEntity(Entity entity){
        removeQueue.add(entity);
    }
    public boolean doesWorldContainEntity(Entity entity){
        return entities.contains(entity);
    }

    public LinkedList<Entity> getEntities() {
        return entities;
    }
    public List<Entity> getEntitiesByClass(Class className){
        List<Entity> ents = new ArrayList<>();
        for(Entity entity : getEntities()){
            if(entity.getClass() == className){
                ents.add(entity);
            }
        }
        return ents;
    }
    public void setEntities(LinkedList<Entity> entities) {
        this.entities = entities;
    }
    public void tick(){
        entities.removeAll(removeQueue);
        entities.addAll(addQueue);
        addQueue.clear();
        removeQueue.clear();
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
        for(CollisionListener collisionListener : Engine.getEventManager().getCollisionListeners()){
            collisionListener.onCollision(entity,entity2);
        }
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(0,0,Engine.getWIDTH(),Engine.getHEIGHT());
        try{
            entities.forEach((entity)-> entity.draw(g));
        }catch (ConcurrentModificationException ex){}
    }
}
