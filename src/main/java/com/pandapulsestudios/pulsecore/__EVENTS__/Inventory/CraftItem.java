package com.pandapulsestudios.pulsecore.__Events__.Inventory;

import com.pandapulsestudios.pulsecore.Enchantment.EnchantmentAPI;
import com.pandapulsestudios.pulsecore.Events.CustomEvent;
import com.pandapulsestudios.pulsecore.Items.ItemLocation;
import com.pandapulsestudios.pulsecore.Items.ItemStackAPI;
import com.pandapulsestudios.pulsecore.Location.LocationAPI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.BrewEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.FurnaceSmeltEvent;


@CustomEvent
public class CraftItem implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEvent(CraftItemEvent event){
        for(var pulseEnchantment : EnchantmentAPI.ReturnCustomEnchantmentOnItems(event.getCurrentItem())){
            var state = pulseEnchantment.CraftItemEvent(event, event.getCurrentItem(), ItemLocation.Container);
            if(!event.isCancelled()) event.setCancelled(state);
        }

        var pulseItemStack = ItemStackAPI.ReturnPulseItem(event.getCurrentItem());
        if(pulseItemStack != null){
            var state = pulseItemStack.CraftItemEvent(event, event.getCurrentItem(), ItemLocation.Container);
            if(!event.isCancelled()) event.setCancelled(state);
        }

        var eventLocation = event.getInventory().getLocation();
        for(var pulseLocation :  LocationAPI.ReturnAllPulseLocations(eventLocation, true)){
            var state = pulseLocation.CraftItemEvent(event, eventLocation);
            if(!event.isCancelled()) event.setCancelled(state);
        }
    }
}
