package com.pandapulsestudios.pulsecore.__Events__.Block;

import com.pandapulsestudios.pulsecore.Block.PersistentDataAPI;
import com.pandapulsestudios.pulsecore.Enchantment.EnchantmentAPI;
import com.pandapulsestudios.pulsecore.Events.CustomEvent;
import com.pandapulsestudios.pulsecore.Items.ItemStackAPI;
import com.pandapulsestudios.pulsecore.Location.LocationAPI;
import com.pandapulsestudios.pulsecore.NBT.NBTAPI;
import com.pandapulsestudios.pulsecore.Player.PlayerAPI;
import com.pandapulsestudios.pulsecore.Player.Enums.PlayerAction;
import com.pandapulsestudios.pulsecore.PulseCoreMain;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

@CustomEvent
public class BlockPlace implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEvent(BlockPlaceEvent event){
        var inventoryItems = PlayerAPI.ReturnALlPlayerItems(event.getPlayer());

        for(var itemStack : inventoryItems.keySet()){
            if(itemStack.getItemMeta() == null) continue;
            for(var nbtListener : PulseCoreMain.nbtListeners){
                if(!event.isCancelled() && nbtListener.BlockPlaceEvent(event, itemStack, NBTAPI.GetAll(itemStack), event.getPlayer())) event.setCancelled(true);
            }

            for(var pulseEnchantment : EnchantmentAPI.ReturnCustomEnchantmentOnItems(itemStack)){
                if(!event.isCancelled() && pulseEnchantment.BlockPlaceEvent(event, itemStack, inventoryItems.get(itemStack))) event.setCancelled(true);
            }

            var pulseItemStack = ItemStackAPI.ReturnPulseItem(itemStack);
            if(pulseItemStack != null) if(!event.isCancelled() && pulseItemStack.BlockPlaceEvent(event, itemStack, inventoryItems.get(itemStack))) event.setCancelled(true);
        }

        if(PulseCoreMain.handlePlayerActionEventsInHouse){
            if(!event.isCancelled() && !PlayerAPI.CanDoAction(PlayerAction.BlockPlace, event.getPlayer())) event.setCancelled(true);
        }

        var world = event.getPlayer().getLocation().getWorld();
        if(PulseCoreMain.playerActionLock.containsKey(world)){
            if(!event.isCancelled()) event.setCancelled(PulseCoreMain.playerActionLock.get(world).contains(PlayerAction.BlockPlace));
        }

        for(var pulseLocation :  LocationAPI.ReturnAllPulseLocations(event.getBlock().getLocation(), true)){
            if(!event.isCancelled() && pulseLocation.BlockPlaceEvent(event, event.getBlock().getLocation())) event.setCancelled(true);
        }

        for(var pdListener : PulseCoreMain.persistentDataListeners){
            if(!event.isCancelled() && pdListener.BlockPlaceEvent(event, event.getBlock(), PersistentDataAPI.GetAll(event.getBlock()))) event.setCancelled(true);
        }

        for(var coreEvent : PulseCoreMain.pulseCoreEvents) if(!event.isCancelled() && coreEvent.BlockPlaceEvent(event)) event.setCancelled(true);
    }
}
