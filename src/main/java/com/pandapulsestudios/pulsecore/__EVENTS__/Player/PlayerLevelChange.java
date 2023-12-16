package com.pandapulsestudios.pulsecore.__Events__.Player;

import com.pandapulsestudios.pulsecore.Enchantment.EnchantmentAPI;
import com.pandapulsestudios.pulsecore.Events.CustomEvent;
import com.pandapulsestudios.pulsecore.Items.ItemStackAPI;
import com.pandapulsestudios.pulsecore.Location.LocationAPI;
import com.pandapulsestudios.pulsecore.Player.PlayerAPI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerLevelChangeEvent;

@CustomEvent
public class PlayerLevelChange implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEvent(PlayerLevelChangeEvent event){
        var inventoryItems = PlayerAPI.ReturnALlPlayerItems(event.getPlayer());
        for(var itemStack : inventoryItems.keySet()){
            for(var pulseEnchantment : EnchantmentAPI.ReturnCustomEnchantmentOnItems(itemStack)){
                pulseEnchantment.PlayerLevelChangeEvent(event, itemStack, inventoryItems.get(itemStack));
            }
        }

        for(var itemStack : inventoryItems.keySet()){
            var pulseItemStack = ItemStackAPI.ReturnPulseItem(itemStack);
            if(pulseItemStack == null) continue;
            pulseItemStack.PlayerLevelChangeEvent(event, itemStack, inventoryItems.get(itemStack));
        }

        var eventLocation = event.getPlayer().getLocation();
        for(var pulseLocation :  LocationAPI.ReturnAllPulseLocations(eventLocation, true)){
            pulseLocation.PlayerLevelChangeEvent(event, eventLocation);
        }
    }
}
