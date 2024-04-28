package com.pandapulsestudios.pulsecore.CamAPI.Events;

import com.pandapulsestudios.pulsecore.CamAPI.Objects.CamPath;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

@Getter
public class PlayerStoppedCamPathEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private final Player player;
    private final CamPath camPath;

    public PlayerStoppedCamPathEvent(Player player, CamPath camPath){
        this.player = player;
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
