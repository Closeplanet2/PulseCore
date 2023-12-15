package com.pandapulsestudios.pulsecore.Items;

import com.pandapulsestudios.pulsecore.Chat.ChatAPI;
import com.pandapulsestudios.pulsecore.Chat.Enums.MessageType;
import com.pandapulsestudios.pulsecore.NBT.NBTAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface PulseItemStack {
    default String itemName(){ return "ItemName"; }
    default Material itemType(){ return Material.DIAMOND_PICKAXE; }
    default int itemAmount(){ return 1; }
    default List<String> itemLore(){ return new ArrayList<>(); }
    default List<ItemFlag> itemFlags(){ return new ArrayList<>(); }
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
        }
        itemStack.setItemMeta(itemMeta);
        for(String key : nbtTags().keySet()) NBTAPI.AddNBT(itemStack, key, nbtTags().get(key));
        return itemStack;
    }

    default void giveToPlayers(Player... players){ for(var player : players) addToInventory(player.getInventory());}
    default void addToInventory(Inventory inventory){
        inventory.addItem(returnItemStack());
    }
    default boolean isItemStack(ItemStack itemStack){return ItemStackAPI.IsItemTheSame(itemStack, returnItemStack());}
    default void RegisteredItemStack(){ ChatAPI.SendChat(String.format("&3Registered ItemStack: %s", itemName()), MessageType.ConsoleMessageNormal, true); }
    default boolean BlockBreakEvent(BlockBreakEvent event){ return false; }
}
