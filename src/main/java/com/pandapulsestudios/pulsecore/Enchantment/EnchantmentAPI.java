package com.pandapulsestudios.pulsecore.Enchantment;

import com.pandapulsestudios.pulsecore.PulseCoreMain;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class EnchantmentAPI {
    public static List<PulseEnchantment> ReturnCustomEnchantmentOnItems(ItemStack itemStack){
        var data = new ArrayList<PulseEnchantment>();
        if(itemStack == null || itemStack.getItemMeta() == null) return data;
        var itemMeta = itemStack.getItemMeta();
        for(var pulseEnchantment : PulseCoreMain.registeredEnchantments){
            if(itemMeta.hasEnchant(pulseEnchantment.ReturnEnchantment())) data.add(pulseEnchantment);
        }
        return data;
    }
}
