package com.pandapulsestudios.pulsecore.Events.CustomEvents.BossBar;

import com.pandapulsestudios.pulsecore.BossBar.PandaBossBar;
import com.pandapulsestudios.pulsecore.BossBar.PandaEntityBossBar;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PandaEntityBossBarPlayerAddEvent extends Event implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled;
    private final PandaEntityBossBar pandaBossBar;
    private final Player player;

    public PandaEntityBossBarPlayerAddEvent(PandaEntityBossBar pandaBossBar, Player player){
        this.pandaBossBar = pandaBossBar;
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

    private PandaEntityBossBar getPandaBossBar(){ return pandaBossBar; }
    private Player getPlayer(){ return player; }
}
