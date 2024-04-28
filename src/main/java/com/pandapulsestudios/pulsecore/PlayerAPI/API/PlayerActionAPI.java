package com.pandapulsestudios.pulsecore.PlayerAPI.API;

import com.pandapulsestudios.pulsecore.PlayerAPI.Enum.PlayerAction;
import com.pandapulsestudios.pulsecore.PulseCore;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.UUID;

public class PlayerActionAPI {
    public static ArrayList<PlayerAction> ReturnPlayerLocks(UUID uuid){
        return PulseCore.PlayerActions.getOrDefault(uuid, new ArrayList<>());
    }

    public static boolean CanPlayerAction(PlayerAction playerAction, UUID player){
        var playerActionData = ReturnPlayerLocks(player);
        return !playerActionData.contains(playerAction);
    }

    public static void LockPlayerAction(PlayerAction playerAction, UUID player){
        var playerActionData = ReturnPlayerLocks(player);
        playerActionData.add(playerAction);
        PulseCore.PlayerActions.put(player, playerActionData);
    }

    public static void UnLockPlayerAction(PlayerAction playerAction, UUID player){
        var playerActionData = ReturnPlayerLocks(player);
        playerActionData.remove(playerAction);
        PulseCore.PlayerActions.put(player, playerActionData);
    }
}
