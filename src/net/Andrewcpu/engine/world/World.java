package net.Andrewcpu.engine.world;

import java.awt.*;
import java.util.*;

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
    private java.util.List<Entity> entities = new ArrayList<>();
    public void addEntity(Entity entity){
        entities.add(entity);
    }
    public void removeEntity(Entity entity){
        entities.remove(entity);
    }
    public boolean doesWorldContainEntity(Entity entity){
        return entities.contains(entity);
    }
    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0,0,500,500);
        for(Entity entity : entities){
            g.setColor(Color.WHITE);
            g.fillRect(entity.getX(),entity.getY(),entity.getWidth(),entity.getHeight());
        }
    }
}
