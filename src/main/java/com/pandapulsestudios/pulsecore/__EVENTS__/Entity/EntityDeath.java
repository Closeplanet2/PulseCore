package com.pandapulsestudios.pulsecore.__Events__.Entity;

import com.pandapulsestudios.pulsecore.Enchantment.EnchantmentAPI;
import com.pandapulsestudios.pulsecore.Events.CustomEvent;
import com.pandapulsestudios.pulsecore.Items.ItemStackAPI;
import com.pandapulsestudios.pulsecore.Location.LocationAPI;
import com.pandapulsestudios.pulsecore.Player.PlayerAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityPortalEnterEvent;

@CustomEvent
public class EntityDeath implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEvent(EntityDeathEvent event){
        if(!(event.getEntity() instanceof Player player)) return;

        var inventoryItems = PlayerAPI.ReturnALlPlayerItems(player);
        for(var itemStack : inventoryItems.keySet()){
            for(var pulseEnchantment : EnchantmentAPI.ReturnCustomEnchantmentOnItems(itemStack)){
                pulseEnchantment.EntityDeathEvent(event, itemStack, inventoryItems.get(itemStack));
            }
        }

        for(var itemStack : inventoryItems.keySet()){
            var pulseItemStack = ItemStackAPI.ReturnPulseItem(itemStack);
            if(pulseItemStack == null) continue;
            pulseItemStack.EntityDeathEvent(event, itemStack, inventoryItems.get(itemStack));
        }

        var eventLocation = player.getLocation();
        for(var pulseLocation :  LocationAPI.ReturnAllPulseLocations(eventLocation, true)){
            pulseLocation.EntityDeathEvent(event, eventLocation);
        }
    }
}
