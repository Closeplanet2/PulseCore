package com.pandapulsestudios.pulsecore.HologramAPI.Event;

import com.pandapulsestudios.pulsecore.HologramAPI.Object.Hologram;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

@Getter
public class RightClickHologramEvent extends Event implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled;
    private final Player player;
    private final Hologram hologram;

    public RightClickHologramEvent(Player player, Hologram hologram){
        this.player = player;
        this.hologram = hologram;
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
