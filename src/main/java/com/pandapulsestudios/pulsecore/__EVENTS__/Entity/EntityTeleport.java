package com.pandapulsestudios.pulsecore.__Events__.Entity;

import com.pandapulsestudios.pulsecore.Enchantment.EnchantmentAPI;
import com.pandapulsestudios.pulsecore.Events.CustomEvent;
import com.pandapulsestudios.pulsecore.Items.ItemStackAPI;
import com.pandapulsestudios.pulsecore.Location.LocationAPI;
import com.pandapulsestudios.pulsecore.Player.PlayerAPI;
import com.pandapulsestudios.pulsecore.Player.Enums.PlayerAction;
import com.pandapulsestudios.pulsecore.PulseCoreMain;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTeleportEvent;

@CustomEvent
public class EntityTeleport implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEvent(EntityTeleportEvent event){
        if(!(event.getEntity() instanceof Player player)) return;

        var inventoryItems = PlayerAPI.ReturnALlPlayerItems(player);
        for(var itemStack : inventoryItems.keySet()){
            for(var pulseEnchantment : EnchantmentAPI.ReturnCustomEnchantmentOnItems(itemStack)){
                var state = pulseEnchantment.EntityTeleportEvent(event, itemStack, inventoryItems.get(itemStack));
                if(!event.isCancelled()) event.setCancelled(state);
            }
        }

        if(PulseCoreMain.handlePlayerActionEventsInHouse){
            var state = PlayerAPI.CanDoAction(PlayerAction.EntityTeleport, player);
            if(!event.isCancelled()) event.setCancelled(state);
        }

        var world = player.getLocation().getWorld();
        if(PulseCoreMain.playerActionLock.containsKey(world)){
            if(!event.isCancelled()) event.setCancelled(PulseCoreMain.playerActionLock.get(world).contains(PlayerAction.EntityTeleport));
        }

        for(var itemStack : inventoryItems.keySet()){
            var pulseItemStack = ItemStackAPI.ReturnPulseItem(itemStack);
            if(pulseItemStack == null) continue;
            var state = pulseItemStack.EntityTeleportEvent(event, itemStack, inventoryItems.get(itemStack));
            if(!event.isCancelled()) event.setCancelled(state);
        }

        var eventLocation = player.getLocation();
        for(var pulseLocation :  LocationAPI.ReturnAllPulseLocations(eventLocation, true)){
            var state = pulseLocation.EntityTeleportEvent(event, eventLocation);
            if(!event.isCancelled()) event.setCancelled(state);
        }
    }
}
