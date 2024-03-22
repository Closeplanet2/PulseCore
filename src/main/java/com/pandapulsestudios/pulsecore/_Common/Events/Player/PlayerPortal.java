package com.pandapulsestudios.pulsecore._Common.Events.Player;

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
import org.bukkit.event.player.PlayerPortalEvent;

@PulseAutoRegister
public class PlayerPortal implements Listener {
    @EventHandler(priority = EventPriority.HIGH)
    public void OnBlockBreak(PlayerPortalEvent event){
        for(var pulseCoreEvent : PulseCore.customCoreEvents.values()){
            if(EventAPI.CanDoEvent(event.getPlayer(), event.getPlayer().getLocation(), pulseCoreEvent)){
                var state = pulseCoreEvent.PlayerPortalEvent(event);
                if(!event.isCancelled()) event.setCancelled(state);
            }
        }

        for(var pulseLocation :  LocationAPI.ReturnAllPulseLocations(event.getPlayer().getLocation(), true)){
            var state = pulseLocation.PlayerPortalEvent(event, event.getPlayer().getLocation());
            if(!event.isCancelled()) event.setCancelled(state);
        }

        if(PulseCore.handlePlayerAction){
            var state = PlayerAPI.CanPlayerAction(PlayerAction.PlayerPortal, event.getPlayer());
            if(!event.isCancelled()) event.setCancelled(!state);
        }

        var playerWorld = event.getPlayer().getWorld();
        if(PulseCore.PlayerActionLock.containsKey(playerWorld)){
            var state = PulseCore.PlayerActionLock.get(playerWorld).contains(PlayerAction.PlayerPortal);
            if(!event.isCancelled()) event.setCancelled(state);
        }

        for(var pulseLocation : LocationAPI.ReturnAllPulseLocations(event.getPlayer().getLocation(), true)){
            var state = pulseLocation.PlayerPortalEvent(event, event.getPlayer().getLocation());
            if(!event.isCancelled()) event.setCancelled(state);
        }

        var playerInventoryItems = InventoryAPI.ReturnALlItemsWithLocation(event.getPlayer());
        for(var itemStack : playerInventoryItems.keySet()){
            if(itemStack.getItemMeta() == null) continue;

            for(var nbtListener : PulseCore.customNBTListener.values()){
                if(!NBTAPI.DoesItemStackContainNBTTags(itemStack, nbtListener.BlockHasTags())) continue;
                var state = nbtListener.PlayerPortalEvent(event, itemStack, NBTAPI.GetAll(itemStack), event.getPlayer());
                if(!event.isCancelled()) event.setCancelled(state);
            }

            for(var pulseEnchantment : EnchantmentAPI.ReturnAllCustomEnchantmentsFromItem(itemStack)){
                var state = pulseEnchantment.PlayerPortalEvent(event, itemStack, playerInventoryItems.get(itemStack));
                if(!event.isCancelled()) event.setCancelled(state);
            }

            var pulseItemStack = ItemStackAPI.ReturnPulseItem(itemStack);
            if(pulseItemStack != null){
                var state = pulseItemStack.PlayerPortalEvent(event, itemStack, playerInventoryItems.get(itemStack));
                if(!event.isCancelled()) event.setCancelled(state);
            }
        }
    }
}
