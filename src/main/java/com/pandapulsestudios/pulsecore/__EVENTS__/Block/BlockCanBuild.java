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
import org.bukkit.event.block.BlockCanBuildEvent;

@CustomEvent
public class BlockCanBuild implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEvent(BlockCanBuildEvent event){
        if(event.getPlayer() == null) return;
        var inventoryItems = PlayerAPI.ReturnALlPlayerItems(event.getPlayer());

        for(var itemStack : inventoryItems.keySet()){
            if(itemStack.getItemMeta() == null) continue;
            for(var nbtListener : PulseCoreMain.nbtListeners){
                if(!event.isBuildable() && nbtListener.BlockCanBuildEvent(event, itemStack, NBTAPI.GetAll(itemStack), event.getPlayer())) event.setBuildable(true);
            }

            for(var pulseEnchantment : EnchantmentAPI.ReturnCustomEnchantmentOnItems(itemStack)){
                if(!event.isBuildable() && pulseEnchantment.BlockCanBuildEvent(event, itemStack, inventoryItems.get(itemStack))) event.setBuildable(true);
            }

            var pulseItemStack = ItemStackAPI.ReturnPulseItem(itemStack);
            if(pulseItemStack != null) if(!event.isBuildable() && pulseItemStack.BlockCanBuildEvent(event, itemStack, inventoryItems.get(itemStack))) event.setBuildable(true);
        }

        if(PulseCoreMain.handlePlayerActionEventsInHouse){
            if(!event.isBuildable() && !PlayerAPI.CanDoAction(PlayerAction.BlockCanBuild, event.getPlayer())) event.setBuildable(true);
        }

        var world = event.getPlayer().getLocation().getWorld();
        if(PulseCoreMain.playerActionLock.containsKey(world)){
            if(!event.isBuildable()) event.setBuildable(PulseCoreMain.playerActionLock.get(world).contains(PlayerAction.BlockCanBuild));
        }

        for(var pulseLocation :  LocationAPI.ReturnAllPulseLocations(event.getBlock().getLocation(), true)){
            if(!event.isBuildable() && pulseLocation.BlockCanBuildEvent(event, event.getBlock().getLocation())) event.setBuildable(true);
        }

        for(var pdListener : PulseCoreMain.persistentDataListeners){
            if(!event.isBuildable() && pdListener.BlockCanBuildEvent(event, event.getBlock(), PersistentDataAPI.GetAll(event.getBlock()))) event.setBuildable(true);
        }

        for(var coreEvent : PulseCoreMain.pulseCoreEvents) if(!event.isBuildable() && coreEvent.BlockCanBuildEvent(event)) event.setBuildable(true);
    }
}
