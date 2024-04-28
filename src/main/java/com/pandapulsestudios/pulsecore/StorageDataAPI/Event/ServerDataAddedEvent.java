package com.pandapulsestudios.pulsecore.StorageDataAPI.Event;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

@Getter
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

    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }
}
