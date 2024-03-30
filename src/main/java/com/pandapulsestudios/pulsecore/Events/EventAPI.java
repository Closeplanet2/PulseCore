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

        if(location.getWorld() != null){
            for(var world : pulseCoreEvents.worlds()){
                if(!location.getWorld().getName().equals(world)) return false;
            }
        }

        if(location.getWorld() != null && PluginAPI.IsPluginInstalled(PulseCore.Instance, SoftDependPlugins.WorldGuard)){
            var runningState = true;
            for(var region : pulseCoreEvents.regions()){
                runningState = WorldGuardAPI.REGION.IsLocationInRegion(location.getWorld(), region, location);
            }
            return runningState;
        }

        return true;
    }
}
