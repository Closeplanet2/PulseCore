package com.pandapulsestudios.pulsecore.Enchantment;

import com.pandapulsestudios.pulsecore.PulseCore;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class EnchantmentAPI {
    public static PulseEnchantment ReturnPulseEnchantment(String enchantmentName){
        return PulseCore.CustomEnchantments.getOrDefault(enchantmentName, null);
    }

    public static boolean AddCustomEnchantmentToItemStack(ItemStack itemStack, String encahntmentName){
        var pulseEnchantment = PulseCore.CustomEnchantments.get(encahntmentName);
        return pulseEnchantment.AddEnchantmentToItemStack(itemStack);
    }

    public static List<PulseEnchantment> ReturnCustomEnchantmentOnItem(ItemStack itemStack){
        var data = new ArrayList<PulseEnchantment>();
        if(itemStack != null && itemStack.getItemMeta() != null){
            var itemMeta = itemStack.getItemMeta();
            for(var pulseEnchantmentName : PulseCore.CustomEnchantments.keySet()){
                var pulseEnchantment = PulseCore.CustomEnchantments.get(pulseEnchantmentName);
                if(itemMeta.hasEnchant(pulseEnchantment.ReturnEnchantment())) data.add(pulseEnchantment);
            }
        }
        return data;
    }
}
