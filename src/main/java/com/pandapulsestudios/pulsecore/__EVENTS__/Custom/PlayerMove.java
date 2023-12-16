package com.pandapulsestudios.pulsecore.__Events__.Custom;

import com.pandapulsestudios.pulsecore.Enchantment.EnchantmentAPI;
import com.pandapulsestudios.pulsecore.Items.ItemStackAPI;
import com.pandapulsestudios.pulsecore.Location.LocationAPI;
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
            for(var pulseEnchantment : EnchantmentAPI.ReturnCustomEnchantmentOnItems(itemStack)){
                var state = pulseEnchantment.PlayerMove(player, lastLocation, newLocation, itemStack, inventoryItems.get(itemStack));
                if(!isCancelled) isCancelled = state;
            }
        }

        if(PulseCoreMain.handlePlayerActionEventsInHouse){
            var state = PlayerAPI.CanDoAction(PlayerAction.PlayerMove, player);
            if(!isCancelled) isCancelled = state;
        }

        var world = player.getLocation().getWorld();
        if(PulseCoreMain.playerActionLock.containsKey(world)){
            if(!isCancelled) isCancelled = PulseCoreMain.playerActionLock.get(world).contains(PlayerAction.PlayerMove);
        }

        for(var itemStack : inventoryItems.keySet()){
            var pulseItemStack = ItemStackAPI.ReturnPulseItem(itemStack);
            if(pulseItemStack == null) continue;
            var state = pulseItemStack.PlayerMove(player, lastLocation, newLocation, itemStack, inventoryItems.get(itemStack));
            if(!isCancelled) isCancelled = state;
        }

        var eventLocation = player.getLocation();
        for(var pulseLocation :  LocationAPI.ReturnAllPulseLocations(eventLocation, true)){
            var state = pulseLocation.PlayerMove(player, lastLocation, newLocation);
            if(!isCancelled) isCancelled = state;
        }

        if(isCancelled){
            player.teleport(lastLocation);
        }
    }
}
