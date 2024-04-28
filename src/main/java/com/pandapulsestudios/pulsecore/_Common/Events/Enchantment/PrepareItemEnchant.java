package com.pandapulsestudios.pulsecore._Common.Events.Enchantment;

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
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;

@PulseAutoRegister
public class PrepareItemEnchant implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEvent(PrepareItemEnchantEvent event){

        for(var pulseCoreEvent : PulseCore.PulseCoreEvents.values()){
            if(EventAPI.CanDoEvent(event.getEnchanter(), event.getEnchantBlock().getLocation(), pulseCoreEvent)){
                var state = pulseCoreEvent.PrepareItemEnchantEvent(event);
                if(!event.isCancelled()) event.setCancelled(state);
            }
        }

        for(var pulseLocation :  LocationAPI.ReturnAllPulseLocations(event.getEnchanter().getLocation(), true)){
            var state = pulseLocation.PrepareItemEnchantEvent(event, event.getEnchanter().getLocation());
            if(!event.isCancelled()) event.setCancelled(state);
        }

        var state1 = PlayerActionAPI.CanPlayerAction(PlayerAction.PrepareItemEnchant, event.getEnchanter().getUniqueId());
        if(!event.isCancelled()) event.setCancelled(!state1);

        var playerWorld = event.getEnchanter().getWorld();
        if(PulseCore.PlayerActionLock.containsKey(playerWorld)){
            var state = PulseCore.PlayerActionLock.get(playerWorld).contains(PlayerAction.PrepareItemEnchant);
            if(!event.isCancelled()) event.setCancelled(state);
        }

        for(var pulseLocation : LocationAPI.ReturnAllPulseLocations(event.getEnchanter().getLocation(), true)){
            var state = pulseLocation.PrepareItemEnchantEvent(event, event.getEnchantBlock().getLocation());
            if(!event.isCancelled()) event.setCancelled(state);
        }

        var playerInventoryItems = InventoryAPI.ReturnALlItemsWithLocation(event.getEnchanter().getInventory());
        for(var itemStack : playerInventoryItems.keySet()){
            if(itemStack == null || itemStack.getItemMeta() == null) continue;

            for(var nbtListener : PulseCore.PulseNBTListeners.values()){
                if(!NBTAPI.DoesItemStackContainNBTTags(itemStack, nbtListener.BlockHasTags())) continue;
                var state = nbtListener.PrepareItemEnchantEvent(event, itemStack, NBTAPI.GetAll(itemStack), event.getEnchanter());
                if(!event.isCancelled()) event.setCancelled(state);
            }

            for(var pulseEnchantment : EnchantmentAPI.ReturnAllCustomEnchantmentsFromItem(itemStack)){
                var state = pulseEnchantment.PrepareItemEnchantEvent(event, itemStack, playerInventoryItems.get(itemStack));
                if(!event.isCancelled()) event.setCancelled(state);
            }

            var pulseItemStack = ItemStackAPI.ReturnPulseItem(itemStack);
            if(pulseItemStack != null){
                var state = pulseItemStack.PrepareItemEnchantEvent(event, itemStack, playerInventoryItems.get(itemStack));
                if(!event.isCancelled()) event.setCancelled(state);
            }
        }

        for(var persistentDataCallback : PulseCore.PersistentDataCallbacks.values()){
            if(!PersistentDataAPI.CanBeCalled(persistentDataCallback, event.getEnchantBlock())) continue;
            var feedbackState = persistentDataCallback.PrepareItemEnchantEvent(event, event.getEnchantBlock(), PersistentDataAPI.GetALl(event.getEnchantBlock()));
            if(!event.isCancelled()) event.setCancelled(feedbackState);
        }
    }
}
