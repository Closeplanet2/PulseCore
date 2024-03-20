package com.pandapulsestudios.pulsecore.Events;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.persistence.PersistentDataType;

public class PersistentDataRemovedEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled;
    private final PersistentDataType persistentDataType;
    private final Block block;
    private final NamespacedKey namespacedKey;

    public PersistentDataRemovedEvent(PersistentDataType persistentDataType, Block block, NamespacedKey namespacedKey){
        this.persistentDataType = persistentDataType;
        this.block = block;
        this.namespacedKey = namespacedKey;
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

    public PersistentDataType getPersistentDataType() {
        return persistentDataType;
    }

    public Block getBlock() {
        return block;
    }

    public NamespacedKey getNamespacedKey() {
        return namespacedKey;
    }
}
