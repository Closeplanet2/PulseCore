package com.pandapulsestudios.pulsecore.Events.CustomEvents.Data;

import org.bukkit.Bukkit;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ServerDataAddedEvent extends Event implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled;
    private final String key;
    private final Object data;

    public ServerDataAddedEvent(String key, Object data){
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

    public String getKey(){ return key; }
    public Object getData(){ return data;}
}
