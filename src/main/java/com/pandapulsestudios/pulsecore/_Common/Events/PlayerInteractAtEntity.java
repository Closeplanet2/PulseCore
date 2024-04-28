package com.pandapulsestudios.pulsecore._Common.Events;

import com.pandapulsestudios.pulsecore.HologramAPI.API.HologramAPI;
import com.pandapulsestudios.pulsecore.HologramAPI.Event.RightClickHologramEvent;
import com.pandapulsestudios.pulsecore.JavaAPI.Interface.PulseAutoRegister;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

@PulseAutoRegister
public class PlayerInteractAtEntity implements Listener {
    @EventHandler
    public void onEntityInteract(PlayerInteractAtEntityEvent event){
        HologramAPI.CheckForEventClick(event);
    }

    @EventHandler
    public void onHologramClick(RightClickHologramEvent event){
        event.getPlayer().sendMessage(event.getHologram().getClass().getSimpleName());
    }
}
