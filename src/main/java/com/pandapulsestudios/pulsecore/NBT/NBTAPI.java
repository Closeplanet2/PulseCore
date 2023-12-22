package com.pandapulsestudios.pulsecore.NBT;

import com.pandapulsestudios.pulsecore.PulseCoreMain;
import com.sun.jna.platform.win32.WinDef;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class NBTAPI {
    public static HashMap<String, Object> GetAll(ItemStack itemStack, PersistentDataType persistentDataType){
        var data = new HashMap<String, Object>();
        if(itemStack == null || itemStack.getItemMeta() == null) return data;
        var pdc = itemStack.getItemMeta().getPersistentDataContainer();
        for(var namespacedKey : pdc.getKeys()) data.put(namespacedKey.getKey(), pdc.get(namespacedKey, persistentDataType));
        return data;
    }

    public static HashMap<String, Object> GetAll(ItemStack itemStack){
        var data = new HashMap<String, Object>();
        if(itemStack == null || !itemStack.hasItemMeta()) return data;
        var pdc = itemStack.getItemMeta().getPersistentDataContainer();
        for(var persistentDataType : PersistentDataTypes.values()){
            for(var namespacedKey : pdc.getKeys()) data.put(namespacedKey.getKey(), pdc.get(namespacedKey, persistentDataType.persistentDataType));
        }
        return data;
    }

    public static Object Get(JavaPlugin javaPlugin, ItemStack itemStack, String key, PersistentDataType persistentDataType){
        if(itemStack == null || !itemStack.hasItemMeta()) return null;
        javaPlugin = javaPlugin == null ? PulseCoreMain.Instance : javaPlugin;
        var pdc = itemStack.getItemMeta().getPersistentDataContainer();
        var namespacedKey = new NamespacedKey(javaPlugin, key);
        return pdc.has(namespacedKey, persistentDataType) ? pdc.get(namespacedKey, persistentDataType) : null;
    }

    public static void Add(JavaPlugin javaPlugin, ItemStack itemStack, PersistentDataType persistentDataType, String key, Object value){
        if(itemStack == null || !itemStack.hasItemMeta()) return;
        javaPlugin = javaPlugin == null ? PulseCoreMain.Instance : javaPlugin;
        var itemMeta = itemStack.getItemMeta();
        var pdc = itemMeta.getPersistentDataContainer();
        var namespacedKey = new NamespacedKey(javaPlugin, key);
        pdc.set(namespacedKey, persistentDataType, value);
        itemStack.setItemMeta(itemMeta);
    }

    public static boolean Has(JavaPlugin javaPlugin, ItemStack itemStack, PersistentDataType persistentDataType, String key){
        if(itemStack == null || !itemStack.hasItemMeta()) return false;
        javaPlugin = javaPlugin == null ? PulseCoreMain.Instance : javaPlugin;
        return itemStack.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(javaPlugin, key), persistentDataType);
    }

    public static void Remove(JavaPlugin javaPlugin, ItemStack itemStack, String key){
        if(itemStack == null || !itemStack.hasItemMeta()) return;
        javaPlugin = javaPlugin == null ? PulseCoreMain.Instance : javaPlugin;
        itemStack.getItemMeta().getPersistentDataContainer().remove(new NamespacedKey(javaPlugin, key));
    }
}

