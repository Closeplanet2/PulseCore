package com.pandapulsestudios.pulsecore._Common.Events.Custom;

import com.pandapulsestudios.pulsecore.Block.API.PersistentDataAPI;
import com.pandapulsestudios.pulsecore.Enchantment.EnchantmentAPI;
import com.pandapulsestudios.pulsecore.Items.ItemStackAPI;
import com.pandapulsestudios.pulsecore.Location.LocationAPI;
import com.pandapulsestudios.pulsecore.NBT.NBTAPI;
import com.pandapulsestudios.pulsecore.Player.Enums.PlayerAction;
import com.pandapulsestudios.pulsecore.Player.PlayerAPI;
import com.pandapulsestudios.pulsecore.PulseCore;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class PlayerMove {
    public static void HandleEvent(Player player, Location lastLocation, Location newLocation){
        var isCancelled = false;

        for(var pulseCoreEvent : PulseCore.PulseCoreEvents){
            if(pulseCoreEvent.CanDoEvent(player, newLocation)){
                var state = pulseCoreEvent.pulseCoreEvents.PlayerMove(player, lastLocation, newLocation);
                if(!isCancelled) isCancelled = !state;
            }
        }

        if(PulseCore.handlePlayerActionEventsInHouse){
            var state = PlayerAPI.CanDoAction(PlayerAction.PlayerMove, player);
            if(!isCancelled) isCancelled = !state;
        }

        var playerWorld = player.getWorld();
        if(PulseCore.playerActionLock.containsKey(playerWorld)){
            var state = PulseCore.playerActionLock.get(playerWorld).contains(PlayerAction.PlayerMove);
            if(!isCancelled) isCancelled = state;
        }

        for(var pulseLocation : LocationAPI.ReturnAllPulseLocations(newLocation, true)){
            var state = pulseLocation.PlayerMove(player, lastLocation, newLocation);
            if(!isCancelled) isCancelled = state;
        }

        var playerInventoryItems = PlayerAPI.ReturnALlPlayerItems(player);
        for(var itemStack : playerInventoryItems.keySet()){
            if(itemStack.getItemMeta() == null) continue;

            for(var nbtListener : PulseCore.nbtListeners){
                var state = nbtListener.PlayerMove(player, lastLocation, newLocation);
                if(!isCancelled) isCancelled = state;
            }

            for(var pulseEnchantment : EnchantmentAPI.ReturnCustomEnchantmentOnItem(itemStack)){
                var state = pulseEnchantment.PlayerMove(player, lastLocation, newLocation);
                if(!isCancelled) isCancelled = state;
            }

            var pulseItemStack = ItemStackAPI.ReturnPulseItem(itemStack);
            if(pulseItemStack != null){
                var state = pulseItemStack.PlayerMove(player, lastLocation, newLocation);
                if(!isCancelled) isCancelled = state;
            }
        }

        if(isCancelled) player.teleport(lastLocation);
    }
}
