package com.pandapulsestudios.pulsecore.__Events__.Entity;

import com.pandapulsestudios.pulsecore.Block.PersistentDataAPI;
import com.pandapulsestudios.pulsecore.Enchantment.EnchantmentAPI;
import com.pandapulsestudios.pulsecore.Events.CustomEvent;
import com.pandapulsestudios.pulsecore.Items.ItemStackAPI;
import com.pandapulsestudios.pulsecore.Location.LocationAPI;
import com.pandapulsestudios.pulsecore.NBT.NBTAPI;
import com.pandapulsestudios.pulsecore.Player.PlayerAPI;
import com.pandapulsestudios.pulsecore.Player.Enums.PlayerAction;
import com.pandapulsestudios.pulsecore.PulseCoreMain;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityBreakDoorEvent;

@CustomEvent
public class EntityBreakDoor implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEvent(EntityBreakDoorEvent event){
        var isEntityPlayer = event.getEntity() instanceof Player;
        var inventoryItems = isEntityPlayer ? PlayerAPI.ReturnALlPlayerItems((Player) event.getEntity()) : PlayerAPI.ReturnALlPlayerItems(event.getEntity());

        for(var itemStack : inventoryItems.keySet()){
            if(itemStack.getItemMeta() == null) continue;
            for(var nbtListener : PulseCoreMain.nbtListeners){
                if(!event.isCancelled() && nbtListener.EntityBreakDoorEvent(event, itemStack, NBTAPI.GetAll(itemStack), event.getEntity())) event.setCancelled(true);
            }

            for(var pulseEnchantment : EnchantmentAPI.ReturnCustomEnchantmentOnItems(itemStack)){
                if(!event.isCancelled() && pulseEnchantment.EntityBreakDoorEvent(event, itemStack, inventoryItems.get(itemStack))) event.setCancelled(true);
            }

            var pulseItemStack = ItemStackAPI.ReturnPulseItem(itemStack);
            if(pulseItemStack != null) if(!event.isCancelled() && pulseItemStack.EntityBreakDoorEvent(event, itemStack, inventoryItems.get(itemStack))) event.setCancelled(true);
        }

        if(PulseCoreMain.handlePlayerActionEventsInHouse && isEntityPlayer){
            if(!event.isCancelled() && !PlayerAPI.CanDoAction(PlayerAction.BlockBreak, (Player) event.getEntity())) event.setCancelled(true);
        }

        var world = event.getEntity().getLocation().getWorld();
        if(PulseCoreMain.playerActionLock.containsKey(world)  && isEntityPlayer)  if(!event.isCancelled()) event.setCancelled(PulseCoreMain.playerActionLock.get(world).contains(PlayerAction.BlockBreak));

        for(var pulseLocation :  LocationAPI.ReturnAllPulseLocations(event.getEntity().getLocation(), true)){
            if(!event.isCancelled() && pulseLocation.EntityBreakDoorEvent(event, event.getBlock().getLocation())) event.setCancelled(true);
        }

        for(var pdListener : PulseCoreMain.persistentDataListeners){
            if(!event.isCancelled() && pdListener.EntityBreakDoorEvent(event, event.getBlock(), PersistentDataAPI.GetAll(event.getBlock()))) event.setCancelled(true);
        }

        for(var coreEvent : PulseCoreMain.pulseCoreEvents) if(!event.isCancelled() && coreEvent.EntityBreakDoorEvent(event)) event.setCancelled(true);
    }
}
