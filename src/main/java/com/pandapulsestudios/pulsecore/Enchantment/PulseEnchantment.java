package com.pandapulsestudios.pulsecore.Enchantment;

import com.pandapulsestudios.pulsecore.Chat.ChatAPI;
import com.pandapulsestudios.pulsecore.Chat.Enums.MessageType;
import com.pandapulsestudios.pulsecore.PulseCoreMain;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public interface PulseEnchantment {
    int enchantmentMaxLvl();
    default String nameSpace(){
        return String.format("%s_%s", PulseCoreMain.class.getSimpleName(), enchantmentName()).toLowerCase();
    }
    default String enchantmentName(){
        return getClass().getSimpleName();
    }
    default Enchantment ReturnEnchantment(){
        return new EnchantWrapper(nameSpace(), enchantmentName(), enchantmentMaxLvl());
    }
    default void RegisteredEnchantment(Enchantment enchantment){ ChatAPI.SendChat(String.format("&3Registered Enchantment: %s", enchantmentName()), MessageType.ConsoleMessageNormal, true); }
    default boolean BlockBreakEvent(BlockBreakEvent event, ItemStack itemStack){ return false; }
}
