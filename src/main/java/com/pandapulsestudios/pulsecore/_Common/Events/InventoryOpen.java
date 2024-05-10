package com.pandapulsestudios.pulsecore._Common.Events;

import com.pandapulsestudios.pulsecore.JavaAPI.Interface.PulseAutoRegister;
import com.pandapulsestudios.pulsecore.PulseCore;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;

@PulseAutoRegister
public class InventoryOpen implements Listener {
    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent event){
        PulseCore.LastInventoryOpenEvent.put(event.getPlayer().getUniqueId(), event);
    }
}
