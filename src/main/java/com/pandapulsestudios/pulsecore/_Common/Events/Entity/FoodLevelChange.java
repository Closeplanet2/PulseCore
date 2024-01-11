package com.pandapulsestudios.pulsecore._Common.Events.Entity;

import com.pandapulsestudios.pulsecore.Block.API.PersistentDataAPI;
import com.pandapulsestudios.pulsecore.Enchantment.EnchantmentAPI;
import com.pandapulsestudios.pulsecore.Events.CustomEvent;
import com.pandapulsestudios.pulsecore.Items.ItemStackAPI;
import com.pandapulsestudios.pulsecore.Location.LocationAPI;
import com.pandapulsestudios.pulsecore.NBT.NBTAPI;
import com.pandapulsestudios.pulsecore.Player.Enums.PlayerAction;
import com.pandapulsestudios.pulsecore.Player.PlayerAPI;
import com.pandapulsestudios.pulsecore.PulseCore;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;

@CustomEvent
public class FoodLevelChange implements Listener {
    @EventHandler(priority = EventPriority.HIGH)
    public void OnBlockBreak(FoodLevelChangeEvent event){
        var isEntityPlayer = event.getEntity() instanceof Player;

        for(var pulseCoreEvent : PulseCore.PulseCoreEvents){
            var state = pulseCoreEvent.FoodLevelChangeEvent(event);
            if(!event.isCancelled()) event.setCancelled(state);
        }

        for(var pulseLocation :  LocationAPI.ReturnAllPulseLocations(event.getEntity().getLocation(), true)){
            var state = pulseLocation.FoodLevelChangeEvent(event, event.getEntity().getLocation());
            if(!event.isCancelled()) event.setCancelled(state);
        }

        if(PulseCore.handlePlayerActionEventsInHouse && isEntityPlayer){
            var state = PlayerAPI.CanDoAction(PlayerAction.FoodLevelChange, (Player) event.getEntity());
            if(!event.isCancelled()) event.setCancelled(!state);
        }

        var playerWorld = event.getEntity().getWorld();
        if(PulseCore.playerActionLock.containsKey(playerWorld) && isEntityPlayer){
            var state = PulseCore.playerActionLock.get(playerWorld).contains(PlayerAction.FoodLevelChange);
            if(!event.isCancelled()) event.setCancelled(state);
        }

        for(var pulseLocation : LocationAPI.ReturnAllPulseLocations(event.getEntity().getLocation(), true)){
            var state = pulseLocation.FoodLevelChangeEvent(event, event.getEntity().getLocation());
            if(!event.isCancelled()) event.setCancelled(state);
        }

        var playerInventoryItems = PlayerAPI.ReturnALlPlayerItems(event.getEntity());
        for(var itemStack : playerInventoryItems.keySet()){
            if(itemStack.getItemMeta() == null) continue;

            for(var nbtListener : PulseCore.nbtListeners){
                var state = nbtListener.FoodLevelChangeEvent(event, itemStack, NBTAPI.GetAll(itemStack), event.getEntity());
                if(!event.isCancelled()) event.setCancelled(state);
            }

            for(var pulseEnchantment : EnchantmentAPI.ReturnCustomEnchantmentOnItem(itemStack)){
                var state = pulseEnchantment.FoodLevelChangeEvent(event, itemStack, playerInventoryItems.get(itemStack));
                if(!event.isCancelled()) event.setCancelled(state);
            }

            var pulseItemStack = ItemStackAPI.ReturnPulseItem(itemStack);
            if(pulseItemStack != null){
                var state = pulseItemStack.FoodLevelChangeEvent(event, itemStack, playerInventoryItems.get(itemStack));
                if(!event.isCancelled()) event.setCancelled(state);
            }
        }
    }
}

