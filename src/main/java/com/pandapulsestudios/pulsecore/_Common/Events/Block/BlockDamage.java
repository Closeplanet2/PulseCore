package com.pandapulsestudios.pulsecore._Common.Events.Block;

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
import org.bukkit.event.block.BlockDamageEvent;

@CustomEvent
public class BlockDamage implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEvent(BlockDamageEvent event){
        for(var pulseCoreEvent : PulseCore.PulseCoreEvents){
            if(pulseCoreEvent.CanDoEvent(event.getPlayer(), event.getBlock().getLocation())){
                var state = pulseCoreEvent.pulseCoreEvents.BlockDamageEvent(event);
                if(!event.isCancelled()) event.setCancelled(state);
            }
        }

        for(var pulseLocation :  LocationAPI.ReturnAllPulseLocations(event.getBlock().getLocation(), true)){
            var state = pulseLocation.BlockDamageEvent(event, event.getBlock().getLocation());
            if(!event.isCancelled()) event.setCancelled(state);
        }

        if(PulseCore.HandlePlayerActionEventsInHouse){
            var state = PlayerAPI.CanDoAction(PlayerAction.BlockDamage, event.getPlayer());
            if(!event.isCancelled()) event.setCancelled(!state);
        }

        var playerWorld = event.getPlayer().getWorld();
        if(PulseCore.PlayerActionLock.containsKey(playerWorld)){
            var state = PulseCore.PlayerActionLock.get(playerWorld).contains(PlayerAction.BlockDamage);
            if(!event.isCancelled()) event.setCancelled(state);
        }

        for(var pulseLocation : LocationAPI.ReturnAllPulseLocations(event.getBlock().getLocation(), true)){
            var state = pulseLocation.BlockDamageEvent(event, event.getBlock().getLocation());
            if(!event.isCancelled()) event.setCancelled(state);
        }

        var playerInventoryItems = PlayerAPI.ReturnALlPlayerItems(event.getPlayer());
        for(var itemStack : playerInventoryItems.keySet()){
            if(itemStack.getItemMeta() == null) continue;

            for(var nbtListener : PulseCore.NbtListeners){
                var state = nbtListener.BlockDamageEvent(event, itemStack, NBTAPI.GetAll(itemStack), event.getPlayer());
                if(!event.isCancelled()) event.setCancelled(state);
            }

            for(var pulseEnchantment : EnchantmentAPI.ReturnCustomEnchantmentOnItem(itemStack)){
                var state = pulseEnchantment.BlockDamageEvent(event, itemStack, playerInventoryItems.get(itemStack));
                if(!event.isCancelled()) event.setCancelled(state);
            }

            var pulseItemStack = ItemStackAPI.ReturnPulseItem(itemStack);
            if(pulseItemStack != null){
                var state = pulseItemStack.BlockDamageEvent(event, itemStack, playerInventoryItems.get(itemStack));
                if(!event.isCancelled()) event.setCancelled(state);
            }
        }

        for(var persistentDataCallback : PulseCore.PersistentDataCallbacks){
            if(!PersistentDataAPI.CanBeCalled(persistentDataCallback, event.getBlock())) continue;
            var feedbackState = persistentDataCallback.BlockDamageEvent(event, event.getBlock(), PersistentDataAPI.GetAll(event.getBlock()));
            if(!event.isCancelled()) event.setCancelled(feedbackState);
        }
    }
}
