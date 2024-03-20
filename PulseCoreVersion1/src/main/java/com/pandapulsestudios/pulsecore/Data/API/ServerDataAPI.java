package com.pandapulsestudios.pulsecore.Data.API;

import com.pandapulsestudios.pulsecore.PulseCore;

public class ServerDataAPI {
    public static void STORE(Object key, Object data, boolean overwrite){
        if(CONTAINS(key) && !overwrite) return;
        PulseCore.ServerData.put(key.toString(), data);
    }

    public static boolean CONTAINS(Object key){
        return PulseCore.ServerData.containsKey(key.toString());
    }

    public static void REMOVE(Object key){
        PulseCore.ServerData.remove(key.toString());
    }

    public static Object GET(Object key, Object defaultt){
        return PulseCore.ServerData.getOrDefault(key.toString(), defaultt);
    }
}
