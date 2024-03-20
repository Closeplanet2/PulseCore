package com.pandapulsestudios.pulsecore.Data;

import com.pandapulsestudios.pulsecore.Events.TempBlockDataAddedEvent;
import com.pandapulsestudios.pulsecore.Events.TempBlockDataRemovedEvent;
import com.pandapulsestudios.pulsecore.PulseCore;
import org.bukkit.block.Block;

import java.util.LinkedHashMap;

/**
 * Data stored using this API will reset on server restart!
 */
public class TempBlockDataAPI {
    /**
     * Get all stored data from block!
     * @param block block to get the stored data from!
     * @return LinkedHashMap<String, Object>!
     */
    public static LinkedHashMap<String, Object> GetALl(Block block){
        return PulseCore.customBlockData.getOrDefault(block, new LinkedHashMap<>());
    }

    /**
     * Get stored data form block and key
     * @param block block to get the stored data from!
     * @param nameSpacedKey key for the data!
     * @return Object!
     */
    public static Object Get(Block block, String nameSpacedKey){
        return GetALl(block).getOrDefault(nameSpacedKey, null);
    }

    /**
     * Check if block has data assigned to it!
     * @param block block to get the stored data from!
     * @param nameSpacedKey key for the data!
     * @return boolean
     */
    public static boolean Has(Block block, String nameSpacedKey){
        return GetALl(block).containsValue(nameSpacedKey);
    }

    /**
     * Add data to block!
     * @param block block to get the stored data from!
     * @param nameSpacedKey key for the data!
     * @param object object to assign to the block!
     */
    public static void Add(Block block, String nameSpacedKey, Object object){
        var getAll = PulseCore.customBlockData.getOrDefault(block, new LinkedHashMap<>());
        var tempBlockDataAddedEvent = new TempBlockDataAddedEvent(block, nameSpacedKey, object);
        if(tempBlockDataAddedEvent.isCancelled()) return;
        getAll.put(nameSpacedKey, object);
        PulseCore.customBlockData.put(block, getAll);
    }

    /**
     * Remove data from block
     * @param block block to get the stored data from!
     * @param nameSpacedKey key for the data!
     */
    public static void Remove(Block block, String nameSpacedKey){
        var getAll = PulseCore.customBlockData.getOrDefault(block, new LinkedHashMap<>());
        var tempBlockDataRemovedEvent = new TempBlockDataRemovedEvent(block, nameSpacedKey);
        if(tempBlockDataRemovedEvent.isCancelled()) return;
        getAll.remove(nameSpacedKey);
        PulseCore.customBlockData.put(block, getAll);
    }
}
