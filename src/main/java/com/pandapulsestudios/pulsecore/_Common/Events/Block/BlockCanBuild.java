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
import org.bukkit.event.block.BlockCanBuildEvent;

@CustomEvent
public class BlockCanBuild implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEvent(BlockCanBuildEvent event){
        for(var pulseCoreEvent : PulseCore.PulseCoreEvents){
            var state = pulseCoreEvent.BlockCanBuildEvent(event);
            if(!event.isBuildable()) event.setBuildable(state);
        }

        for(var pulseLocation :  LocationAPI.ReturnAllPulseLocations(event.getBlock().getLocation(), true)){
            var state = pulseLocation.BlockCanBuildEvent(event, event.getBlock().getLocation());
            if(!event.isBuildable()) event.setBuildable(state);
        }

        if(PulseCore.handlePlayerActionEventsInHouse){
            var state = PlayerAPI.CanDoAction(PlayerAction.BlockCanBuild, event.getPlayer());
            if(!event.isBuildable()) event.setBuildable(!state);
        }

        var playerWorld = event.getPlayer().getWorld();
        if(PulseCore.playerActionLock.containsKey(playerWorld)){
            var state = PulseCore.playerActionLock.get(playerWorld).contains(PlayerAction.BlockCanBuild);
            if(!event.isBuildable()) event.setBuildable(state);
        }

        for(var pulseLocation : LocationAPI.ReturnAllPulseLocations(event.getBlock().getLocation(), true)){
            var state = pulseLocation.BlockCanBuildEvent(event, event.getBlock().getLocation());
            if(!event.isBuildable()) event.setBuildable(state);
        }

        var playerInventoryItems = PlayerAPI.ReturnALlPlayerItems(event.getPlayer());
        for(var itemStack : playerInventoryItems.keySet()){
            if(itemStack.getItemMeta() == null) continue;

            for(var nbtListener : PulseCore.nbtListeners){
                var state = nbtListener.BlockCanBuildEvent(event, itemStack, NBTAPI.GetAll(itemStack), event.getPlayer());
                if(!event.isBuildable()) event.setBuildable(state);
            }

            for(var pulseEnchantment : EnchantmentAPI.ReturnCustomEnchantmentOnItem(itemStack)){
                var state = pulseEnchantment.BlockCanBuildEvent(event, itemStack, playerInventoryItems.get(itemStack));
                if(!event.isBuildable()) event.setBuildable(state);
            }

            var pulseItemStack = ItemStackAPI.ReturnPulseItem(itemStack);
            if(pulseItemStack != null){
                var state = pulseItemStack.BlockCanBuildEvent(event, itemStack, playerInventoryItems.get(itemStack));
                if(!event.isBuildable()) event.setBuildable(state);
            }
        }

        for(var persistentDataCallback : PulseCore.persistentDataCallbacks){
            if(!PersistentDataAPI.CanBeCalled(persistentDataCallback, event.getBlock())) continue;
            var feedbackState = persistentDataCallback.BlockCanBuildEvent(event, event.getBlock(), PersistentDataAPI.GetAll(event.getBlock()));
            if(!event.isBuildable()) event.setBuildable(feedbackState);
        }
    }
}
