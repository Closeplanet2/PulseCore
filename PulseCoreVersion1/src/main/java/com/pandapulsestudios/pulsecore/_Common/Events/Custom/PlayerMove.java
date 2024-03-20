package com.pandapulsestudios.pulsecore._Common.Events.Custom;

import com.pandapulsestudios.pulsecore.Enchantment.EnchantmentAPI;
import com.pandapulsestudios.pulsecore.Items.ItemStackAPI;
import com.pandapulsestudios.pulsecore.Location.LocationAPI;
import com.pandapulsestudios.pulsecore.Player.Enums.PlayerAction;
import com.pandapulsestudios.pulsecore.Player.PlayerAPI;
import com.pandapulsestudios.pulsecore.PulseCore;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class PlayerMove {
    public static void HandleEvent(Player player, Location lastLocation, Location newLocation){
        var hasPlayerMoved = lastLocation.getX() != newLocation.getX() || lastLocation.getY() != newLocation.getY() || lastLocation.getZ() != newLocation.getZ();
        var hasPlayerRotated = lastLocation.getYaw() != newLocation.getYaw() || lastLocation.getPitch() != lastLocation.getPitch();
        if(!hasPlayerMoved && !hasPlayerRotated) return;

        var moveEventCancelled = false;
        var rotateEventCancelled = false;

        for(var pulseCoreEvent : PulseCore.PulseCoreEvents){
            if(pulseCoreEvent.CanDoEvent(player, newLocation)){
                if(hasPlayerMoved){
                    var playerMoveState = pulseCoreEvent.pulseCoreEvents.PlayerMove(player, lastLocation, newLocation);
                    if(!moveEventCancelled) moveEventCancelled = playerMoveState;
                }

                if(hasPlayerRotated){
                    var playerRotateState = pulseCoreEvent.pulseCoreEvents.PlayerRotate(player, lastLocation, newLocation);
                    if(!rotateEventCancelled) rotateEventCancelled = playerRotateState;
                }
            }
        }

        if(PulseCore.HandlePlayerActionEventsInHouse){
            var playerMoveState = PlayerAPI.CanDoAction(PlayerAction.PlayerMove, player);
            var playerRotateState = PlayerAPI.CanDoAction(PlayerAction.PlayerRotate, player);
            if(!moveEventCancelled) moveEventCancelled = !playerMoveState;
            if(!rotateEventCancelled) rotateEventCancelled = !playerRotateState;
        }

        var playerWorld = player.getWorld();
        if(PulseCore.PlayerActionLock.containsKey(playerWorld)){
            var playerMoveState = PulseCore.PlayerActionLock.get(playerWorld).contains(PlayerAction.PlayerMove);
            var playerRotateState = PulseCore.PlayerActionLock.get(playerWorld).contains(PlayerAction.PlayerRotate);
            if(!moveEventCancelled) moveEventCancelled = playerMoveState;
            if(!rotateEventCancelled) rotateEventCancelled = playerRotateState;
        }

        for(var pulseLocation : LocationAPI.ReturnAllPulseLocations(newLocation, true)){
            if(hasPlayerMoved){
                var playerMoveState = pulseLocation.PlayerMove(player, lastLocation, newLocation);
                if(!moveEventCancelled) moveEventCancelled = playerMoveState;
            }

            if(hasPlayerRotated){
                var playerRotateState = pulseLocation.PlayerRotate(player, lastLocation, newLocation);
                if(!rotateEventCancelled) rotateEventCancelled = playerRotateState;
            }
        }

        var playerInventoryItems = PlayerAPI.ReturnALlPlayerItems(player);
        for(var itemStack : playerInventoryItems.keySet()){
            if(itemStack.getItemMeta() == null) continue;

            for(var nbtListener : PulseCore.NbtListeners){
                if(hasPlayerMoved){
                    var playerMoveState = nbtListener.PlayerMove(player, lastLocation, newLocation);
                    if(!moveEventCancelled) moveEventCancelled = playerMoveState;
                }

                if(hasPlayerRotated){
                    var playerRotateState = nbtListener.PlayerRotate(player, lastLocation, newLocation);
                    if(!rotateEventCancelled) rotateEventCancelled = playerRotateState;
                }
            }

            for(var pulseEnchantment : EnchantmentAPI.ReturnCustomEnchantmentOnItem(itemStack)){
                if(hasPlayerMoved){
                    var playerMoveState = pulseEnchantment.PlayerMove(player, lastLocation, newLocation);
                    if(!moveEventCancelled) moveEventCancelled = playerMoveState;
                }

                if(hasPlayerRotated){
                    var playerRotateState = pulseEnchantment.PlayerRotate(player, lastLocation, newLocation);
                    if(!rotateEventCancelled) rotateEventCancelled = playerRotateState;
                }
            }

            var pulseItemStack = ItemStackAPI.ReturnPulseItem(itemStack);
            if(pulseItemStack != null){
                if(hasPlayerMoved){
                    var playerMoveState = pulseItemStack.PlayerMove(player, lastLocation, newLocation);
                    if(!moveEventCancelled) moveEventCancelled = playerMoveState;
                }

                if(hasPlayerRotated){
                    var playerRotateState = pulseItemStack.PlayerRotate(player, lastLocation, newLocation);
                    if(!rotateEventCancelled) rotateEventCancelled = playerRotateState;
                }
            }
        }

        if(moveEventCancelled || rotateEventCancelled){
            var locationToTeleport = newLocation.clone();
            if(moveEventCancelled){
                locationToTeleport.setX(lastLocation.getX());
                locationToTeleport.setY(lastLocation.getY());
                locationToTeleport.setZ(lastLocation.getZ());
            }

            if(rotateEventCancelled){
                locationToTeleport.setYaw(lastLocation.getYaw());
                locationToTeleport.setPitch(lastLocation.getPitch());
            }
            player.teleport(locationToTeleport);
        }
    }
}
