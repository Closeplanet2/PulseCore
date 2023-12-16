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
import org.bukkit.event.block.BlockCanBuildEvent;

@CustomEvent
public class BlockCanBuild implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEvent(BlockCanBuildEvent event){
        if(event.getPlayer() == null) return;

        var inventoryItems = PlayerAPI.ReturnALlPlayerItems(event.getPlayer());
        for(var itemStack : inventoryItems.keySet()){
            for(var pulseEnchantment : EnchantmentAPI.ReturnCustomEnchantmentOnItems(itemStack)){
                var state = pulseEnchantment.BlockCanBuildEvent(event, itemStack, inventoryItems.get(itemStack));
                if(!event.isBuildable()) event.setBuildable(state);
            }
        }

        if(PulseCoreMain.handlePlayerActionEventsInHouse){
            var state = PlayerAPI.CanDoAction(PlayerAction.BlockCanBuild, event.getPlayer());
            if(!event.isBuildable()) event.setBuildable(state);
        }

        var world = event.getPlayer().getLocation().getWorld();
        if(PulseCoreMain.playerActionLock.containsKey(world)){
            if(!event.isBuildable()) event.setBuildable(PulseCoreMain.playerActionLock.get(world).contains(PlayerAction.BlockCanBuild));
        }

        for(var itemStack : inventoryItems.keySet()){
            var pulseItemStack = ItemStackAPI.ReturnPulseItem(itemStack);
            if(pulseItemStack == null) continue;
            var state = pulseItemStack.BlockCanBuildEvent(event, itemStack, inventoryItems.get(itemStack));
            if(!event.isBuildable()) event.setBuildable(state);
        }

        var eventLocation = event.getBlock().getLocation();
        for(var pulseLocation :  LocationAPI.ReturnAllPulseLocations(eventLocation, true)){
            var state = pulseLocation.BlockCanBuildEvent(event, eventLocation);
            if(!event.isBuildable()) event.setBuildable(state);
        }
    }
}
