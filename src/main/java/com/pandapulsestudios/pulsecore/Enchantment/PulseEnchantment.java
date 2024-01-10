package com.pandapulsestudios.pulsecore.Enchantment;

import com.pandapulsestudios.pulsecore.Chat.ChatAPI;
import com.pandapulsestudios.pulsecore.Chat.MessageType;
import com.pandapulsestudios.pulsecore.Items.ItemLocation;
import com.pandapulsestudios.pulsecore.PulseCore;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.event.block.*;
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
    default List<Enchantment> conflictsWith(){ return new ArrayList<>(); };
    default Enchantment ReturnEnchantment(){ return new EnchantWrapper(this); }
    default boolean AddEnchantmentToItemStack(ItemStack itemStack){
        var itemMeta = itemStack.getItemMeta();
        itemMeta.addEnchant(ReturnEnchantment(), 1, true);
        itemStack.setItemMeta(itemMeta);
        return itemStack.getEnchantmentLevel(ReturnEnchantment()) != 0;
    }
    default void RegisteredEnchantment(){
        ChatAPI.SendChat(String.format("&3Registered Enchantment: %s", enchantmentName()), MessageType.ConsoleMessageNormal, true, null);
    }
    default boolean BlockBreakEvent(BlockBreakEvent event, ItemStack itemStack, ItemLocation itemLocation){ return false; }
    default boolean BlockPlaceEvent(BlockPlaceEvent event, ItemStack itemStack, ItemLocation itemLocation){ return false; }
    default boolean BlockCanBuildEvent(BlockCanBuildEvent event, ItemStack itemStack, ItemLocation itemLocation){ return false; }
    default boolean BlockDamageEvent(BlockDamageEvent event, ItemStack itemStack, ItemLocation itemLocation){ return false; }
    default boolean BlockIgniteEvent(BlockIgniteEvent event, ItemStack itemStack, ItemLocation itemLocation){ return false; }
}
