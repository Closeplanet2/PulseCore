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
import org.bukkit.event.entity.EntityTeleportEvent;

@CustomEvent
public class EntityTeleport implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEvent(EntityTeleportEvent event){
        if(!(event.getEntity() instanceof LivingEntity livingEntity)) return;
        var isEntityPlayer = livingEntity instanceof Player;
        var inventoryItems = isEntityPlayer ? PlayerAPI.ReturnALlPlayerItems((Player) livingEntity) : PlayerAPI.ReturnALlPlayerItems(livingEntity);

        for(var itemStack : inventoryItems.keySet()){
            if(itemStack.getItemMeta() == null) continue;
            for(var nbtListener : PulseCoreMain.nbtListeners){
                if(!event.isCancelled() && nbtListener.EntityTeleportEvent(event, itemStack, NBTAPI.GetAll(itemStack), livingEntity)) event.setCancelled(true);
            }

            for(var pulseEnchantment : EnchantmentAPI.ReturnCustomEnchantmentOnItems(itemStack)){
                if(!event.isCancelled() && pulseEnchantment.EntityTeleportEvent(event, itemStack, inventoryItems.get(itemStack))) event.setCancelled(true);
            }

            var pulseItemStack = ItemStackAPI.ReturnPulseItem(itemStack);
            if(pulseItemStack != null) if(!event.isCancelled() && pulseItemStack.EntityTeleportEvent(event, itemStack, inventoryItems.get(itemStack))) event.setCancelled(true);
        }

        if(PulseCoreMain.handlePlayerActionEventsInHouse && isEntityPlayer){
            if(!event.isCancelled() && !PlayerAPI.CanDoAction(PlayerAction.EntityTeleport, (Player) livingEntity)) event.setCancelled(true);
        }

        var world = livingEntity.getLocation().getWorld();
        if(PulseCoreMain.playerActionLock.containsKey(world)  && isEntityPlayer)  if(!event.isCancelled()) event.setCancelled(PulseCoreMain.playerActionLock.get(world).contains(PlayerAction.EntityTeleport));

        for(var pulseLocation :  LocationAPI.ReturnAllPulseLocations(livingEntity.getLocation(), true)){
            if(!event.isCancelled() && pulseLocation.EntityTeleportEvent(event, livingEntity.getLocation())) event.setCancelled(true);
        }

        for(var coreEvent : PulseCoreMain.pulseCoreEvents) if(!event.isCancelled() && coreEvent.EntityTeleportEvent(event)) event.setCancelled(true);
    }
}
