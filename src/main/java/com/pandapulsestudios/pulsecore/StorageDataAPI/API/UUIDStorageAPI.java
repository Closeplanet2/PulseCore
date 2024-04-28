package com.pandapulsestudios.pulsecore.StorageDataAPI.API;

import com.pandapulsestudios.pulsecore.StorageDataAPI.Event.UUIDDataAddedEvent;
import com.pandapulsestudios.pulsecore.StorageDataAPI.Event.UUIDDataGetEvent;
import com.pandapulsestudios.pulsecore.StorageDataAPI.Event.UUIDDataRemovedEvent;
import com.pandapulsestudios.pulsecore.StorageDataAPI.Objects.StorageObject;
import com.pandapulsestudios.pulsecore.PulseCore;

import java.util.LinkedHashMap;
import java.util.UUID;

public class UUIDStorageAPI {
    public static StorageObject Store(UUID uuid, Object key, Object data){
        if(new UUIDDataAddedEvent(uuid, key.toString(), data).isCancelled()) return null;
        var storageObjects = PulseCore.UUIDStorageObjects.getOrDefault(uuid, new LinkedHashMap<String, StorageObject>());
        var storageObject = new StorageObject(data);
        storageObjects.put(key.toString(), storageObject);
        PulseCore.UUIDStorageObjects.put(uuid, storageObjects);
        return storageObject;
    }

    public static boolean Contains(UUID uuid, Object key){
        var storageObjects = PulseCore.UUIDStorageObjects.getOrDefault(uuid, new LinkedHashMap<String, StorageObject>());
        return storageObjects.containsKey(key.toString());
    }

    public static StorageObject Remove(UUID uuid, Object key){
        if(new UUIDDataRemovedEvent(uuid, key.toString()).isCancelled()) return null;
        var storageObjects = PulseCore.UUIDStorageObjects.getOrDefault(uuid, new LinkedHashMap<String, StorageObject>());
        return storageObjects.remove(key.toString());
    }

    public static StorageObject Get(UUID uuid, Object key, Object defaultObject){
        if(new UUIDDataGetEvent(uuid, key.toString()).isCancelled()) return null;
        var storageObjects = PulseCore.UUIDStorageObjects.getOrDefault(uuid, new LinkedHashMap<String, StorageObject>());
        return storageObjects.getOrDefault(key.toString(), new StorageObject(defaultObject));
    }
}
