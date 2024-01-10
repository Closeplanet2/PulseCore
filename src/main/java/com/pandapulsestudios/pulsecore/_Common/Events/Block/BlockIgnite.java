package com.pandapulsestudios.pulsecore._Common.Events.Block;

import com.pandapulsestudios.pulsecore.Block.API.PersistentDataAPI;
import com.pandapulsestudios.pulsecore.Events.CustomEvent;
import com.pandapulsestudios.pulsecore.Items.ItemStackAPI;
import com.pandapulsestudios.pulsecore.Location.LocationAPI;
import com.pandapulsestudios.pulsecore.Player.PlayerAPI;
import com.pandapulsestudios.pulsecore.PulseCore;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockIgniteEvent;

@CustomEvent
public class BlockIgnite implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEvent(BlockIgniteEvent event){
        for(var pulseCoreEvent : PulseCore.PulseCoreEvents){
            var state = pulseCoreEvent.BlockIgniteEvent(event);
            if(!event.isCancelled()) event.setCancelled(state);
        }

        for(var pulseLocation :  LocationAPI.ReturnAllPulseLocations(event.getBlock().getLocation(), true)){
            var state = pulseLocation.BlockIgniteEvent(event, event.getBlock().getLocation());
            if(!event.isCancelled()) event.setCancelled(state);
        }

        if(event.getPlayer() != null){
            var playerInventoryItems = PlayerAPI.ReturnALlPlayerItems(event.getPlayer());
            for(var itemStack : playerInventoryItems.keySet()){
                if(itemStack.getItemMeta() == null) continue;

                var pulseItemStack = ItemStackAPI.ReturnPulseItem(itemStack);
                if(pulseItemStack != null){
                    var state = pulseItemStack.BlockIgniteEvent(event, itemStack, playerInventoryItems.get(itemStack));
                    if(!event.isCancelled()) event.setCancelled(state);
                }
            }
        }


        for(var persistentDataCallback : PulseCore.persistentDataCallbacks){
            if(!PersistentDataAPI.CanBeCalled(persistentDataCallback, event.getBlock())) continue;
            var feedbackState = persistentDataCallback.BlockIgniteEvent(event, event.getBlock(), PersistentDataAPI.GetAll(event.getBlock()));
            if(!event.isCancelled()) event.setCancelled(feedbackState);
        }
    }
}
