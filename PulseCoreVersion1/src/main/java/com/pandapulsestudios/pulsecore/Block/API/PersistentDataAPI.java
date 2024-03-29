package com.pandapulsestudios.pulsecore.Block.API;

import com.pandapulsestudios.pulsecore.Block.Interface.PersistentDataCallbacks;
import com.pandapulsestudios.pulsecore.PulseCore;
import com.pandapulsestudios.pulsecore._Common.Enums.PersistentDataTypes;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.TileState;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class PersistentDataAPI {
    public static HashMap<String, Object> GetAll(Block block, PersistentDataType persistentDataType){
        var data = new HashMap<String, Object>();
        if(!(block instanceof TileState titleState)) return data;
        var persistentDataContainer = titleState.getPersistentDataContainer();
        for(var namespacedKey : persistentDataContainer.getKeys()){
            data.put(namespacedKey.getKey(), persistentDataContainer.get(namespacedKey, persistentDataType));
        }
        return data;
    }

    public static HashMap<String, Object> GetAll(Block block){
        var data = new HashMap<String, Object>();
        if(!(block instanceof TileState titleState)) return data;
        var persistentDataContainer = titleState.getPersistentDataContainer();
        for(var persistentDataType : PersistentDataTypes.values()){
            for(var namespacedKey : persistentDataContainer.getKeys()){
                data.put(namespacedKey.getKey(), persistentDataContainer.get(namespacedKey, persistentDataType.persistentDataType));
            }
        }
        return data;
    }

    public static Object Get(JavaPlugin javaPlugin, Block block, PersistentDataType persistentDataType, String persistentDataKey){
        if(!(block instanceof TileState titleState)) return null;
        javaPlugin = javaPlugin == null ? PulseCore.Instance : javaPlugin;
        var persistentDataContainer = titleState.getPersistentDataContainer();
        var namedSpaceKey = new NamespacedKey(javaPlugin, persistentDataKey);
        return persistentDataContainer.get(namedSpaceKey, persistentDataType);
    }

    public static void Add(JavaPlugin javaPlugin, Block block, PersistentDataType persistentDataType, String persistentDataKey, Object data){
        if(!(block instanceof TileState titleState)) return;
        javaPlugin = javaPlugin == null ? PulseCore.Instance : javaPlugin;
        var persistentDataContainer = titleState.getPersistentDataContainer();
        var namedSpaceKey = new NamespacedKey(javaPlugin, persistentDataKey);
        persistentDataContainer.set(namedSpaceKey, persistentDataType, data);
        titleState.update();
    }

    public static boolean Has(JavaPlugin javaPlugin, Block block, String persistentDataKey){
        if(!(block instanceof TileState titleState)) return false;
        javaPlugin = javaPlugin == null ? PulseCore.Instance : javaPlugin;
        var persistentDataContainer = titleState.getPersistentDataContainer();
        var namedSpaceKey = new NamespacedKey(javaPlugin, persistentDataKey);
        return persistentDataContainer.getKeys().contains(namedSpaceKey);
    }

    public static void Remove(JavaPlugin javaPlugin, Block block, String persistentDataKey){
        if(!(block instanceof TileState titleState)) return;
        javaPlugin = javaPlugin == null ? PulseCore.Instance : javaPlugin;
        var persistentDataContainer = titleState.getPersistentDataContainer();
        var namedSpaceKey = new NamespacedKey(javaPlugin, persistentDataKey);
        persistentDataContainer.remove(namedSpaceKey);
        titleState.update();
    }

    public static boolean CanBeCalled(PersistentDataCallbacks callback, Block block){
        var allTags = GetAll(block);
        for(var key : callback.BlockHasTags()) if(!allTags.containsKey(key)) return false;
        if(callback.BlockIsMaterial() != null) if(block.getType() != callback.BlockIsMaterial()) return false;
        return true;
    }
}
