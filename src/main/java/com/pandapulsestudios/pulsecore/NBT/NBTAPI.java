package com.pandapulsestudios.pulsecore.NBT;

import com.pandapulsestudios.pulsecore.PulseCoreMain;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class NBTAPI {
    public static String Get(ItemStack itemStack, String key){
        if(itemStack == null || key == null) return null;
        if(!itemStack.hasItemMeta()) return null;

        ItemMeta itemMeta = itemStack.getItemMeta();
        PersistentDataContainer pdc = itemMeta.getPersistentDataContainer();
        NamespacedKey namespacedKey = new NamespacedKey(PulseCoreMain.Instance, key);
        if(pdc.has(namespacedKey, PersistentDataType.STRING)) return pdc.get(namespacedKey, PersistentDataType.STRING);
        return null;
    }

    public static String Get(Entity entity, String key){
        if(entity == null || key == null) return null;
        PersistentDataContainer pdc = entity.getPersistentDataContainer();
        NamespacedKey namespacedKey = new NamespacedKey(PulseCoreMain.Instance,key);
        if(pdc.has(namespacedKey, PersistentDataType.STRING)) return pdc.get(namespacedKey, PersistentDataType.STRING);
        return null;
    }

    public static boolean Add(ItemStack itemStack, String key, String value){
        if(itemStack == null || key == null) return false;
        if(!itemStack.hasItemMeta()) return false;
        ItemMeta meta = itemStack.hasItemMeta() ? itemStack.getItemMeta() : Bukkit.getItemFactory().getItemMeta(itemStack.getType());
        PersistentDataContainer pdc = meta.getPersistentDataContainer();
        NamespacedKey namespacedKey = new NamespacedKey(PulseCoreMain.Instance, key);
        pdc.set(namespacedKey, PersistentDataType.STRING, value);
        itemStack.setItemMeta(meta);
        return true;
    }

    public static boolean Add(Entity entity, String key, String value){
        if(entity == null || key == null) return false;
        PersistentDataContainer pdc = entity.getPersistentDataContainer();
        NamespacedKey namespacedKey = new NamespacedKey(PulseCoreMain.Instance, key);
        pdc.set(namespacedKey, PersistentDataType.STRING, value);
        return true;
    }

    public static boolean Has(ItemStack itemStack, String key){
        if(itemStack == null || key == null) return false;
        if(!itemStack.hasItemMeta()) return false;
        ItemMeta meta = itemStack.getItemMeta();
        PersistentDataContainer pdc = meta.getPersistentDataContainer();
        return pdc.has(new NamespacedKey(PulseCoreMain.Instance,key),PersistentDataType.STRING);
    }

    public static boolean Has(Entity entity, String key){
        if(entity == null || key == null) return false;
        PersistentDataContainer pdc = entity.getPersistentDataContainer();
        return pdc.has(new NamespacedKey(PulseCoreMain.Instance,key),PersistentDataType.STRING);
    }

    public static boolean Remove(ItemStack itemStack, String key){
        if(itemStack == null || key == null) return false;
        if(!itemStack.hasItemMeta()) return false;
        ItemMeta meta = itemStack.getItemMeta();
        PersistentDataContainer pdc = meta.getPersistentDataContainer();
        pdc.remove(new NamespacedKey(PulseCoreMain.Instance,key));
        itemStack.setItemMeta(meta);
        return true;
    }

    public static boolean Remove(Entity entity, String key){
        if(entity == null || key == null) return false;
        PersistentDataContainer pdc = entity.getPersistentDataContainer();
        pdc.remove(new NamespacedKey(PulseCoreMain.Instance,key));
        return true;
    }
}
