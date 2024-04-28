package com.pandapulsestudios.pulsecore.EventsAPI.API;

import com.pandapulsestudios.pulsecore.EventsAPI.Interface.PulseCoreEvents;
import com.pandapulsestudios.pulsecore.JavaAPI.API.PluginAPI;
import com.pandapulsestudios.pulsecore.JavaAPI.Enum.SoftDependPlugins;
import com.pandapulsestudios.pulsecore.PulseCore;
import com.pandapulsestudios.pulsecore.WorldGuard.API.WorldGuardAPI;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class EventAPI {
    public static boolean CanDoEvent(Player player, Location location, PulseCoreEvents pulseCoreEvents){
        if(player != null){
            if(pulseCoreEvents.op() && !player.isOp()) return false;
            for(var perm : pulseCoreEvents.perms()) if(!player.hasPermission(perm)) return false;
        }

        if(location.getWorld() != null){
            for(var world : pulseCoreEvents.worlds()){
                if(!location.getWorld().getName().equals(world)) return false;
            }
        }

        if(location.getWorld() != null && PluginAPI.IsPluginInstalled(PulseCore.PulseCore, SoftDependPlugins.WorldGuard)){
            var runningState = true;
            for(var region : pulseCoreEvents.regions()){
                runningState = WorldGuardAPI.REGION.IsLocationInRegion(location.getWorld(), region, location);
            }
            return runningState;
        }

        return true;
    }
}