package com.pandapulsestudios.pulsecore.StoredData;

import com.pandapulsestudios.pulsecore.PulseCoreMain;

public class ServerDataAPI {
    public static void STORE(Object key, Object data, boolean overwrite){
        if(CONTAINS(key) && !overwrite) return;
        PulseCoreMain.SERVER_DATA.put(key.toString(), data);
    }

    public static boolean CONTAINS(Object key){
        return PulseCoreMain.SERVER_DATA.containsKey(key.toString());
    }

    public static void REMOVE(Object key){
        PulseCoreMain.SERVER_DATA.remove(key.toString());
    }

    public static Object GET(Object key){
        return PulseCoreMain.SERVER_DATA.getOrDefault(key.toString(), null);
    }
}
