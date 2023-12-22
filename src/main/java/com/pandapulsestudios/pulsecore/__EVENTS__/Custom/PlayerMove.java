package com.pandapulsestudios.pulsecore.__Events__.Custom;

import com.pandapulsestudios.pulsecore.Block.PersistentDataAPI;
import com.pandapulsestudios.pulsecore.Enchantment.EnchantmentAPI;
import com.pandapulsestudios.pulsecore.Items.ItemStackAPI;
import com.pandapulsestudios.pulsecore.Location.LocationAPI;
import com.pandapulsestudios.pulsecore.NBT.NBTAPI;
import com.pandapulsestudios.pulsecore.Player.Enums.PlayerAction;
import com.pandapulsestudios.pulsecore.Player.PlayerAPI;
import com.pandapulsestudios.pulsecore.PulseCoreMain;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class PlayerMove {
    public static void HandleEvent(Player player, Location lastLocation, Location newLocation){
        var isCancelled = false;

        var inventoryItems = PlayerAPI.ReturnALlPlayerItems(player);

        for(var itemStack : inventoryItems.keySet()){
            if(itemStack.getItemMeta() == null) continue;
            for(var nbtListener : PulseCoreMain.nbtListeners){
                if(!isCancelled && nbtListener.PlayerMove(player, lastLocation, newLocation, itemStack, NBTAPI.GetAll(itemStack))) isCancelled = true;
            }

            for(var pulseEnchantment : EnchantmentAPI.ReturnCustomEnchantmentOnItems(itemStack)){
                if(!isCancelled && pulseEnchantment.PlayerMove(player, lastLocation, newLocation, itemStack, inventoryItems.get(itemStack))) isCancelled = true;
            }

            var pulseItemStack = ItemStackAPI.ReturnPulseItem(itemStack);
            if(pulseItemStack != null) if(!isCancelled && pulseItemStack.PlayerMove(player, lastLocation, newLocation, itemStack, inventoryItems.get(itemStack))) isCancelled = true;
        }

        if(PulseCoreMain.handlePlayerActionEventsInHouse){
            if(!isCancelled && !PlayerAPI.CanDoAction(PlayerAction.PlayerMove, player)) isCancelled = true;
        }

        var world = player.getLocation().getWorld();
        if(PulseCoreMain.playerActionLock.containsKey(world)){
            if(!isCancelled) isCancelled = PulseCoreMain.playerActionLock.get(world).contains(PlayerAction.PlayerMove);
        }

        var eventLocation = player.getLocation();
        for(var pulseLocation :  LocationAPI.ReturnAllPulseLocations(eventLocation, true)){
            var state = pulseLocation.PlayerMove(player, lastLocation, newLocation);
            if(!isCancelled) isCancelled = state;
        }

        for(var coreEvent : PulseCoreMain.pulseCoreEvents) if(!isCancelled && coreEvent.PlayerMove(player, lastLocation, newLocation)) isCancelled = true;

        if(isCancelled) player.teleport(lastLocation);
    }
}
