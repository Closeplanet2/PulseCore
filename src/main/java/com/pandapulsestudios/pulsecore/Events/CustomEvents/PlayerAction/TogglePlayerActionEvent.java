package com.pandapulsestudios.pulsecore.Events.CustomEvents.PlayerAction;

import com.pandapulsestudios.pulsecore.Player.PlayerAction;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class TogglePlayerActionEvent extends Event implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled;
    private final Player player;
    private final PlayerAction playerAction;
    private final boolean newState;

    public TogglePlayerActionEvent(Player player, PlayerAction playerAction, boolean newState){
        this.player = player;
        this.playerAction = playerAction;
        this.newState = newState;
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
    public PlayerAction getPlayerAction(){ return playerAction; }
    public Boolean getNewState(){ return newState; }
}
