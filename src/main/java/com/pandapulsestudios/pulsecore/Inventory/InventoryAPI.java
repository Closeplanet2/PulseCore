package com.pandapulsestudios.pulsecore.Inventory;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryAPI {
    public static ItemStack AddItem(Inventory inventory, ItemStack itemStack, int amount){
        var newItemStack = itemStack.clone();
        newItemStack.setAmount(amount);
        inventory.addItem(newItemStack);
        return newItemStack;
    }

    public static void RemoveItem(Inventory inventory, ItemStack itemStack, int amount){
        if(itemStack.getAmount() - amount < 0) return;
        if(itemStack.getAmount() == 1) inventory.removeItem(itemStack);
        else itemStack.setAmount(itemStack.getAmount() - amount);
    }
}
