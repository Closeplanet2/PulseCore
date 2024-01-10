package com.pandapulsestudios.pulsecore.StoredData;

import com.pandapulsestudios.pulsecore.PulseCoreMain;

import java.util.HashMap;
import java.util.UUID;

public class PlayerDataAPI {
    public static void STORE(UUID player, Object key, Object data){
        var playerData = PulseCoreMain.playerData.getOrDefault(player, new HashMap<>());
        playerData.put(key.toString(), data);
        PulseCoreMain.playerData.put(player, playerData);
    }

    public static boolean CONTAINS(UUID player, Object key){
        var playerData = PulseCoreMain.playerData.getOrDefault(player, new HashMap<>());
        return playerData.containsKey(key.toString());
    }

    public static void REMOVE(UUID player, Object key){
        var playerData = PulseCoreMain.playerData.getOrDefault(player, new HashMap<>());
        playerData.remove(key.toString());
        PulseCoreMain.playerData.put(player, playerData);
    }

    public static Object GET(UUID player, Object key, Object defaultt){
        var playerData = PulseCoreMain.playerData.getOrDefault(player, new HashMap<>());
        return playerData.getOrDefault(key.toString(), defaultt);
    }
}
