package com.pandapulsestudios.pulsecore.StoredData;

import com.pandapulsestudios.pulsecore.PulseCoreMain;

import java.util.UUID;

public class PlayerDataAPI {
    public static void STORE(UUID player, Object key, Object data){
        PulseCoreMain.playerData.get(player).put(key.toString(), data);
    }

    public static boolean CONTAINS(UUID player, Object key){
        return PulseCoreMain.playerData.get(player).containsKey(key.toString());
    }

    public static void REMOVE(UUID player, Object key){
        PulseCoreMain.playerData.get(player).remove(key.toString());
    }

    public static Object GET(UUID player, Object key){
        return PulseCoreMain.playerData.get(player).getOrDefault(key.toString(), null);
    }
}
