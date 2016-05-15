package net.Andrewcpu.engine.world;

import net.Andrewcpu.engine.Engine;
import net.Andrewcpu.engine.display.Renderable;
import net.Andrewcpu.engine.listeners.CollisionListener;
import net.Andrewcpu.engine.world.physics.Solid;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by stein on 5/10/2016.
 */
public class World implements Renderable {
    private static World instance = null;
    private Color color = Color.WHITE;
    private Image backgroundImage = null;
    private int animateTime = 5, currentTime = 0;
    private List<Solid> solidObjects = new ArrayList<>();



    public World(){
        instance = this;
    }
    public static World getInstance() {
        if (instance == null)
            instance = new World();
        return instance;
    }

    public List<Solid> getSolidObjects() {
        return solidObjects;
    }

    public void setSolidObjects(List<Solid> solidObjects) {
        this.solidObjects = solidObjects;
    }

    public void addSolidObject(Solid solid){
        solidObjects.add(solid);
    }

    public void removeSolidObject(Solid solid){
        solidObjects.remove(solid);
    }

    public Image getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(Image backgroundImage) {
        this.backgroundImage = backgroundImage;
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
        currentTime+=1;
        boolean animate = currentTime==animateTime;
        if(animate)
            currentTime = 0;
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
            if(animate)
                entity.frame();
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
        if(getBackgroundImage()!=null)
            g.drawImage(getBackgroundImage(),0,0,Engine.getWIDTH(),Engine.getHEIGHT(),null);
        try{
            entities.forEach((entity)-> entity.draw(g));
            solidObjects.forEach((solid)->solid.draw(g));
        }catch (ConcurrentModificationException ex){}
    }
}
