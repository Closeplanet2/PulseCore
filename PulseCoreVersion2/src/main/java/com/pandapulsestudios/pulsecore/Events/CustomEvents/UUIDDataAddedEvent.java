package com.pandapulsestudios.pulsecore.Events.CustomEvents;

import org.bukkit.Bukkit;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.UUID;

public class UUIDDataAddedEvent extends Event implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled;
    private final UUID uuid;
    private final String key;
    private final Object data;

    public UUIDDataAddedEvent(UUID uuid, String key, Object data){
        this.uuid = uuid;
        this.key = key;
        this.data = data;
        Bukkit.getServer().getPluginManager().callEvent(this);
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }

    public UUID getUUID(){return uuid;}
    public String getKey(){ return key; }
    public Object getData(){ return data;}
}
