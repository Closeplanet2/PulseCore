package com.pandapulsestudios.pulsecore.Enchantment;

import com.pandapulsestudios.pulsecore.PulseCore;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public interface PulseEnchantment {
    default String enchantmentName(){ return getClass().getSimpleName(); }
    default String nameSpace(){ return String.format("%s_%s", PulseCore.class.getSimpleName(), enchantmentName()).toLowerCase(); }
    default int enchantmentMaxLvl(){ return 1; };
    default int startLevel(){ return 0; };
    default EnchantmentTarget itemTarget(){ return null; }
    default boolean treasure(){ return false; };
    default boolean cursed(){ return false; };
    default String translationKey(){return null;}
    default List<Enchantment> conflictsWith(){ return new ArrayList<>(); };
    default Enchantment ReturnEnchantment(){ return new EnchantWrapper(this); }
    default boolean AddEnchantmentToItemStack(ItemStack itemStack){
        var itemMeta = itemStack.getItemMeta();
        itemMeta.addEnchant(ReturnEnchantment(), 1, true);
        itemStack.setItemMeta(itemMeta);
        return itemStack.getEnchantmentLevel(ReturnEnchantment()) != 0;
    }
}
