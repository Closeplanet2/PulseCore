package com.pandapulsestudios.pulsecore.__Events__.Player;

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
import org.bukkit.event.player.PlayerToggleSprintEvent;

@CustomEvent
public class PlayerToggleSprint implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEvent(PlayerToggleSprintEvent event){
        var inventoryItems = PlayerAPI.ReturnALlPlayerItems(event.getPlayer());
        for(var itemStack : inventoryItems.keySet()){
            for(var pulseEnchantment : EnchantmentAPI.ReturnCustomEnchantmentOnItems(itemStack)){
                var state = pulseEnchantment.PlayerToggleSprintEvent(event, itemStack, inventoryItems.get(itemStack));
                if(!event.isCancelled()) event.setCancelled(state);
            }
        }

        if(PulseCoreMain.handlePlayerActionEventsInHouse){
            var state = PlayerAPI.CanDoAction(PlayerAction.PlayerToggleSprint, event.getPlayer());
            if(!event.isCancelled()) event.setCancelled(state);
        }

        var world = event.getPlayer().getLocation();
        if(PulseCoreMain.playerActionLock.containsKey(world)){
            if(!event.isCancelled()) event.setCancelled(PulseCoreMain.playerActionLock.get(world).contains(PlayerAction.PlayerToggleSprint));
        }

        for(var itemStack : inventoryItems.keySet()){
            var pulseItemStack = ItemStackAPI.ReturnPulseItem(itemStack);
            if(pulseItemStack == null) continue;
            var state = pulseItemStack.PlayerToggleSprintEvent(event, itemStack, inventoryItems.get(itemStack));
            if(!event.isCancelled()) event.setCancelled(state);
        }

        var eventLocation = event.getPlayer().getLocation();
        for(var pulseLocation :  LocationAPI.ReturnAllPulseLocations(eventLocation, true)){
            var state = pulseLocation.PlayerToggleSprintEvent(event, eventLocation);
            if(!event.isCancelled()) event.setCancelled(state);
        }
    }
}
