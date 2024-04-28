package com.pandapulsestudios.pulsecore.EnchantmentAPI.API;

import com.pandapulsestudios.pulsecore.EnchantmentAPI.Interface.PulseEnchantment;
import com.pandapulsestudios.pulsecore.PulseCore;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class EnchantmentAPI {
    public static LinkedHashMap<String, PulseEnchantment> ReturnAllStoredEnchantments(){ return PulseCore.PulseEnchantments; }

    public static ArrayList<PulseEnchantment> ReturnAllCustomEnchantmentsFromItem(ItemStack itemStack){
        var data = new ArrayList<PulseEnchantment>();
        if(itemStack == null || itemStack.getItemMeta() == null || PulseCore.PulseEnchantments.isEmpty()) return data;
        var itemStackMeta = itemStack.getItemMeta();
        for(var pulseEnchantment : PulseCore.PulseEnchantments.values()) if(itemStackMeta.hasEnchant(pulseEnchantment.ReturnEnchantment())) data.add(pulseEnchantment);
        return data;
    }

    public static PulseEnchantment ReturnPulseEnchantment(String pulseEnchantmentName){
        return PulseCore.PulseEnchantments.getOrDefault(pulseEnchantmentName, null);
    }

    public static boolean AddPulseEnchantmentToItemStack(ItemStack itemStack, String pulseEnchantmentName){
        var pulseEnchantment = ReturnPulseEnchantment(pulseEnchantmentName);
        if(pulseEnchantment != null && itemStack != null && itemStack.getItemMeta() != null) return pulseEnchantment.AddEnchantmentToItemStack(itemStack);
        return false;
    }
}
