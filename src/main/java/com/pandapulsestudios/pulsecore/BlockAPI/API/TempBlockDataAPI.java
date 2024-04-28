package com.pandapulsestudios.pulsecore.BlockAPI.API;

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
        return PulseCore.CustomBlockData.getOrDefault(block, new LinkedHashMap<>());
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
        var getAll = PulseCore.CustomBlockData.getOrDefault(block, new LinkedHashMap<>());
        getAll.put(nameSpacedKey, object);
        PulseCore.CustomBlockData.put(block, getAll);
    }

    /**
     * Remove data from block
     * @param block block to get the stored data from!
     * @param nameSpacedKey key for the data!
     */
    public static void Remove(Block block, String nameSpacedKey){
        var getAll = PulseCore.CustomBlockData.getOrDefault(block, new LinkedHashMap<>());
        getAll.remove(nameSpacedKey);
        PulseCore.CustomBlockData.put(block, getAll);
    }
}
