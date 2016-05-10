package net.Andrewcpu.engine.listeners;

import net.Andrewcpu.engine.world.Entity;

/**
 * Created by stein on 5/10/2016.
 */
public interface CollisionListener {
    void onCollision(Entity e1, Entity e2);
}
