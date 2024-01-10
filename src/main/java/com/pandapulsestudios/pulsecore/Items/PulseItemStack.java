package com.pandapulsestudios.pulsecore.Items;

import com.pandapulsestudios.pulsecore.Chat.ChatAPI;
import com.pandapulsestudios.pulsecore.Chat.MessageType;
import com.pandapulsestudios.pulsecore.NBT.NBTAPI;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.block.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface PulseItemStack {
    default String itemName(){ return getClass().getSimpleName(); }
    default Material itemType(){ return Material.DIAMOND_PICKAXE; }
    default int itemAmount(){ return 1; }
    default int customModelData(){ return -1; }
    default boolean unbreakable(){ return false; }
    default List<String> itemLore(){ return new ArrayList<>(); }
    default List<ItemFlag> itemFlags(){ return new ArrayList<>(); }
    default HashMap<AttributeModifier, Attribute> attributeModifiers(){ return new HashMap<>(); }
    default HashMap<String, String> nbtTags(){ return new HashMap<>(); }
    default HashMap<Enchantment, Integer> itemEnchantments(){ return new HashMap<>(); }

    default ItemStack returnItemStack(){
        ItemStack itemStack = new ItemStack(itemType(), itemAmount());
        var itemMeta = itemStack.getItemMeta();
        if(itemMeta != null){
            itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', itemName()));
            itemMeta.setLore(itemLore());
            for(var enchantment : itemEnchantments().keySet()) itemMeta.addEnchant(enchantment, itemEnchantments().get(enchantment), true);
            for(var itemFlag : itemFlags()) itemMeta.addItemFlags(itemFlag);
            for(var attribute : attributeModifiers().keySet()) itemMeta.addAttributeModifier(attributeModifiers().get(attribute), attribute);
            if(customModelData() > 0) itemMeta.setCustomModelData(customModelData());
            itemMeta.setUnbreakable(unbreakable());
        }
        itemStack.setItemMeta(itemMeta);
        for(String key : nbtTags().keySet()) NBTAPI.Add(null, itemStack, PersistentDataType.STRING, key, nbtTags().get(key));
        return itemStack;
    }

    default void giveToPlayers(Player... players){ for(var player : players) addToInventory(player.getInventory());}
    default void addToInventory(Inventory inventory){
        inventory.addItem(returnItemStack());
    }

    default void RegisteredItemStack(){ ChatAPI.SendChat(String.format("&3Registered ItemStack: %s", itemName()), MessageType.ConsoleMessageNormal, true, null); }
    default boolean BlockBreakEvent(BlockBreakEvent event, ItemStack itemStack, ItemLocation itemLocation){ return false; }
    default boolean BlockPlaceEvent(BlockPlaceEvent event, ItemStack itemStack, ItemLocation itemLocation){ return false; }
    default boolean BlockCanBuildEvent(BlockCanBuildEvent event, ItemStack itemStack, ItemLocation itemLocation){ return false; }
    default boolean BlockDamageEvent(BlockDamageEvent event, ItemStack itemStack, ItemLocation itemLocation){ return false; }
    default boolean BlockIgniteEvent(BlockIgniteEvent event, ItemStack itemStack, ItemLocation itemLocation){ return false; }
}
