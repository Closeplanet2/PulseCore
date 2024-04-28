package com.pandapulsestudios.pulsecore.StorageDataAPI.API;

import com.pandapulsestudios.pulsecore.StorageDataAPI.Event.ServerDataAddedEvent;
import com.pandapulsestudios.pulsecore.StorageDataAPI.Event.ServerDataGetEvent;
import com.pandapulsestudios.pulsecore.StorageDataAPI.Event.ServerDataRemovedEvent;
import com.pandapulsestudios.pulsecore.StorageDataAPI.Objects.StorageObject;
import com.pandapulsestudios.pulsecore.PulseCore;

public class ServerStorageAPI {
    public static StorageObject Store(Object key, Object data){
        if(new ServerDataAddedEvent(key.toString(), data).isCancelled()) return null;
        var storageObject = new StorageObject(data);
        PulseCore.ServerStorageObjects.put(key.toString(), storageObject);
        return storageObject;
    }

    public static boolean Contains(Object key){
        return PulseCore.ServerStorageObjects.containsKey(key.toString());
    }

    public static StorageObject Remove(Object key){
        if(new ServerDataRemovedEvent(key.toString()).isCancelled()) return null;
        return PulseCore.ServerStorageObjects.remove(key.toString());
    }

    public static StorageObject Get(Object key, Object defaultObject){
        if(new ServerDataGetEvent(key.toString()).isCancelled()) return null;
        return PulseCore.ServerStorageObjects.getOrDefault(key.toString(), new StorageObject(defaultObject));
    }
}
