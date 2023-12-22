package com.pandapulsestudios.pulsecore.__Events__.Inventory;

import com.pandapulsestudios.pulsecore.Block.PersistentDataAPI;
import com.pandapulsestudios.pulsecore.Enchantment.EnchantmentAPI;
import com.pandapulsestudios.pulsecore.Events.CustomEvent;
import com.pandapulsestudios.pulsecore.Items.ItemLocation;
import com.pandapulsestudios.pulsecore.Items.ItemStackAPI;
import com.pandapulsestudios.pulsecore.Location.LocationAPI;
import com.pandapulsestudios.pulsecore.NBT.NBTAPI;
import com.pandapulsestudios.pulsecore.PulseCoreMain;
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
        if(event.getCurrentItem() == null || !event.getCurrentItem().hasItemMeta()) return;

        for(var nbtListener : PulseCoreMain.nbtListeners){
            if(!event.isCancelled() && nbtListener.CraftItemEvent(event, event.getCurrentItem(), NBTAPI.GetAll(event.getCurrentItem()))) event.setCancelled(true);
        }

        for(var pulseEnchantment : EnchantmentAPI.ReturnCustomEnchantmentOnItems(event.getCurrentItem())){
            if(!event.isCancelled() && pulseEnchantment.CraftItemEvent(event, event.getCurrentItem())) event.setCancelled(true);
        }

        var pulseItemStack = ItemStackAPI.ReturnPulseItem(event.getCurrentItem());
        if(pulseItemStack != null) if(!event.isCancelled() && pulseItemStack.CraftItemEvent(event, event.getCurrentItem())) event.setCancelled(true);

        for(var pulseLocation :  LocationAPI.ReturnAllPulseLocations(event.getClickedInventory().getLocation(), true)){
            if(!event.isCancelled() && pulseLocation.CraftItemEvent(event, event.getClickedInventory().getLocation())) event.setCancelled(true);
        }
    }
}
