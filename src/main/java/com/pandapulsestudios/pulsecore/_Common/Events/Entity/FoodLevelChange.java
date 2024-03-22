package com.pandapulsestudios.pulsecore._Common.Events.Entity;

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
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

@PulseAutoRegister
public class FoodLevelChange implements Listener {
    @EventHandler(priority = EventPriority.HIGH)
    public void OnBlockBreak(FoodLevelChangeEvent event){
        var isEntityPlayer = event.getEntity() instanceof Player;

        for(var pulseCoreEvent : PulseCore.customCoreEvents.values()){
            if(EventAPI.CanDoEvent(isEntityPlayer ? (Player) event.getEntity() : null, event.getEntity().getLocation(), pulseCoreEvent)){
                var state = pulseCoreEvent.FoodLevelChangeEvent(event);
                if(!event.isCancelled()) event.setCancelled(state);
            }
        }

        for(var pulseLocation :  LocationAPI.ReturnAllPulseLocations(event.getEntity().getLocation(), true)){
            var state = pulseLocation.FoodLevelChangeEvent(event, event.getEntity().getLocation());
            if(!event.isCancelled()) event.setCancelled(state);
        }

        if(PulseCore.handlePlayerAction && isEntityPlayer){
            var state = PlayerAPI.CanPlayerAction(PlayerAction.FoodLevelChange, (Player) event.getEntity());
            if(!event.isCancelled()) event.setCancelled(!state);
        }

        var playerWorld = event.getEntity().getWorld();
        if(PulseCore.PlayerActionLock.containsKey(playerWorld) && isEntityPlayer){
            var state = PulseCore.PlayerActionLock.get(playerWorld).contains(PlayerAction.FoodLevelChange);
            if(!event.isCancelled()) event.setCancelled(state);
        }

        for(var pulseLocation : LocationAPI.ReturnAllPulseLocations(event.getEntity().getLocation(), true)){
            var state = pulseLocation.FoodLevelChangeEvent(event, event.getEntity().getLocation());
            if(!event.isCancelled()) event.setCancelled(state);
        }

        var playerInventoryItems = InventoryAPI.ReturnALlItemsWithLocation(event.getEntity());
        for(var itemStack : playerInventoryItems.keySet()){
            if(itemStack.getItemMeta() == null) continue;

            for(var nbtListener : PulseCore.customNBTListener.values()){
                if(!NBTAPI.DoesItemStackContainNBTTags(itemStack, nbtListener.BlockHasTags())) continue;
                var state = nbtListener.FoodLevelChangeEvent(event, itemStack, NBTAPI.GetAll(itemStack), event.getEntity());
                if(!event.isCancelled()) event.setCancelled(state);
            }

            for(var pulseEnchantment : EnchantmentAPI.ReturnAllCustomEnchantmentsFromItem(itemStack)){
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

