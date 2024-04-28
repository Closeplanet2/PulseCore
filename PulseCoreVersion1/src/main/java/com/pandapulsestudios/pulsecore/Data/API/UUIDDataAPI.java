package com.pandapulsestudios.pulsecore.StorageDataAPI.API;

import com.pandapulsestudios.pulsecore.PulseCore;

import java.util.HashMap;
import java.util.UUID;

public class UUIDDataAPI {
    public static void STORE(UUID player, Object key, Object data){
        var playerData = PulseCore.UUIDData.getOrDefault(player, new HashMap<>());
        playerData.put(key.toString(), data);
        PulseCore.UUIDData.put(player, playerData);
    }

    public static boolean CONTAINS(UUID player, Object key){
        var playerData = PulseCore.UUIDData.getOrDefault(player, new HashMap<>());
        return playerData.containsKey(key.toString());
    }

    public static void REMOVE(UUID player, Object key){
        var playerData = PulseCore.UUIDData.getOrDefault(player, new HashMap<>());
        playerData.remove(key.toString());
        PulseCore.UUIDData.put(player, playerData);
    }

    public static Object GET(UUID player, Object key, Object defaultt){
        var playerData = PulseCore.UUIDData.getOrDefault(player, new HashMap<>());
        return playerData.getOrDefault(key.toString(), defaultt);
    }
}
