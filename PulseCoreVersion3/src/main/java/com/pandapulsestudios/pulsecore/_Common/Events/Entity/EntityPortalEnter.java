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
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPortalEnterEvent;

@PulseAutoRegister
public class EntityPortalEnter implements Listener {
    @EventHandler(priority = EventPriority.HIGH)
    public void OnBlockBreak(EntityPortalEnterEvent event){
        if(!(event.getEntity() instanceof LivingEntity livingEntity)) return;
        var isEntityPlayer = livingEntity instanceof Player;

        for(var pulseCoreEvent : PulseCore.customCoreEvents.values()){
            if(EventAPI.CanDoEvent(isEntityPlayer ? (Player) event.getEntity() : null, event.getEntity().getLocation(), pulseCoreEvent)){
                pulseCoreEvent.EntityPortalEnterEvent(event);
            }
        }

        for(var pulseLocation :  LocationAPI.ReturnAllPulseLocations(livingEntity.getLocation(), true)){
            pulseLocation.EntityPortalEnterEvent(event, livingEntity.getLocation());
        }

        var playerInventoryItems = InventoryAPI.ReturnALlItemsWithLocation(livingEntity);
        for(var itemStack : playerInventoryItems.keySet()){
            if(itemStack == null || itemStack.getItemMeta() == null) continue;

            for(var nbtListener : PulseCore.customNBTListener.values()){
                nbtListener.EntityPortalEnterEvent(event, itemStack, NBTAPI.GetAll(itemStack), livingEntity);
            }

            for(var pulseEnchantment : EnchantmentAPI.ReturnAllCustomEnchantmentsFromItem(itemStack)){
                pulseEnchantment.EntityPortalEnterEvent(event, itemStack, playerInventoryItems.get(itemStack));
            }

            var pulseItemStack = ItemStackAPI.ReturnPulseItem(itemStack);
            if(pulseItemStack != null){
                pulseItemStack.EntityPortalEnterEvent(event, itemStack, playerInventoryItems.get(itemStack));
            }
        }
    }
}
