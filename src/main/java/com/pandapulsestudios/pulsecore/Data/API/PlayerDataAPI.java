package com.pandapulsestudios.pulsecore.Data.API;

import com.pandapulsestudios.pulsecore.PulseCore;

import java.util.HashMap;
import java.util.UUID;

public class PlayerDataAPI {
    public static void STORE(UUID player, Object key, Object data){
        var playerData = PulseCore.PlayerData.getOrDefault(player, new HashMap<>());
        playerData.put(key.toString(), data);
        PulseCore.PlayerData.put(player, playerData);
    }

    public static boolean CONTAINS(UUID player, Object key){
        var playerData = PulseCore.PlayerData.getOrDefault(player, new HashMap<>());
        return playerData.containsKey(key.toString());
    }

    public static void REMOVE(UUID player, Object key){
        var playerData = PulseCore.PlayerData.getOrDefault(player, new HashMap<>());
        playerData.remove(key.toString());
        PulseCore.PlayerData.put(player, playerData);
    }

    public static Object GET(UUID player, Object key, Object defaultt){
        var playerData = PulseCore.PlayerData.getOrDefault(player, new HashMap<>());
        return playerData.getOrDefault(key.toString(), defaultt);
    }
}
