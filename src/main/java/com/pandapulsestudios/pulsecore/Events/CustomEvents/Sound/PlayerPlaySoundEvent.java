package com.pandapulsestudios.pulsecore.Events.CustomEvents.Sound;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerPlaySoundEvent extends Event implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled;
    private final Player player;
    private final Location soundLocation;
    private final Sound minecraftSound;

    public PlayerPlaySoundEvent(Player player, Location soundLocation, Sound minecraftSound){
        this.player = player;
        this.soundLocation = soundLocation;
        this.minecraftSound= minecraftSound;
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

    public Player getPlayer(){ return player; }
    public Location getSoundLocation(){ return soundLocation; }
    public Sound getMinecraftSound(){ return minecraftSound; }
}
