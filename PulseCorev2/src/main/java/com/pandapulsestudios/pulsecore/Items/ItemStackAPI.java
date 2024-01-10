package com.pandapulsestudios.pulsecore.Items;

import com.pandapulsestudios.pulsecore.NBT.NBTAPI;
import com.pandapulsestudios.pulsecore.PulseCoreMain;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ItemStackAPI {
    public static int CountItem(Player player, ItemStack itemStack){
        if(player == null || itemStack == null) return 0;
        return CountItem(player.getInventory(), itemStack);
    }

    public static int CountItem(Inventory inventory, ItemStack itemStack){
        if(inventory == null || itemStack == null) return 0;
        var count = 0;
        for(var item : inventory.getContents()){
            if(IsItemTheSame(item, itemStack)) count += item.getAmount();
        }
        return count;
    }

    public static int CountItem(Player player, Material itemStack){
        if(player == null || itemStack == null) return 0;
        return CountItem(player.getInventory(), itemStack);
    }

    public static int CountItem(Inventory inventory, Material itemStack){
        if(inventory == null || itemStack == null) return 0;
        var count = 0;
        for(var item : inventory.getContents()){
            if(item.getType() == itemStack) count += item.getAmount();
        }
        return count;
    }

    public static boolean IsItemTheSame(ItemStack a, ItemStack b){
        if(a == null || b == null) return false;
        return a.isSimilar(b);
    }

    public static PulseItemStack ReturnPulseItem(String itemName){
        for(var pulseItemName : PulseCoreMain.registeredItemStacks.keySet()){
            if(pulseItemName.equals(itemName)) return PulseCoreMain.registeredItemStacks.get(pulseItemName);
        }
        return null;
    }

    public static PulseItemStack ReturnPulseItem(ItemStack itemStack){
        for(var pulseItemName : PulseCoreMain.registeredItemStacks.keySet()){
            var pulseItemStack = PulseCoreMain.registeredItemStacks.get(pulseItemName);
            if(pulseItemStack.returnItemStack().equals(itemStack)) return pulseItemStack;
        }
        return null;
    }
}
