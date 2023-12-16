package com.pandapulsestudios.pulsecore.__Events__.Inventory;

import com.pandapulsestudios.pulsecore.Enchantment.EnchantmentAPI;
import com.pandapulsestudios.pulsecore.Events.CustomEvent;
import com.pandapulsestudios.pulsecore.Items.ItemLocation;
import com.pandapulsestudios.pulsecore.Items.ItemStackAPI;
import com.pandapulsestudios.pulsecore.Location.LocationAPI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.BrewEvent;

@CustomEvent
public class Brew implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEvent(BrewEvent event){
        for(var itemStack : event.getResults()){
            for(var pulseEnchantment : EnchantmentAPI.ReturnCustomEnchantmentOnItems(itemStack)){
                var state = pulseEnchantment.BrewEvent(event, itemStack, ItemLocation.Container);
                if(!event.isCancelled()) event.setCancelled(state);
            }

            var pulseItemStack = ItemStackAPI.ReturnPulseItem(itemStack);
            if(pulseItemStack != null){
                var state = pulseItemStack.BrewEvent(event, itemStack, ItemLocation.Container);
                if(!event.isCancelled()) event.setCancelled(state);
            }

            var eventLocation = event.getBlock().getLocation();
            for(var pulseLocation :  LocationAPI.ReturnAllPulseLocations(eventLocation, true)){
                var state = pulseLocation.BrewEvent(event, eventLocation);
                if(!event.isCancelled()) event.setCancelled(state);
            }
        }
    }
}
