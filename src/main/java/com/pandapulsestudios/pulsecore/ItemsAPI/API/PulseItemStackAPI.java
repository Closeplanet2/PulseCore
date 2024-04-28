package com.pandapulsestudios.pulsecore.ItemsAPI.API;

import com.pandapulsestudios.pulsecore.ItemsAPI.Interface.PulseItemStack;
import com.pandapulsestudios.pulsecore.NBTAPI.API.NBTAPI;
import com.pandapulsestudios.pulsecore.PulseCore;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

public class PulseItemStackAPI {

    public static PulseItemStack ReturnPulseItemFromName(String itemName){
        return PulseCore.PulseItemStacks.getOrDefault(itemName, null);
    }

    public static ItemStack ReturnItemFromName(String itemName){
        var pulseItemStack = ReturnPulseItemFromName(itemName);
        return pulseItemStack == null ? null : ReturnItemStack(pulseItemStack);
    }

    public static ItemStack ReturnItemStack(PulseItemStack pulseItemStack){
        ItemStack itemStack = new ItemStack(pulseItemStack.itemType(), pulseItemStack.itemAmount());
        var itemMeta = itemStack.getItemMeta();
        if(itemMeta != null){
            itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', pulseItemStack.itemName()));
            itemMeta.setLore(pulseItemStack.itemLore());
            for(var enchantment : pulseItemStack.itemEnchantments().keySet()) itemMeta.addEnchant(enchantment, pulseItemStack.itemEnchantments().get(enchantment), true);
            for(var itemFlag : pulseItemStack.itemFlags()) itemMeta.addItemFlags(itemFlag);
            for(var attribute : pulseItemStack.attributeModifiers().keySet()) itemMeta.addAttributeModifier(pulseItemStack.attributeModifiers().get(attribute), attribute);
            if(pulseItemStack.customModelData() > 0) itemMeta.setCustomModelData(pulseItemStack.customModelData());
            itemMeta.setUnbreakable(pulseItemStack.unbreakable());
        }
        itemStack.setItemMeta(itemMeta);
        for(String key : pulseItemStack.nbtTags().keySet()) NBTAPI.Add(null, itemStack, PersistentDataType.STRING, key, pulseItemStack.nbtTags().get(key));
        return itemStack;
    }

    public static void AddToInventory(PulseItemStack pulseItemStack, Player... players){ for(var player : players) AddToInventory(pulseItemStack, player.getInventory()); }
    public static void AddToInventory(PulseItemStack pulseItemStack, Inventory inventory){ inventory.addItem(ReturnItemStack(pulseItemStack)); }
}
