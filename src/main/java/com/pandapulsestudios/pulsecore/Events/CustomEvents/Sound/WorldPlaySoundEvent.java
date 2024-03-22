package com.pandapulsestudios.pulsecore.Events.CustomEvents.Sound;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class WorldPlaySoundEvent extends Event implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled;
    private final World world;
    private final Location soundLocation;
    private final Sound minecraftSound;

    public WorldPlaySoundEvent(World world, Location soundLocation, Sound minecraftSound){
        this.world = world;
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

    public World getWorld(){ return world; }
    public Location getSoundLocation(){ return soundLocation; }
    public Sound getMinecraftSound(){ return minecraftSound; }
}
