package com.pandapulsestudios.pulsecore.StoredData;

import com.pandapulsestudios.pulsecore.PulseCore;

import java.util.UUID;

public class PlayerDataAPI {
    public static void STORE(UUID player, Object key, Object data){
        PulseCore.PLAYER_DATA.get(player).put(key.toString(), data);
    }

    public static boolean CONTAINS(UUID player, Object key){
        return PulseCore.PLAYER_DATA.get(player).containsKey(key.toString());
    }

    public static void REMOVE(UUID player, Object key){
        PulseCore.PLAYER_DATA.get(player).remove(key.toString());
    }

    public static Object GET(UUID player, Object key){
        return PulseCore.PLAYER_DATA.get(player).getOrDefault(key.toString(), null);
    }
}
