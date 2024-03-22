package com.pandapulsestudios.pulsecore.Events.CustomEvents.Data;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.persistence.PersistentDataType;

public class PersistentDataAddedEvent extends Event implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled;
    private final PersistentDataType persistentDataType;
    private final Block block;
    private final NamespacedKey namespacedKey;
    private final Object dataObject;

    public PersistentDataAddedEvent(PersistentDataType persistentDataType, Block block,  NamespacedKey namespacedKey, Object dataObject){
        this.persistentDataType = persistentDataType;
        this.block = block;
        this.namespacedKey = namespacedKey;
        this.dataObject = dataObject;
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

    public Object getDataObject() {
        return dataObject;
    }
}
