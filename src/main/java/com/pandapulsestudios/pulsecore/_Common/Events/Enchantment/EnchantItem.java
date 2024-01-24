package com.pandapulsestudios.pulsecore._Common.Events.Enchantment;

import com.pandapulsestudios.pulsecore.Block.API.PersistentDataAPI;
import com.pandapulsestudios.pulsecore.Enchantment.EnchantmentAPI;
import com.pandapulsestudios.pulsecore.Events.CustomEvent;
import com.pandapulsestudios.pulsecore.Items.ItemStackAPI;
import com.pandapulsestudios.pulsecore.Location.LocationAPI;
import com.pandapulsestudios.pulsecore.NBT.NBTAPI;
import com.pandapulsestudios.pulsecore.Player.Enums.PlayerAction;
import com.pandapulsestudios.pulsecore.Player.PlayerAPI;
import com.pandapulsestudios.pulsecore.PulseCore;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;

@CustomEvent
public class EnchantItem implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEvent(EnchantItemEvent event){
        for(var pulseCoreEvent : PulseCore.PulseCoreEvents){
            if(pulseCoreEvent.CanDoEvent(event.getEnchanter(), event.getEnchantBlock().getLocation())){
                var state = pulseCoreEvent.pulseCoreEvents.EnchantItemEvent(event);
                if(!event.isCancelled()) event.setCancelled(state);
            }
        }

        for(var pulseLocation :  LocationAPI.ReturnAllPulseLocations(event.getEnchanter().getLocation(), true)){
            var state = pulseLocation.EnchantItemEvent(event, event.getEnchanter().getLocation());
            if(!event.isCancelled()) event.setCancelled(state);
        }

        if(PulseCore.handlePlayerActionEventsInHouse){
            var state = PlayerAPI.CanDoAction(PlayerAction.EnchantItem, event.getEnchanter());
            if(!event.isCancelled()) event.setCancelled(!state);
        }

        var playerWorld = event.getEnchanter().getWorld();
        if(PulseCore.playerActionLock.containsKey(playerWorld)){
            var state = PulseCore.playerActionLock.get(playerWorld).contains(PlayerAction.EnchantItem);
            if(!event.isCancelled()) event.setCancelled(state);
        }

        for(var pulseLocation : LocationAPI.ReturnAllPulseLocations(event.getEnchanter().getLocation(), true)){
            var state = pulseLocation.EnchantItemEvent(event, event.getEnchantBlock().getLocation());
            if(!event.isCancelled()) event.setCancelled(state);
        }

        var playerInventoryItems = PlayerAPI.ReturnALlPlayerItems(event.getEnchanter());
        for(var itemStack : playerInventoryItems.keySet()){
            if(itemStack.getItemMeta() == null) continue;

            for(var nbtListener : PulseCore.nbtListeners){
                var state = nbtListener.EnchantItemEvent(event, itemStack, NBTAPI.GetAll(itemStack), event.getEnchanter());
                if(!event.isCancelled()) event.setCancelled(state);
            }

            for(var pulseEnchantment : EnchantmentAPI.ReturnCustomEnchantmentOnItem(itemStack)){
                var state = pulseEnchantment.EnchantItemEvent(event, itemStack, playerInventoryItems.get(itemStack));
                if(!event.isCancelled()) event.setCancelled(state);
            }

            var pulseItemStack = ItemStackAPI.ReturnPulseItem(itemStack);
            if(pulseItemStack != null){
                var state = pulseItemStack.EnchantItemEvent(event, itemStack, playerInventoryItems.get(itemStack));
                if(!event.isCancelled()) event.setCancelled(state);
            }
        }

        for(var persistentDataCallback : PulseCore.persistentDataCallbacks){
            if(!PersistentDataAPI.CanBeCalled(persistentDataCallback, event.getEnchantBlock())) continue;
            var feedbackState = persistentDataCallback.EnchantItemEvent(event, event.getEnchantBlock(), PersistentDataAPI.GetAll(event.getEnchantBlock()));
            if(!event.isCancelled()) event.setCancelled(feedbackState);
        }
    }
}
