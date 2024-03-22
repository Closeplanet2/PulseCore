package com.pandapulsestudios.pulsecore.Events.CustomEvents.Data;

import org.bukkit.Bukkit;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.UUID;

public class UUIDDataRemovedEvent extends Event implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled;
    private final UUID uuid;
    private final String key;

    public UUIDDataRemovedEvent(UUID uuid, String key){
        this.uuid = uuid;
        this.key = key;
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
}
