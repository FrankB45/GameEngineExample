package net.Andrewcpu.tests;

import net.Andrewcpu.engine.Engine;
import net.Andrewcpu.engine.world.Entity;
import net.Andrewcpu.engine.listeners.CollisionListener;
import net.Andrewcpu.engine.listeners.EventManager;

/**
 * Created by stein on 5/10/2016.
 */
public class Main {
    public static void main(String[] args){
        EventManager eventManager = Engine.getEventManager();
        //1.7
        eventManager.registerCollisionListener(new CollisionListener() {
            @Override
            public void onCollision(Entity e1, Entity e2) {
                System.out.println(e1.getUUID().toString() + " hit " + e2.getUUID().toString());
            }
        });
        //1.8

    }
}
