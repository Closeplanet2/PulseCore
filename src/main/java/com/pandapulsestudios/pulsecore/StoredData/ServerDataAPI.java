package com.pandapulsestudios.pulsecore.StoredData;

import com.pandapulsestudios.pulsecore.PulseCoreMain;

public class ServerDataAPI {
    public static void STORE(Object key, Object data, boolean overwrite){
        if(CONTAINS(key) && !overwrite) return;
        PulseCoreMain.serverData.put(key.toString(), data);
    }

    public static boolean CONTAINS(Object key){
        return PulseCoreMain.serverData.containsKey(key.toString());
    }

    public static void REMOVE(Object key){
        PulseCoreMain.serverData.remove(key.toString());
    }

    public static Object GET(Object key){
        return PulseCoreMain.serverData.getOrDefault(key.toString(), null);
    }
}
