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
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;

@CustomEvent
public class PrepareItemEnchant implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEvent(PrepareItemEnchantEvent event){
        for(var pulseCoreEvent : PulseCore.PulseCoreEvents){
            if(pulseCoreEvent.CanDoEvent(event.getEnchanter(), event.getEnchantBlock().getLocation())){
                var state = pulseCoreEvent.pulseCoreEvents.PrepareItemEnchantEvent(event);
                if(!event.isCancelled()) event.setCancelled(state);
            }
        }

        for(var pulseLocation :  LocationAPI.ReturnAllPulseLocations(event.getEnchanter().getLocation(), true)){
            var state = pulseLocation.PrepareItemEnchantEvent(event, event.getEnchanter().getLocation());
            if(!event.isCancelled()) event.setCancelled(state);
        }

        if(PulseCore.HandlePlayerActionEventsInHouse){
            var state = PlayerAPI.CanDoAction(PlayerAction.PrepareItemEnchant, event.getEnchanter());
            if(!event.isCancelled()) event.setCancelled(!state);
        }

        var playerWorld = event.getEnchanter().getWorld();
        if(PulseCore.PlayerActionLock.containsKey(playerWorld)){
            var state = PulseCore.PlayerActionLock.get(playerWorld).contains(PlayerAction.PrepareItemEnchant);
            if(!event.isCancelled()) event.setCancelled(state);
        }

        for(var pulseLocation : LocationAPI.ReturnAllPulseLocations(event.getEnchanter().getLocation(), true)){
            var state = pulseLocation.PrepareItemEnchantEvent(event, event.getEnchantBlock().getLocation());
            if(!event.isCancelled()) event.setCancelled(state);
        }

        var playerInventoryItems = PlayerAPI.ReturnALlPlayerItems(event.getEnchanter());
        for(var itemStack : playerInventoryItems.keySet()){
            if(itemStack.getItemMeta() == null) continue;

            for(var nbtListener : PulseCore.NbtListeners){
                var state = nbtListener.PrepareItemEnchantEvent(event, itemStack, NBTAPI.GetAll(itemStack), event.getEnchanter());
                if(!event.isCancelled()) event.setCancelled(state);
            }

            for(var pulseEnchantment : EnchantmentAPI.ReturnCustomEnchantmentOnItem(itemStack)){
                var state = pulseEnchantment.PrepareItemEnchantEvent(event, itemStack, playerInventoryItems.get(itemStack));
                if(!event.isCancelled()) event.setCancelled(state);
            }

            var pulseItemStack = ItemStackAPI.ReturnPulseItem(itemStack);
            if(pulseItemStack != null){
                var state = pulseItemStack.PrepareItemEnchantEvent(event, itemStack, playerInventoryItems.get(itemStack));
                if(!event.isCancelled()) event.setCancelled(state);
            }
        }

        for(var persistentDataCallback : PulseCore.PersistentDataCallbacks){
            if(!PersistentDataAPI.CanBeCalled(persistentDataCallback, event.getEnchantBlock())) continue;
            var feedbackState = persistentDataCallback.PrepareItemEnchantEvent(event, event.getEnchantBlock(), PersistentDataAPI.GetAll(event.getEnchantBlock()));
            if(!event.isCancelled()) event.setCancelled(feedbackState);
        }
    }
}
