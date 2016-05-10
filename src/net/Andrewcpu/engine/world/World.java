package net.Andrewcpu.engine.world;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by stein on 5/10/2016.
 */
public class World implements Renderable{
    private static World instance = null;
    public World(){
        instance = this;
    }
    public static World getInstance() {
        if (instance == null)
            instance = new World();
        return instance;
    }
    private java.util.LinkedList<Entity> entities = new LinkedList<>();
    public void addEntity(Entity entity){
        entities.add(entity);
    }
    public void removeEntity(Entity entity){
        entities.remove(entity);
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

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0,0,500,500);
        for(Entity entity : entities)
            entity.draw(g);
    }
}
