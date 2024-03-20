package com.pandapulsestudios.pulsecore.Events;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class TempBlockDataAddedEvent extends Event implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled;
    private final Block block;
    private final String key;
    private final Object dataObject;

    public TempBlockDataAddedEvent(Block block, String key, Object dataObject){
        this.block = block;
        this.key = key;
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

    public String getKey(){return key;}
    public Block getBlock() {
        return block;
    }
    public Object getDataObject() {
        return dataObject;
    }
}
