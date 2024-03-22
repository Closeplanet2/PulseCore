package com.pandapulsestudios.pulsecore._Common.Events.Entity;

import com.pandapulsestudios.pulsecore.Enchantment.EnchantmentAPI;
import com.pandapulsestudios.pulsecore.Events.EventAPI;
import com.pandapulsestudios.pulsecore.Inventory.InventoryAPI;
import com.pandapulsestudios.pulsecore.Items.ItemStackAPI;
import com.pandapulsestudios.pulsecore.Java.PulseAutoRegister;
import com.pandapulsestudios.pulsecore.Location.LocationAPI;
import com.pandapulsestudios.pulsecore.NBT.NBTAPI;
import com.pandapulsestudios.pulsecore.Player.PlayerAPI;
import com.pandapulsestudios.pulsecore.PulseCore;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

@PulseAutoRegister
public class EntityDeath implements Listener {
    @EventHandler(priority = EventPriority.HIGH)
    public void OnBlockBreak(EntityDeathEvent event){
        for(var pulseCoreEvent : PulseCore.customCoreEvents.values()){
            if(EventAPI.CanDoEvent(null, event.getEntity().getLocation(), pulseCoreEvent)){
                pulseCoreEvent.EntityDeathEvent(event);
            }
        }

        for(var pulseLocation :  LocationAPI.ReturnAllPulseLocations(event.getEntity().getLocation(), true)){
            pulseLocation.EntityDeathEvent(event, event.getEntity().getLocation());
        }

        var playerInventoryItems = InventoryAPI.ReturnALlItemsWithLocation(event.getEntity());
        for(var itemStack : playerInventoryItems.keySet()){
            if(itemStack.getItemMeta() == null) continue;

            for(var nbtListener : PulseCore.customNBTListener.values()){
                if(!NBTAPI.DoesItemStackContainNBTTags(itemStack, nbtListener.BlockHasTags())) continue;
                nbtListener.EntityDeathEvent(event, itemStack, NBTAPI.GetAll(itemStack), event.getEntity());
            }

            for(var pulseEnchantment : EnchantmentAPI.ReturnAllCustomEnchantmentsFromItem(itemStack)){
                pulseEnchantment.EntityDeathEvent(event, itemStack, playerInventoryItems.get(itemStack));
            }

            var pulseItemStack = ItemStackAPI.ReturnPulseItem(itemStack);
            if(pulseItemStack != null){
                pulseItemStack.EntityDeathEvent(event, itemStack, playerInventoryItems.get(itemStack));
            }
        }
    }
}
