package com.pandapulsestudios.pulsecore.HologramAPI.API;

import com.pandapulsestudios.pulsecore.HologramAPI.Event.RightClickHologramEvent;
import com.pandapulsestudios.pulsecore.HologramAPI.Object.Hologram;
import com.pandapulsestudios.pulsecore.PulseCore;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

public class HologramAPI {
    public static Hologram ReturnHologram(String hologramName){
        return PulseCore.Holograms.getOrDefault(hologramName, null);
    }

    public static void CheckForEventClick(PlayerInteractAtEntityEvent event){
        if (event.getRightClicked().getType().equals(EntityType.ARMOR_STAND)){
            var armorStand = (ArmorStand) event.getRightClicked();
            for(var value : PulseCore.Holograms.values()){
                if(value.IsArmorStand(armorStand)){
                    new RightClickHologramEvent(event.getPlayer(), value);
                    return;
                }
            }
        }
    }
}
