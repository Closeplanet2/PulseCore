package com.pandapulsestudios.pulsecore.Player;

import com.pandapulsestudios.pulsecore.PulseCore;
import org.bukkit.entity.Player;

import java.util.LinkedHashMap;

public class PlayerAPI {

    public static LinkedHashMap<PlayerAction, Boolean> ReturnPlayerActionData(Player player){
        return PulseCore.playerActionLocks.getOrDefault(player.getUniqueId(), new LinkedHashMap<>());
    }

    public static boolean CanPlayerAction(PlayerAction playerAction, Player player){
        var playerActionData = ReturnPlayerActionData(player);
        return playerActionData.getOrDefault(playerAction, true);
    }

    public static void TogglePlayerAction(PlayerAction playerAction, boolean actionState, Player player){
        var playerActionData = ReturnPlayerActionData(player);
        playerActionData.put(playerAction, actionState);
        PulseCore.playerActionLocks.put(player.getUniqueId(), playerActionData);
    }
}
