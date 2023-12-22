package com.pandapulsestudios.pulsecore.__Events__.Player;

import com.pandapulsestudios.pulsecore.Block.PersistentDataAPI;
import com.pandapulsestudios.pulsecore.Enchantment.EnchantmentAPI;
import com.pandapulsestudios.pulsecore.Events.CustomEvent;
import com.pandapulsestudios.pulsecore.Items.ItemStackAPI;
import com.pandapulsestudios.pulsecore.Location.LocationAPI;
import com.pandapulsestudios.pulsecore.NBT.NBTAPI;
import com.pandapulsestudios.pulsecore.Player.Enums.PlayerAction;
import com.pandapulsestudios.pulsecore.Player.PlayerAPI;
import com.pandapulsestudios.pulsecore.PulseCoreMain;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerExpChangeEvent;

@CustomEvent
public class PlayerExpChange implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEvent(PlayerExpChangeEvent event){
        var inventoryItems = PlayerAPI.ReturnALlPlayerItems(event.getPlayer());

        for(var itemStack : inventoryItems.keySet()){
            if(itemStack.getItemMeta() == null) continue;
            for(var nbtListener : PulseCoreMain.nbtListeners) nbtListener.PlayerExpChangeEvent(event, itemStack, NBTAPI.GetAll(itemStack), event.getPlayer());
            for(var pulseEnchantment : EnchantmentAPI.ReturnCustomEnchantmentOnItems(itemStack)) pulseEnchantment.PlayerExpChangeEvent(event, itemStack, inventoryItems.get(itemStack));
            var pulseItemStack = ItemStackAPI.ReturnPulseItem(itemStack);
            if(pulseItemStack != null) pulseItemStack.PlayerExpChangeEvent(event, itemStack, inventoryItems.get(itemStack));
        }

        for(var pulseLocation :  LocationAPI.ReturnAllPulseLocations(event.getPlayer().getLocation(), true)) pulseLocation.PlayerExpChangeEvent(event, event.getPlayer().getLocation());

        for(var coreEvent : PulseCoreMain.pulseCoreEvents) coreEvent.PlayerExpChangeEvent(event);
    }
}
