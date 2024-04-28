package com.pandapulsestudios.pulsecore.InventoryAPI.API;

import com.pandapulsestudios.pulsecore.ItemsAPI.Enum.ItemLocation;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.HashMap;

public class InventoryAPI {
    public static HashMap<ItemStack, ItemLocation> ReturnALlItemsWithLocation(LivingEntity livingEntity){
        var foundInformation = new HashMap<ItemStack, ItemLocation>();
        for(var itemStack : livingEntity.getEquipment().getArmorContents()) foundInformation.put(itemStack, ItemLocation.EntityArmor);
        foundInformation.put(livingEntity.getEquipment().getItemInOffHand(), ItemLocation.EntityMainHand);
        foundInformation.put(livingEntity.getEquipment().getItemInOffHand(), ItemLocation.EntityOffHand);
        return foundInformation;
    }

    public static HashMap<ItemStack, ItemLocation> ReturnALlItemsWithLocation(PlayerInventory playerInventory){
        var foundInformation = new HashMap<ItemStack, ItemLocation>();
        for(var itemStack : playerInventory.getContents()){
            if(itemStack == null) continue;
            else if(itemStack.isSimilar(playerInventory.getItemInMainHand())) foundInformation.put(itemStack, ItemLocation.EntityMainHand);
            else if(itemStack.isSimilar(playerInventory.getItemInOffHand())) foundInformation.put(itemStack, ItemLocation.EntityOffHand);
            else if(ArmorContentsContains(playerInventory.getArmorContents(), itemStack)) foundInformation.put(itemStack, ItemLocation.EntityArmor);
            else foundInformation.put(itemStack, ItemLocation.EntityInventory);
        }
        return foundInformation;
    }

    private static boolean ArmorContentsContains(ItemStack[] itemStacks, ItemStack itemStack){
        for(var i : itemStacks) if(itemStack.isSimilar(i)) return true;
        return false;
    }
}