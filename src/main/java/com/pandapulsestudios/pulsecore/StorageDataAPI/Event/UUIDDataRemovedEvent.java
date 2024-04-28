package com.pandapulsestudios.pulsecore.StorageDataAPI.Event;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

@Getter
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

    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }

    public UUID getUUID(){return uuid;}
}
