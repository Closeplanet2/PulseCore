package com.pandapulsestudios.pulsecore.Events;

import com.pandapulsestudios.pulsecore.Java.PluginAPI;
import com.pandapulsestudios.pulsecore.Java.SoftDependPlugins;
import com.pandapulsestudios.pulsecore.PulseCore;
import com.pandapulsestudios.pulsecore._External.WorldGuard.WorldGuardAPI;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class EventAPI {
    public static boolean CanDoEvent(Player player, Location location, PulseCoreEvents pulseCoreEvents){
        if(player != null){
            if(pulseCoreEvents.op() && !player.isOp()) return false;
            for(var perm : pulseCoreEvents.perms()) if(!player.hasPermission(perm)) return false;
        }

        var isInWorld = pulseCoreEvents.worlds().length == 0 || Arrays.stream(pulseCoreEvents.worlds()).toList().contains(location.getWorld().getName());
        if(!isInWorld) return false;

        var isInRegion = true;
        if(PluginAPI.IsPluginInstalled(PulseCore.Instance, SoftDependPlugins.WorldGuard)){
            for(var regionName : pulseCoreEvents.regions()){
                var state = WorldGuardAPI.REGION.IsLocationInRegion(location.getWorld(), regionName, location);
                if(!state) isInRegion = false;
            }
        }

        return isInRegion;
    }
}
