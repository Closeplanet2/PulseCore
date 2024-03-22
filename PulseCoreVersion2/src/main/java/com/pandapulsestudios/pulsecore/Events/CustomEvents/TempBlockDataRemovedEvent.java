package com.pandapulsestudios.pulsecore.Events.CustomEvents;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class TempBlockDataRemovedEvent extends Event implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled;
    private final Block block;
    private final String key;

    public TempBlockDataRemovedEvent(Block block, String key){
        this.block = block;
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

    public String getKey(){return key;}
    public Block getBlock() {
        return block;
    }
}
