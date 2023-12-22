package com.pandapulsestudios.pulsecore.__Events__.Player;

import com.pandapulsestudios.pulsecore.Block.PersistentDataAPI;
import com.pandapulsestudios.pulsecore.Enchantment.EnchantmentAPI;
import com.pandapulsestudios.pulsecore.Events.CustomEvent;
import com.pandapulsestudios.pulsecore.Items.ItemLocation;
import com.pandapulsestudios.pulsecore.Items.ItemStackAPI;
import com.pandapulsestudios.pulsecore.Location.LocationAPI;
import com.pandapulsestudios.pulsecore.NBT.NBTAPI;
import com.pandapulsestudios.pulsecore.Player.Enums.PlayerAction;
import com.pandapulsestudios.pulsecore.Player.PlayerAPI;
import com.pandapulsestudios.pulsecore.PulseCoreMain;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerItemBreakEvent;
import org.bukkit.inventory.ItemStack;

@CustomEvent
public class PlayerItemBreak implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEvent(PlayerItemBreakEvent event){
        var inventoryItems = PlayerAPI.ReturnALlPlayerItems(event.getPlayer());
        for(var itemStack : inventoryItems.keySet()) TestItemStack(event, itemStack, inventoryItems.get(itemStack));
        TestItemStack(event, event.getBrokenItem(), ItemLocation.BrokenItem);
        for(var pulseLocation :  LocationAPI.ReturnAllPulseLocations(event.getPlayer().getLocation(), true)) pulseLocation.PlayerItemBreakEvent(event, event.getPlayer().getLocation());
    }

    private void TestItemStack(PlayerItemBreakEvent event, ItemStack itemStack, ItemLocation itemLocation){
        if(itemStack.getItemMeta() == null) return;
        for(var nbtListener : PulseCoreMain.nbtListeners) nbtListener.PlayerItemBreakEvent(event, itemStack, NBTAPI.GetAll(itemStack), event.getPlayer());
        for(var pulseEnchantment : EnchantmentAPI.ReturnCustomEnchantmentOnItems(itemStack)) pulseEnchantment.PlayerItemBreakEvent(event, itemStack, itemLocation);
        var pulseItemStack = ItemStackAPI.ReturnPulseItem(itemStack);
        if(pulseItemStack != null) pulseItemStack.PlayerItemBreakEvent(event, itemStack, itemLocation);
    }
}
