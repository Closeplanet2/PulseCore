package com.pandapulsestudios.pulsecore.Enchantment;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;

public class CustomEnchantWrapper extends Enchantment {

    private final String enchantmentName;
    private final int enchantmentMaxLvl;

    public CustomEnchantWrapper(String nameSpace, String enchantmentName, int enchantmentMaxLvl) {
        super(NamespacedKey.minecraft(nameSpace));
        this.enchantmentName = enchantmentName;
        this.enchantmentMaxLvl = enchantmentMaxLvl;
    }

    @Override
    public String getName() {
        return enchantmentName;
    }

    @Override
    public int getMaxLevel() {
        return enchantmentMaxLvl;
    }

    @Override
    public int getStartLevel() {
        return 0;
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return null;
    }

    @Override
    public boolean isTreasure() {
        return false;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    @Override
    public boolean conflictsWith(Enchantment enchantment) {
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack itemStack) {
        return false;
    }
}
