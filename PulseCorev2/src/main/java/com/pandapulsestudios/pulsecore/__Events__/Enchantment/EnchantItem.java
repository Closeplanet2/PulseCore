package com.pandapulsestudios.pulsecore.__Events__.Enchantment;

import com.pandapulsestudios.pulsecore.Block.PersistentDataAPI;
import com.pandapulsestudios.pulsecore.Enchantment.EnchantmentAPI;
import com.pandapulsestudios.pulsecore.Events.CustomEvent;
import com.pandapulsestudios.pulsecore.Items.ItemStackAPI;
import com.pandapulsestudios.pulsecore.Location.LocationAPI;
import com.pandapulsestudios.pulsecore.NBT.NBTAPI;
import com.pandapulsestudios.pulsecore.Player.PlayerAPI;
import com.pandapulsestudios.pulsecore.Player.Enums.PlayerAction;
import com.pandapulsestudios.pulsecore.PulseCoreMain;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;

@CustomEvent
public class EnchantItem implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEvent(EnchantItemEvent event){
        var inventoryItems = PlayerAPI.ReturnALlPlayerItems(event.getEnchanter());

        for(var itemStack : inventoryItems.keySet()){
            if(itemStack.getItemMeta() == null) continue;
            for(var nbtListener : PulseCoreMain.nbtListeners){
                if(!event.isCancelled() && nbtListener.EnchantItemEvent(event, itemStack, NBTAPI.GetAll(itemStack), event.getEnchanter())) event.setCancelled(true);
            }

            for(var pulseEnchantment : EnchantmentAPI.ReturnCustomEnchantmentOnItems(itemStack)){
                if(!event.isCancelled() && pulseEnchantment.EnchantItemEvent(event, itemStack, inventoryItems.get(itemStack))) event.setCancelled(true);
            }

            var pulseItemStack = ItemStackAPI.ReturnPulseItem(itemStack);
            if(pulseItemStack != null) if(!event.isCancelled() && pulseItemStack.EnchantItemEvent(event, itemStack, inventoryItems.get(itemStack))) event.setCancelled(true);
        }

        if(PulseCoreMain.handlePlayerActionEventsInHouse){
            if(!event.isCancelled() && !PlayerAPI.CanDoAction(PlayerAction.EnchantItem, event.getEnchanter())) event.setCancelled(true);
        }

        var world = event.getEnchanter().getLocation().getWorld();
        if(PulseCoreMain.playerActionLock.containsKey(world)){
            if(!event.isCancelled()) event.setCancelled(PulseCoreMain.playerActionLock.get(world).contains(PlayerAction.EnchantItem));
        }

        for(var pulseLocation :  LocationAPI.ReturnAllPulseLocations(event.getEnchanter().getLocation(), true)){
            if(!event.isCancelled() && pulseLocation.EnchantItemEvent(event, event.getEnchanter().getLocation())) event.setCancelled(true);
        }

        for(var coreEvent : PulseCoreMain.pulseCoreEvents) if(!event.isCancelled() && coreEvent.EnchantItemEvent(event)) event.setCancelled(true);
    }
}
