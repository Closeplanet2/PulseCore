package com.pandapulsestudios.pulsecore.CamAPI.Events;

import com.pandapulsestudios.pulsecore.CamAPI.Objects.CamPath;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class CamPathStopEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private final CamPath camPath;

    public CamPathStopEvent(CamPath camPath){
        this.camPath = camPath;
        Bukkit.getServer().getPluginManager().callEvent(this);
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public @NotNull HandlerList getHandlers() {
        return handlers;
    }
}
