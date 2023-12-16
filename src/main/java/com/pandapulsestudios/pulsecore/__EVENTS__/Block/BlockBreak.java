package com.pandapulsestudios.pulsecore.__Events__.Block;

import com.pandapulsestudios.pulsecore.Enchantment.EnchantmentAPI;
import com.pandapulsestudios.pulsecore.Events.CustomEvent;
import com.pandapulsestudios.pulsecore.Items.ItemStackAPI;
import com.pandapulsestudios.pulsecore.Location.LocationAPI;
import com.pandapulsestudios.pulsecore.Player.PlayerAPI;
import com.pandapulsestudios.pulsecore.Player.Enums.PlayerAction;
import com.pandapulsestudios.pulsecore.PulseCoreMain;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

@CustomEvent
public class BlockBreak implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockBreak(BlockBreakEvent event){
        var inventoryItems = PlayerAPI.ReturnALlPlayerItems(event.getPlayer());
        for(var itemStack : inventoryItems.keySet()){
            for(var pulseEnchantment : EnchantmentAPI.ReturnCustomEnchantmentOnItems(itemStack)){
                var state = pulseEnchantment.BlockBreakEvent(event, itemStack, inventoryItems.get(itemStack));
                if(!event.isCancelled()) event.setCancelled(state);
            }
        }

        if(PulseCoreMain.handlePlayerActionEventsInHouse){
            var state = PlayerAPI.CanDoAction(PlayerAction.BlockBreak, event.getPlayer());
            if(!event.isCancelled()) event.setCancelled(state);
        }

        var world = event.getPlayer().getLocation().getWorld();
        if(PulseCoreMain.playerActionLock.containsKey(world)){
            if(!event.isCancelled()) event.setCancelled(PulseCoreMain.playerActionLock.get(world).contains(PlayerAction.BlockBreak));
        }

        for(var itemStack : inventoryItems.keySet()){
            var pulseItemStack = ItemStackAPI.ReturnPulseItem(itemStack);
            if(pulseItemStack == null) continue;
            var state = pulseItemStack.BlockBreakEvent(event, itemStack, inventoryItems.get(itemStack));
            if(!event.isCancelled()) event.setCancelled(state);
        }

        var eventLocation = event.getBlock().getLocation();
        for(var pulseLocation :  LocationAPI.ReturnAllPulseLocations(eventLocation, true)){
            var state = pulseLocation.BlockBreakEvent(event, eventLocation);
            if(!event.isCancelled()) event.setCancelled(state);
        }
    }
}
