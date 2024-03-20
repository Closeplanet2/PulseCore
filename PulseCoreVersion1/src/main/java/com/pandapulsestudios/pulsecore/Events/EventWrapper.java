package com.pandapulsestudios.pulsecore.Events;

import com.pandapulsestudios.pulsecore.Class.PluginAPI;
import com.pandapulsestudios.pulsecore.Class.SoftDependPlugins;
import com.pandapulsestudios.pulsecore.PulseCore;
import com.pandapulsestudios.pulsecore._External.WorldGuard.WorldGuardAPI;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class EventWrapper {
    public PulseCoreEvents pulseCoreEvents;
    public CustomCoreEvents customCoreEvents;

    public EventWrapper(PulseCoreEvents pulseCoreEvents, CustomCoreEvents customCoreEvents){
        this.pulseCoreEvents = pulseCoreEvents;
        this.customCoreEvents = customCoreEvents;
    }

    public boolean CanDoEvent(Player player, Location location){
        if(player != null){
            if(customCoreEvents.op() && !player.isOp()) return false;
            for(var perm : customCoreEvents.perms()) if(!player.hasPermission(perm)) return false;
        }

        var isInWorld = customCoreEvents.worlds().length == 0 || Arrays.stream(customCoreEvents.worlds()).toList().contains(location.getWorld().getName());
        if(!isInWorld) return false;

        var isInRegion = true;
        if(PluginAPI.IsPluginInstalled(PulseCore.Instance, SoftDependPlugins.WorldGuard)){
            for(var regionName : customCoreEvents.regions()){
                var state = WorldGuardAPI.REGION.IsLocationInRegion(location.getWorld(), regionName, location);
                if(!state) isInRegion = false;
            }
        }

        return isInRegion;
    }
}
