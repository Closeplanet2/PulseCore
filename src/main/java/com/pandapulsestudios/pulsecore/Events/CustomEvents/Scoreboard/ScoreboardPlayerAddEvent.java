package com.pandapulsestudios.pulsecore.Events.CustomEvents.Scoreboard;

import com.pandapulsestudios.pulsecore.Scoreboard.PulseScoreboard;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ScoreboardPlayerAddEvent extends Event implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled;
    private final PulseScoreboard pulseScoreboard;
    private final Player player;

    public ScoreboardPlayerAddEvent(PulseScoreboard pulseScoreboard, Player player){
        this.pulseScoreboard = pulseScoreboard;
        this.player = player;
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

    public PulseScoreboard getPulseScoreboard(){ return pulseScoreboard; }
    public Player getPlayer(){ return player; }
}
