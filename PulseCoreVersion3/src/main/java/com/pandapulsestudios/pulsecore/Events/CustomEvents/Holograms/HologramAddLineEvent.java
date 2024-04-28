package com.pandapulsestudios.pulsecore.Events.CustomEvents.Holograms;

import com.pandapulsestudios.pulsecore.Holograms.Hologram;
import org.bukkit.Bukkit;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class HologramAddLineEvent extends Event implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled;
    private final Hologram hologram;
    private final String newLine;

    public HologramAddLineEvent(Hologram hologram, String newLine){
        this.hologram = hologram;
        this.newLine = newLine;
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

    public Hologram getHologram(){ return hologram; }
    public String getNewLine(){ return newLine; }
}
