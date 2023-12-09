package com.pandapulsestudios.pulsecore.StoredData;

import com.pandapulsestudios.pulsecore.PulseCore;

public class ServerDataAPI {
    public static void STORE(Object key, Object data, boolean overwrite){
        if(CONTAINS(key) && !overwrite) return;
        PulseCore.SERVER_DATA.put(key.toString(), data);
    }

    public static boolean CONTAINS(Object key){
        return PulseCore.SERVER_DATA.containsKey(key.toString());
    }

    public static void REMOVE(Object key){
        PulseCore.SERVER_DATA.remove(key.toString());
    }

    public static Object GET(Object key){
        return PulseCore.SERVER_DATA.getOrDefault(key.toString(), null);
    }
}
