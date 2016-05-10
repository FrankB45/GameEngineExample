package net.Andrewcpu.engine.world;

import java.util.UUID;

/**
 * Created by stein on 5/10/2016.
 */
public class Entity {
    private UUID uuid = UUID.randomUUID();
    public UUID getUUID() {
        return uuid;
    }
    public void setUUID(UUID uuid) {
        this.uuid = uuid;
    }
}
