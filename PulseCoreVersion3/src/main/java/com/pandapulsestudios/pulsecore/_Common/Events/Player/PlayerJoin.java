package com.pandapulsestudios.pulsecore._Common.Events.Player;

import com.pandapulsestudios.pulsecore.Enchantment.EnchantmentAPI;
import com.pandapulsestudios.pulsecore.Events.EventAPI;
import com.pandapulsestudios.pulsecore.Inventory.InventoryAPI;
import com.pandapulsestudios.pulsecore.Items.ItemStackAPI;
import com.pandapulsestudios.pulsecore.Java.PulseAutoRegister;
import com.pandapulsestudios.pulsecore.Location.LocationAPI;
import com.pandapulsestudios.pulsecore.NBT.NBTAPI;
import com.pandapulsestudios.pulsecore.Player.BlockMask;
import com.pandapulsestudios.pulsecore.Player.EntityMask;
import com.pandapulsestudios.pulsecore.Player.PlayerAPI;
import com.pandapulsestudios.pulsecore.PulseCore;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

@PulseAutoRegister
public class PlayerJoin implements Listener {
    @EventHandler(priority = EventPriority.HIGH)
    public void OnPlayerJoin(PlayerJoinEvent event){
        for(var pulseCoreEvent : PulseCore.customCoreEvents.values()){
            if(EventAPI.CanDoEvent(event.getPlayer(), event.getPlayer().getLocation(), pulseCoreEvent)){
                pulseCoreEvent.PlayerJoinEvent(event);
            }
        }

        for(var pulseLocation :  LocationAPI.ReturnAllPulseLocations(event.getPlayer().getLocation(), true)){
            pulseLocation.PlayerJoinEvent(event, event.getPlayer().getLocation());
        }

        for(var pulseLocation : LocationAPI.ReturnAllPulseLocations(event.getPlayer().getLocation(), true)){
            pulseLocation.PlayerJoinEvent(event, event.getPlayer().getLocation());
        }

        var playerInventoryItems = InventoryAPI.ReturnALlItemsWithLocation(event.getPlayer());
        for(var itemStack : playerInventoryItems.keySet()){
            if(itemStack == null || itemStack.getItemMeta() == null) continue;

            for(var nbtListener : PulseCore.customNBTListener.values()){
                if(!NBTAPI.DoesItemStackContainNBTTags(itemStack, nbtListener.BlockHasTags())) continue;
                nbtListener.PlayerJoinEvent(event, itemStack, NBTAPI.GetAll(itemStack), event.getPlayer());
            }

            for(var pulseEnchantment : EnchantmentAPI.ReturnAllCustomEnchantmentsFromItem(itemStack)){
                pulseEnchantment.PlayerJoinEvent(event, itemStack, playerInventoryItems.get(itemStack));
            }

            var pulseItemStack = ItemStackAPI.ReturnPulseItem(itemStack);
            if(pulseItemStack != null){
                pulseItemStack.PlayerJoinEvent(event, itemStack, playerInventoryItems.get(itemStack));
            }
        }
    }
}
