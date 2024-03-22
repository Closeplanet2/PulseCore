package com.pandapulsestudios.pulsecore._Common.Events.Entity;

import com.pandapulsestudios.pulsecore.Enchantment.EnchantmentAPI;
import com.pandapulsestudios.pulsecore.Events.CustomEvent;
import com.pandapulsestudios.pulsecore.Items.ItemStackAPI;
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

@CustomEvent
public class EntityPortalEnter implements Listener {
    @EventHandler(priority = EventPriority.HIGH)
    public void OnBlockBreak(EntityPortalEnterEvent event){
        if(!(event.getEntity() instanceof LivingEntity livingEntity)) return;
        var isEntityPlayer = livingEntity instanceof Player;

        for(var pulseCoreEvent : PulseCore.PulseCoreEvents){
            if(pulseCoreEvent.CanDoEvent(isEntityPlayer ? (Player) livingEntity : null, event.getLocation())){
                pulseCoreEvent.pulseCoreEvents.EntityPortalEnterEvent(event);
            }
        }

        for(var pulseLocation :  LocationAPI.ReturnAllPulseLocations(livingEntity.getLocation(), true)){
            pulseLocation.EntityPortalEnterEvent(event, livingEntity.getLocation());
        }

        for(var pulseLocation : LocationAPI.ReturnAllPulseLocations(livingEntity.getLocation(), true)){
            pulseLocation.EntityPortalEnterEvent(event, livingEntity.getLocation());
        }

        var playerInventoryItems = PlayerAPI.ReturnALlPlayerItems(livingEntity);
        for(var itemStack : playerInventoryItems.keySet()){
            if(itemStack == null || itemStack.getItemMeta() == null) continue;

            for(var nbtListener : PulseCore.NbtListeners){
                nbtListener.EntityPortalEnterEvent(event, itemStack, NBTAPI.GetAll(itemStack), livingEntity);
            }

            for(var pulseEnchantment : EnchantmentAPI.ReturnCustomEnchantmentOnItem(itemStack)){
                pulseEnchantment.EntityPortalEnterEvent(event, itemStack, playerInventoryItems.get(itemStack));
            }

            var pulseItemStack = ItemStackAPI.ReturnPulseItem(itemStack);
            if(pulseItemStack != null){
                pulseItemStack.EntityPortalEnterEvent(event, itemStack, playerInventoryItems.get(itemStack));
            }
        }
    }
}
