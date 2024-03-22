package com.pandapulsestudios.pulsecore.Data.API;

import com.pandapulsestudios.pulsecore.Events.CustomEvents.Data.UUIDDataAddedEvent;
import com.pandapulsestudios.pulsecore.Events.CustomEvents.Data.UUIDDataRemovedEvent;
import com.pandapulsestudios.pulsecore.PulseCore;

import java.util.LinkedHashMap;
import java.util.UUID;

public class UUIDDataAPI {
    /**
     * Store data for the UUID with given key and data!
     * @param uuid UUID for the data to be associated with!
     * @param key Key to store the data behind!
     * @param data Data to store!
     */
    public static void Store(UUID uuid, String key, Object data){
        var storedData = GetAll(uuid);
        var uuidDataAddedEvent = new UUIDDataAddedEvent(uuid, key, data);
        if(uuidDataAddedEvent.isCancelled()) return;
        storedData.put(key, data);
        PulseCore.uuidData.put(uuid, storedData);
    }

    /**
     * Check if the data is contained behind the UUID!
     * @param uuid UUID for the data to be associated with!
     * @param key Key to store the data behind!
     * @return boolean
     */
    public static boolean Contains(UUID uuid, String key){ return GetAll(uuid).containsKey(key); }

    /**
     * Remove the data from the UUID data if the key exists!
     * @param uuid UUID for the data to be associated with!
     * @param key Key to store the data behind!
     */
    public static void Remove(UUID uuid, String key){
        var storedData = GetAll(uuid);
        var uuidDataRemovedEvent = new UUIDDataRemovedEvent(uuid, key);
        if(uuidDataRemovedEvent.isCancelled()) return;
        storedData.remove(key);
        PulseCore.uuidData.put(uuid, storedData);
    }

    /**
     * Get the data from the UUID with the given key. If the data doesn't exist, return the default value
     * @param uuid UUID for the data to be associated with!
     * @param key Key to store the data behind!
     * @param defaultValue Default value to return!
     * @return Object
     */
    public static Object Get(UUID uuid, String key, Object defaultValue){ return GetAll(uuid).getOrDefault(key, defaultValue); }

    /**
     * Return all the data stored behind the UUID
     * @param uuid UUID for the data to be associated with!
     * @return LinkedHashMap<String, Object>
     */
    public static LinkedHashMap<String, Object> GetAll(UUID uuid){ return PulseCore.uuidData.getOrDefault(uuid, new LinkedHashMap<>()); }
}
