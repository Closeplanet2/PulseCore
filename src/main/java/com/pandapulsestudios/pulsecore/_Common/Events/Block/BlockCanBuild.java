package com.pandapulsestudios.pulsecore._Common.Events.Block;

import com.pandapulsestudios.pulsecore.BlockAPI.API.PersistentDataAPI;
import com.pandapulsestudios.pulsecore.EnchantmentAPI.API.EnchantmentAPI;
import com.pandapulsestudios.pulsecore.EventsAPI.API.EventAPI;
import com.pandapulsestudios.pulsecore.InventoryAPI.API.InventoryAPI;
import com.pandapulsestudios.pulsecore.ItemsAPI.API.ItemStackAPI;
import com.pandapulsestudios.pulsecore.JavaAPI.Interface.PulseAutoRegister;
import com.pandapulsestudios.pulsecore.LocationAPI.API.LocationAPI;
import com.pandapulsestudios.pulsecore.NBTAPI.API.NBTAPI;
import com.pandapulsestudios.pulsecore.PlayerAPI.API.PlayerActionAPI;
import com.pandapulsestudios.pulsecore.PlayerAPI.Enum.PlayerAction;
import com.pandapulsestudios.pulsecore.PulseCore;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockCanBuildEvent;

@PulseAutoRegister
public class BlockCanBuild implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEvent(BlockCanBuildEvent event){
        for(var pulseCoreEvent : PulseCore.PulseCoreEvents.values()){
            if(EventAPI.CanDoEvent(event.getPlayer(), event.getBlock().getLocation(), pulseCoreEvent)){
                var state = pulseCoreEvent.BlockCanBuildEvent(event);
                if(!event.isBuildable()) event.setBuildable(state);
            }
        }

        for(var pulseLocation :  LocationAPI.ReturnAllPulseLocations(event.getBlock().getLocation(), true)){
            var state = pulseLocation.BlockCanBuildEvent(event, event.getBlock().getLocation());
            if(!event.isBuildable()) event.setBuildable(state);
        }

        var state1 = PlayerActionAPI.CanPlayerAction(PlayerAction.BlockCanBuild, event.getPlayer().getUniqueId());
        if(!event.isBuildable()) event.setBuildable(!state1);

        var playerWorld = event.getPlayer().getWorld();
        if(PulseCore.PlayerActionLock.containsKey(playerWorld)){
            var state = PulseCore.PlayerActionLock.get(playerWorld).contains(PlayerAction.BlockCanBuild);
            if(!event.isBuildable()) event.setBuildable(state);
        }

        for(var pulseLocation : LocationAPI.ReturnAllPulseLocations(event.getBlock().getLocation(), true)){
            var state = pulseLocation.BlockCanBuildEvent(event, event.getBlock().getLocation());
            if(!event.isBuildable()) event.setBuildable(state);
        }

        var playerInventoryItems = InventoryAPI.ReturnALlItemsWithLocation(event.getPlayer().getInventory());
        for(var itemStack : playerInventoryItems.keySet()){
            if(itemStack == null || itemStack.getItemMeta() == null) continue;

            for(var nbtListener : PulseCore.PulseNBTListeners.values()){
                if(!NBTAPI.DoesItemStackContainNBTTags(itemStack, nbtListener.BlockHasTags())) continue;
                var state = nbtListener.BlockCanBuildEvent(event, itemStack, NBTAPI.GetAll(itemStack), event.getPlayer());
                if(!event.isBuildable()) event.setBuildable(state);
            }

            for(var pulseEnchantment : EnchantmentAPI.ReturnAllCustomEnchantmentsFromItem(itemStack)){
                var state = pulseEnchantment.BlockCanBuildEvent(event, itemStack, playerInventoryItems.get(itemStack));
                if(!event.isBuildable()) event.setBuildable(state);
            }

            var pulseItemStack = ItemStackAPI.ReturnPulseItem(itemStack);
            if(pulseItemStack != null){
                var state = pulseItemStack.BlockCanBuildEvent(event, itemStack, playerInventoryItems.get(itemStack));
                if(!event.isBuildable()) event.setBuildable(state);
            }
        }

        for(var persistentDataCallback : PulseCore.PersistentDataCallbacks.values()){
            if(!PersistentDataAPI.CanBeCalled(persistentDataCallback, event.getBlock())) continue;
            var feedbackState = persistentDataCallback.BlockCanBuildEvent(event, event.getBlock(), PersistentDataAPI.GetALl(event.getBlock()));
            if(!event.isBuildable()) event.setBuildable(feedbackState);
        }
    }
}
