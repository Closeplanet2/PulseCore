package com.pandapulsestudios.pulsecore._Common.Events.Enchantment;

import com.pandapulsestudios.pulsecore.Block.API.PersistentDataAPI;
import com.pandapulsestudios.pulsecore.Enchantment.EnchantmentAPI;
import com.pandapulsestudios.pulsecore.Events.EventAPI;
import com.pandapulsestudios.pulsecore.Inventory.InventoryAPI;
import com.pandapulsestudios.pulsecore.Items.ItemStackAPI;
import com.pandapulsestudios.pulsecore.Java.PulseAutoRegister;
import com.pandapulsestudios.pulsecore.Location.LocationAPI;
import com.pandapulsestudios.pulsecore.NBT.NBTAPI;
import com.pandapulsestudios.pulsecore.Player.PlayerAPI;
import com.pandapulsestudios.pulsecore.Player.PlayerAction;
import com.pandapulsestudios.pulsecore.PulseCore;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;

@PulseAutoRegister
public class EnchantItem implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEvent(EnchantItemEvent event){

        for(var pulseCoreEvent : PulseCore.customCoreEvents.values()){
            if(EventAPI.CanDoEvent(event.getEnchanter(), event.getEnchantBlock().getLocation(), pulseCoreEvent)){
                var state = pulseCoreEvent.EnchantItemEvent(event);
                if(!event.isCancelled()) event.setCancelled(state);
            }
        }

        for(var pulseLocation :  LocationAPI.ReturnAllPulseLocations(event.getEnchanter().getLocation(), true)){
            var state = pulseLocation.EnchantItemEvent(event, event.getEnchanter().getLocation());
            if(!event.isCancelled()) event.setCancelled(state);
        }

        if(PulseCore.handlePlayerAction){
            var state = PlayerAPI.CanPlayerAction(PlayerAction.EnchantItem, event.getEnchanter());
            if(!event.isCancelled()) event.setCancelled(!state);
        }

        var playerWorld = event.getEnchanter().getWorld();
        if(PulseCore.PlayerActionLock.containsKey(playerWorld)){
            var state = PulseCore.PlayerActionLock.get(playerWorld).contains(PlayerAction.EnchantItem);
            if(!event.isCancelled()) event.setCancelled(state);
        }

        for(var pulseLocation : LocationAPI.ReturnAllPulseLocations(event.getEnchanter().getLocation(), true)){
            var state = pulseLocation.EnchantItemEvent(event, event.getEnchantBlock().getLocation());
            if(!event.isCancelled()) event.setCancelled(state);
        }

        var playerInventoryItems = InventoryAPI.ReturnALlItemsWithLocation(event.getEnchanter().getInventory());
        for(var itemStack : playerInventoryItems.keySet()){
            if(itemStack.getItemMeta() == null) continue;

            for(var nbtListener : PulseCore.customNBTListener.values()){
                if(!NBTAPI.DoesItemStackContainNBTTags(itemStack, nbtListener.BlockHasTags())) continue;
                var state = nbtListener.EnchantItemEvent(event, itemStack, NBTAPI.GetAll(itemStack), event.getEnchanter());
                if(!event.isCancelled()) event.setCancelled(state);
            }

            for(var pulseEnchantment : EnchantmentAPI.ReturnAllCustomEnchantmentsFromItem(itemStack)){
                var state = pulseEnchantment.EnchantItemEvent(event, itemStack, playerInventoryItems.get(itemStack));
                if(!event.isCancelled()) event.setCancelled(state);
            }

            var pulseItemStack = ItemStackAPI.ReturnPulseItem(itemStack);
            if(pulseItemStack != null){
                var state = pulseItemStack.EnchantItemEvent(event, itemStack, playerInventoryItems.get(itemStack));
                if(!event.isCancelled()) event.setCancelled(state);
            }
        }

        for(var persistentDataCallback : PulseCore.customPersistentDataCallbacks.values()){
            if(!PersistentDataAPI.CanBeCalled(persistentDataCallback, event.getEnchantBlock())) continue;
            var feedbackState = persistentDataCallback.EnchantItemEvent(event, event.getEnchantBlock(), PersistentDataAPI.GetALl(event.getEnchantBlock()));
            if(!event.isCancelled()) event.setCancelled(feedbackState);
        }
    }
}
