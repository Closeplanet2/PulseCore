package com.pandapulsestudios.pulsecore.Enchantment;

import com.pandapulsestudios.pulsecore.PulseCore;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class EnchantWrapper extends Enchantment {
    private final String nameSpace;
    private final String enchantmentName;
    private final int enchantmentMaxLvl;
    private final int startLevel;
    private EnchantmentTarget itemTarget;
    private final boolean treasure;
    private final boolean cursed;
    private final String translationKey;
    private final List<Enchantment> conflictsWith;

    public EnchantWrapper(PulseEnchantment pulseEnchantment) {
        this.nameSpace = String.format("%s_%s", PulseCore.class.getSimpleName(), pulseEnchantment.getClass().getSimpleName()).toLowerCase();
        this.enchantmentName = pulseEnchantment.getClass().getSimpleName();
        this.enchantmentMaxLvl = pulseEnchantment.enchantmentMaxLvl();
        this.startLevel = pulseEnchantment.startLevel();
        this.itemTarget = pulseEnchantment.itemTarget();
        this.treasure = pulseEnchantment.treasure();
        this.cursed = pulseEnchantment.cursed();
        this.conflictsWith = pulseEnchantment.conflictsWith();
        this.translationKey = pulseEnchantment.translationKey();
    }

    @Override
    public NamespacedKey getKey() { return NamespacedKey.minecraft(nameSpace); }
    @Override
    public String getName() { return enchantmentName;}
    @Override
    public int getMaxLevel() { return enchantmentMaxLvl;}
    @Override
    public int getStartLevel() { return startLevel; }
    @Override
    public EnchantmentTarget getItemTarget() { return itemTarget; }
    @Override
    public boolean isTreasure() { return treasure; }
    @Override
    public boolean isCursed() { return false; }
    @Override
    public boolean conflictsWith(Enchantment enchantment) { return conflictsWith.contains(enchantment); }
    @Override
    public boolean canEnchantItem(ItemStack itemStack) { return false; }
    @Override
    public String getTranslationKey() { return translationKey;}
}
