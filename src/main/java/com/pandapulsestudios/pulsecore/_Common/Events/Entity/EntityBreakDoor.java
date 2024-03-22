package com.pandapulsestudios.pulsecore._Common.Events.Entity;

import com.pandapulsestudios.pulsecore.Block.API.PersistentDataAPI;
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
import org.bukkit.event.entity.EntityBreakDoorEvent;

@PulseAutoRegister
public class EntityBreakDoor implements Listener {
    @EventHandler(priority = EventPriority.HIGH)
    public void OnBlockBreak(EntityBreakDoorEvent event){
        var isEntityPlayer = event.getEntity() instanceof Player;

        for(var pulseCoreEvent : PulseCore.customCoreEvents.values()){
            if(EventAPI.CanDoEvent(isEntityPlayer ? (Player) event.getEntity() : null, event.getBlock().getLocation(), pulseCoreEvent)){
                var state = pulseCoreEvent.EntityBreakDoorEvent(event);
                if(!event.isCancelled()) event.setCancelled(state);
            }
        }

        for(var pulseLocation :  LocationAPI.ReturnAllPulseLocations(event.getBlock().getLocation(), true)){
            var state = pulseLocation.EntityBreakDoorEvent(event, event.getBlock().getLocation());
            if(!event.isCancelled()) event.setCancelled(state);
        }

        if(PulseCore.handlePlayerAction && isEntityPlayer){
            var state = PlayerAPI.CanPlayerAction(PlayerAction.EntityBreakDoor, (Player) event.getEntity());
            if(!event.isCancelled()) event.setCancelled(!state);
        }

        var playerWorld = event.getEntity().getWorld();
        if(PulseCore.PlayerActionLock.containsKey(playerWorld) && isEntityPlayer){
            var state = PulseCore.PlayerActionLock.get(playerWorld).contains(PlayerAction.EntityBreakDoor);
            if(!event.isCancelled()) event.setCancelled(state);
        }

        for(var pulseLocation : LocationAPI.ReturnAllPulseLocations(event.getBlock().getLocation(), true)){
            var state = pulseLocation.EntityBreakDoorEvent(event, event.getBlock().getLocation());
            if(!event.isCancelled()) event.setCancelled(state);
        }

        var playerInventoryItems = InventoryAPI.ReturnALlItemsWithLocation(event.getEntity());
        for(var itemStack : playerInventoryItems.keySet()){
            if(itemStack.getItemMeta() == null) continue;

            for(var nbtListener : PulseCore.customNBTListener.values()){
                if(!NBTAPI.DoesItemStackContainNBTTags(itemStack, nbtListener.BlockHasTags())) continue;
                var state = nbtListener.EntityBreakDoorEvent(event, itemStack, NBTAPI.GetAll(itemStack), event.getEntity());
                if(!event.isCancelled()) event.setCancelled(state);
            }

            for(var pulseEnchantment : EnchantmentAPI.ReturnAllCustomEnchantmentsFromItem(itemStack)){
                var state = pulseEnchantment.EntityBreakDoorEvent(event, itemStack, playerInventoryItems.get(itemStack));
                if(!event.isCancelled()) event.setCancelled(state);
            }

            var pulseItemStack = ItemStackAPI.ReturnPulseItem(itemStack);
            if(pulseItemStack != null){
                var state = pulseItemStack.EntityBreakDoorEvent(event, itemStack, playerInventoryItems.get(itemStack));
                if(!event.isCancelled()) event.setCancelled(state);
            }
        }

        for(var persistentDataCallback : PulseCore.customPersistentDataCallbacks.values()){
            if(!PersistentDataAPI.CanBeCalled(persistentDataCallback, event.getBlock())) continue;
            var feedbackState = persistentDataCallback.EntityBreakDoorEvent(event, event.getBlock(), PersistentDataAPI.GetALl(event.getBlock()));
            if(!event.isCancelled()) event.setCancelled(feedbackState);
        }
    }
}
