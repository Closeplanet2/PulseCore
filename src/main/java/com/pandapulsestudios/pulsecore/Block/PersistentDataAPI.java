package com.pandapulsestudios.pulsecore.Block;

import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.TileState;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

public class PersistentDataAPI {
    public static void Add(JavaPlugin javaPlugin, Block block, PersistentDataType persistentDataType, String persistentDataKey, Object data){
        if(!(block instanceof TileState titleState)) return;
        var persistentDataContainer = titleState.getPersistentDataContainer();
        var namedSpaceKey = new NamespacedKey(javaPlugin, persistentDataKey);
        persistentDataContainer.set(namedSpaceKey, persistentDataType, data);
        titleState.update();
    }

    public static boolean Has(JavaPlugin javaPlugin, Block block, String persistentDataKey){
        if(!(block instanceof TileState titleState)) return false;
        var persistentDataContainer = titleState.getPersistentDataContainer();
        var namedSpaceKey = new NamespacedKey(javaPlugin, persistentDataKey);
        return persistentDataContainer.getKeys().contains(namedSpaceKey);
    }

    public static void Remove(JavaPlugin javaPlugin, Block block, String persistentDataKey){
        if(!(block instanceof TileState titleState)) return;
        var persistentDataContainer = titleState.getPersistentDataContainer();
        var namedSpaceKey = new NamespacedKey(javaPlugin, persistentDataKey);
        persistentDataContainer.remove(namedSpaceKey);
        titleState.update();
    }
}
