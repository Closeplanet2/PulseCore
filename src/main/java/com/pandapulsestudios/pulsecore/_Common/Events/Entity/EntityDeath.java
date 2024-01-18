package com.pandapulsestudios.pulsecore._Common.Events.Entity;

import com.pandapulsestudios.pulsecore.Block.API.PersistentDataAPI;
import com.pandapulsestudios.pulsecore.Enchantment.EnchantmentAPI;
import com.pandapulsestudios.pulsecore.Events.CustomEvent;
import com.pandapulsestudios.pulsecore.Items.ItemStackAPI;
import com.pandapulsestudios.pulsecore.Location.LocationAPI;
import com.pandapulsestudios.pulsecore.NBT.NBTAPI;
import com.pandapulsestudios.pulsecore.Player.Enums.PlayerAction;
import com.pandapulsestudios.pulsecore.Player.PlayerAPI;
import com.pandapulsestudios.pulsecore.PulseCore;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityDeathEvent;

@CustomEvent
public class EntityDeath implements Listener {
    @EventHandler(priority = EventPriority.HIGH)
    public void OnBlockBreak(EntityDeathEvent event){
        for(var pulseCoreEvent : PulseCore.PulseCoreEvents) pulseCoreEvent.EntityDeathEvent(event);

        for(var pulseLocation :  LocationAPI.ReturnAllPulseLocations(event.getEntity().getLocation(), true)){
            pulseLocation.EntityDeathEvent(event, event.getEntity().getLocation());
        }

        for(var pulseLocation : LocationAPI.ReturnAllPulseLocations(event.getEntity().getLocation(), true)){
            pulseLocation.EntityDeathEvent(event, event.getEntity().getLocation());
        }

        var playerInventoryItems = PlayerAPI.ReturnALlPlayerItems(event.getEntity());
        for(var itemStack : playerInventoryItems.keySet()){
            if(itemStack == null || itemStack.getItemMeta() == null) continue;

            for(var nbtListener : PulseCore.nbtListeners){
                nbtListener.EntityDeathEvent(event, itemStack, NBTAPI.GetAll(itemStack), event.getEntity());
            }

            for(var pulseEnchantment : EnchantmentAPI.ReturnCustomEnchantmentOnItem(itemStack)){
                pulseEnchantment.EntityDeathEvent(event, itemStack, playerInventoryItems.get(itemStack));
            }

            var pulseItemStack = ItemStackAPI.ReturnPulseItem(itemStack);
            if(pulseItemStack != null){
                pulseItemStack.EntityDeathEvent(event, itemStack, playerInventoryItems.get(itemStack));
            }
        }
    }
}
