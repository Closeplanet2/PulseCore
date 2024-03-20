package com.pandapulsestudios.pulsecore.Data;

import com.pandapulsestudios.pulsecore.Events.PersistentDataAddedEvent;
import com.pandapulsestudios.pulsecore.Events.PersistentDataRemovedEvent;
import com.pandapulsestudios.pulsecore.Variable.VariableAPI;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.TileState;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Works With: Banner, Barrel, Beacon, Bed, Beehive, Bell, BlastFurnace, BrewingStand, BrushableBlock, CalibratedSculkSensor, Campfire, Chest, ChiseledBookshelf,
 * CommandBlock, Comparator, Conduit, Container, Crafter, CreatureSpawner, DaylightDetector, DecoratedPot, Dispenser, Dropper, EnchantingTable, EnderChest,
 * EndGateway, EntityBlockStorage<T>, Furnace, HangingSign, Hopper, Jigsaw, Jukebox, Lectern, SculkCatalyst, SculkSensor, SculkShrieker, ShulkerBox, Sign,
 * Skull, Smoker, Structure, SuspiciousSand, TrialSpawner
 */
public class PersistentDataAPI {
    /**
     * Return PersistentDataContainer for give block if instance of @TileState
     * @param block The block to get the PersistentDataContainer for!
     * @return PersistentDataContainer
     */
    public static PersistentDataContainer ReturnPersistentDataContainer(Block block){
        return block instanceof TileState tileState ? tileState.getPersistentDataContainer() : null;
    }

    /**
     * Returns all persistent data types associated with block of all data types!
     * @param block The block to get the persistent data from!
     * @return LinkedHashMap<PersistentDataTypeEnum, LinkedHashMap<String, Object>>!
     */
    public static LinkedHashMap<PersistentDataTypeEnum, LinkedHashMap<String, Object>> GetALl(Block block){
        var data = new LinkedHashMap<PersistentDataTypeEnum, LinkedHashMap<String, Object>>();
        for(var persistentDataTypeEnum : PersistentDataTypeEnum.values()) data.put(persistentDataTypeEnum, GetALl(block, persistentDataTypeEnum.persistentDataType));
        return data;
    }

    /**
     * Returns all persistent data types associated with block!
     * @param block The block to get the persistent data from!
     * @param persistentDataType Type of data to get!
     * @return LinkedHashMap<String, Object>!
     */
    public static LinkedHashMap<String, Object> GetALl(Block block, PersistentDataType persistentDataType){
        var data = new LinkedHashMap<String, Object>();
        var persistentDataContainer = ReturnPersistentDataContainer(block);
        if(persistentDataContainer == null) return data;
        for(var namespacedKey : persistentDataContainer.getKeys()) data.put(namespacedKey.getKey(), persistentDataContainer.get(namespacedKey, persistentDataType));
        return data;
    }

    /**
     * Get single object from data type and key from given block
     * @param block The block to get the persistent data from!
     * @param persistentDataType Type of data to get!
     * @param namespacedKey key of data
     * @return Object
     */
    public static Object Get(Block block, PersistentDataType persistentDataType, NamespacedKey namespacedKey){
        var persistentDataContainer = ReturnPersistentDataContainer(block);
        if(persistentDataContainer == null || !Has(block, namespacedKey)) return null;
        return persistentDataContainer.get(namespacedKey, persistentDataType);
    }

    /**
     * Check to see if block has given namespacedKey
     * @param block Block to check!
     * @param namespacedKey key of data!
     * @return boolean
     */
    public static boolean Has(Block block, NamespacedKey namespacedKey){
        var persistentDataContainer = ReturnPersistentDataContainer(block);
        if(persistentDataContainer == null) return false;
        return persistentDataContainer.getKeys().contains(namespacedKey);
    }

    /**
     * Add a object with the persistentDataType being found from custom Variable Tests!
     * @param block Block to add data to!
     * @param namespacedKey Key of the data
     * @param dataObject Object to add to block
     */
    public static void Add(Block block, NamespacedKey namespacedKey, Object dataObject){
        var persistentDataType = VariableAPI.ReturnTypeFromVariableTest(dataObject);
        if(persistentDataType == null) return;
        Add(block, persistentDataType.persistentDataType, namespacedKey, dataObject);
    }

    /**
     * Add an object to block with a given persistentDataType and namespacedKey (Calls PersistentDataAddedEvent)
     * @param block Block to add data to!
     * @param persistentDataType Data Type
     * @param namespacedKey Key of the data
     * @param dataObject Object to add to block
     */
    public static void Add(Block block, PersistentDataType persistentDataType, NamespacedKey namespacedKey, Object dataObject){
        if(!(block instanceof TileState titleState)) return;
        var persistentDataContainer = titleState.getPersistentDataContainer();
        var persistentDataAddedEvent = new PersistentDataAddedEvent(persistentDataType, block, namespacedKey, dataObject);
        if(persistentDataAddedEvent.isCancelled()) return;
        persistentDataContainer.set(namespacedKey, persistentDataType, dataObject);
        titleState.update();
    }

    /**
     * Remove a object to block with a given persistentDataType and namespacedKey (Calls PersistentDataRemovedEvent)
     * @param block Block to remove data from!
     * @param persistentDataType Data Type
     * @param namespacedKey Key of the data
     */
    public static void Remove(Block block, PersistentDataType persistentDataType, NamespacedKey namespacedKey){
        if(!(block instanceof TileState titleState)) return;
        var persistentDataContainer = titleState.getPersistentDataContainer();
        var persistentDataRemovedEvent = new PersistentDataRemovedEvent(persistentDataType, block, namespacedKey);
        if(persistentDataRemovedEvent.isCancelled()) return;
        persistentDataContainer.remove(namespacedKey);
        titleState.update();
    }


}
