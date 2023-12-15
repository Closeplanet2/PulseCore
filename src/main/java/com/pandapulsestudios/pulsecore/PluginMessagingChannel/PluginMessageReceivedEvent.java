package com.pandapulsestudios.pulsecore.PluginMessagingChannel;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PluginMessageReceivedEvent extends Event {
    @Override
    public HandlerList getHandlers() {return handlers;}

    private static final HandlerList handlers = new HandlerList();
    public static HandlerList getHandlerList(){ return handlers; }

    public String channel;
    public Player player;
    public byte[] message;

    public PluginMessageReceivedEvent(String channel, Player player, byte[] message){
        this.channel = channel;
        this.player = player;
        this.message = message;
    }
}
