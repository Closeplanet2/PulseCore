package com.pandapulsestudios.pulsecore.Items;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface PulseItemStack {
    default String itemName(){ return getClass().getSimpleName(); }
    default Material itemType(){ return Material.DIAMOND_PICKAXE; }
    default int itemAmount(){ return 1; }
    default int customModelData(){ return -1; }
    default boolean unbreakable(){ return false; }
    default List<String> itemLore(){ return new ArrayList<>(); }
    default List<ItemFlag> itemFlags(){ return new ArrayList<>(); }
    default HashMap<AttributeModifier, Attribute> attributeModifiers(){ return new HashMap<>(); }
    default HashMap<String, String> nbtTags(){ return new HashMap<>(); }
    default HashMap<Enchantment, Integer> itemEnchantments(){ return new HashMap<>(); }
}
