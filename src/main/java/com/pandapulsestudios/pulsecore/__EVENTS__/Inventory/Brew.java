package com.pandapulsestudios.pulsecore.__Events__.Inventory;

import com.pandapulsestudios.pulsecore.Block.PersistentDataAPI;
import com.pandapulsestudios.pulsecore.Enchantment.EnchantmentAPI;
import com.pandapulsestudios.pulsecore.Events.CustomEvent;
import com.pandapulsestudios.pulsecore.Items.ItemLocation;
import com.pandapulsestudios.pulsecore.Items.ItemStackAPI;
import com.pandapulsestudios.pulsecore.Location.LocationAPI;
import com.pandapulsestudios.pulsecore.NBT.NBTAPI;
import com.pandapulsestudios.pulsecore.Player.Enums.PlayerAction;
import com.pandapulsestudios.pulsecore.Player.PlayerAPI;
import com.pandapulsestudios.pulsecore.PulseCoreMain;
import org.bukkit.entity.Player;
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
            if(itemStack.getItemMeta() == null) continue;
            for(var nbtListener : PulseCoreMain.nbtListeners){
                if(!event.isCancelled() && nbtListener.BrewEvent(event, itemStack, NBTAPI.GetAll(itemStack))) event.setCancelled(true);
            }

            for(var pulseEnchantment : EnchantmentAPI.ReturnCustomEnchantmentOnItems(itemStack)){
                if(!event.isCancelled() && pulseEnchantment.BrewEvent(event, itemStack, ItemLocation.Container)) event.setCancelled(true);
            }

            var pulseItemStack = ItemStackAPI.ReturnPulseItem(itemStack);
            if(pulseItemStack != null) if(!event.isCancelled() && pulseItemStack.BrewEvent(event, itemStack)) event.setCancelled(true);
        }

        for(var pulseLocation :  LocationAPI.ReturnAllPulseLocations(event.getBlock().getLocation(), true)){
            if(!event.isCancelled() && pulseLocation.BrewEvent(event, event.getBlock().getLocation())) event.setCancelled(true);
        }

        for(var pdListener : PulseCoreMain.persistentDataListeners){
            if(!event.isCancelled() && pdListener.BrewEvent(event, event.getBlock(), PersistentDataAPI.GetAll(event.getBlock()))) event.setCancelled(true);
        }
    }
}
