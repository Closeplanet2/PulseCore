package com.pandapulsestudios.pulsecore.Data.API;

import com.pandapulsestudios.pulsecore.Events.CustomEvents.ServerDataAddedEvent;
import com.pandapulsestudios.pulsecore.Events.CustomEvents.ServerDataRemovedEvent;
import com.pandapulsestudios.pulsecore.PulseCore;

import java.util.LinkedHashMap;

public class ServerDataAPI {
    /**
     * Stores data with the given key!
     * @param key Key to save the data behind!
     * @param data Data to save!
     */
    public static void Store(String key, Object data){
        var serverDataAddedEvent = new ServerDataAddedEvent(key, data);
        if(serverDataAddedEvent.isCancelled()) return;
        PulseCore.serverData.put(key, data);
    }

    /**
     * Checks to see if there is any data stored with the given key!
     * @param key Key to save the data behind!
     * @return boolean
     */
    public static boolean Contains(String key){ return PulseCore.serverData.containsKey(key); }

    /**
     * Removes the data behind the given key!
     * @param key
     */
    public static void Remove(String key){
        var serverDataRemovedEvent = new ServerDataRemovedEvent(key);
        if(serverDataRemovedEvent.isCancelled()) return;
        PulseCore.serverData.remove(key);
    }

    /**
     * Get the data with the given key. If the data doesnt exist return default value
     * @param key
     * @param defaultValue
     * @return Object
     */
    public static Object Get(String key, Object defaultValue){ return PulseCore.serverData.getOrDefault(key, defaultValue); }

    /**
     * Returns all data stored on the server!
     * @return LinkedHashMap<String, Object>
     */
    public static LinkedHashMap<String, Object> ReturnAll(){ return PulseCore.serverData; }
}
