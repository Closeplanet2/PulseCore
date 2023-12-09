package com.pandapulsestudios.pulsecore.Player;

import com.pandapulsestudios.pulsecore.PulseCore;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class PlayerAPI {
    public static boolean TEST_PERMISSIONS(Player player, String[] perms){
        for(var s : perms) if(!player.hasPermission(s)) return false;
        return true;
    }

    public static List<UUID> CONVERT(List<Player> players){
        var data = new ArrayList<UUID>();
        for(var player : players) data.add(player.getUniqueId());
        return data;
    }

    public static void TOGGLE_STAT(Player player, ToggleActions toggle_stats, boolean state){
        PulseCore.playerToggleActions.get(toggle_stats).put(player.getUniqueId(), state);
    }

    public static boolean GET_TOGGLE_STAT(Player player, ToggleActions toggle_stats){
        var toggleMap = PulseCore.playerToggleActions.getOrDefault(toggle_stats, new HashMap<>());
        return toggleMap.getOrDefault(player.getUniqueId(), true);
    }
}
