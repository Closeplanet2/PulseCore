package com.pandapulsestudios.pulsecore._Common.Events.Player;

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
import org.bukkit.event.player.PlayerGameModeChangeEvent;

@PulseAutoRegister
public class PlayerGameModeChange implements Listener {
    @EventHandler(priority = EventPriority.HIGH)
    public void OnBlockBreak(PlayerGameModeChangeEvent event){
        for(var pulseCoreEvent : PulseCore.PulseCoreEvents.values()){
            if(EventAPI.CanDoEvent(event.getPlayer(), event.getPlayer().getLocation(), pulseCoreEvent)){
                var state = pulseCoreEvent.PlayerGameModeChangeEvent(event);
                if(!event.isCancelled()) event.setCancelled(state);
            }
        }

        for(var pulseLocation :  LocationAPI.ReturnAllPulseLocations(event.getPlayer().getLocation(), true)){
            var state = pulseLocation.PlayerGameModeChangeEvent(event, event.getPlayer().getLocation());
            if(!event.isCancelled()) event.setCancelled(state);
        }

        var state1 = PlayerActionAPI.CanPlayerAction(PlayerAction.PlayerGameModeChange, event.getPlayer().getUniqueId());
        if(!event.isCancelled()) event.setCancelled(!state1);

        var playerWorld = event.getPlayer().getWorld();
        if(PulseCore.PlayerActionLock.containsKey(playerWorld)){
            var state = PulseCore.PlayerActionLock.get(playerWorld).contains(PlayerAction.PlayerGameModeChange);
            if(!event.isCancelled()) event.setCancelled(state);
        }

        for(var pulseLocation : LocationAPI.ReturnAllPulseLocations(event.getPlayer().getLocation(), true)){
            var state = pulseLocation.PlayerGameModeChangeEvent(event, event.getPlayer().getLocation());
            if(!event.isCancelled()) event.setCancelled(state);
        }

        var playerInventoryItems = InventoryAPI.ReturnALlItemsWithLocation(event.getPlayer());
        for(var itemStack : playerInventoryItems.keySet()){
            if(itemStack == null || itemStack.getItemMeta() == null) continue;

            for(var nbtListener : PulseCore.PulseNBTListeners.values()){
                if(!NBTAPI.DoesItemStackContainNBTTags(itemStack, nbtListener.BlockHasTags())) continue;
                var state = nbtListener.PlayerGameModeChangeEvent(event, itemStack, NBTAPI.GetAll(itemStack), event.getPlayer());
                if(!event.isCancelled()) event.setCancelled(state);
            }

            for(var pulseEnchantment : EnchantmentAPI.ReturnAllCustomEnchantmentsFromItem(itemStack)){
                var state = pulseEnchantment.PlayerGameModeChangeEvent(event, itemStack, playerInventoryItems.get(itemStack));
                if(!event.isCancelled()) event.setCancelled(state);
            }

            var pulseItemStack = ItemStackAPI.ReturnPulseItem(itemStack);
            if(pulseItemStack != null){
                var state = pulseItemStack.PlayerGameModeChangeEvent(event, itemStack, playerInventoryItems.get(itemStack));
                if(!event.isCancelled()) event.setCancelled(state);
            }
        }
    }
}
