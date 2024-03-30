package com.pandapulsestudios.pulsecore._Common.Events.Custom;

import com.pandapulsestudios.pulsecore.Data.API.UUIDDataAPI;
import com.pandapulsestudios.pulsecore.Enchantment.EnchantmentAPI;
import com.pandapulsestudios.pulsecore.Events.EventAPI;
import com.pandapulsestudios.pulsecore.Inventory.InventoryAPI;
import com.pandapulsestudios.pulsecore.Items.ItemStackAPI;
import com.pandapulsestudios.pulsecore.Location.LocationAPI;
import com.pandapulsestudios.pulsecore.NBT.NBTAPI;
import com.pandapulsestudios.pulsecore.Player.PlayerAPI;
import com.pandapulsestudios.pulsecore.Player.PlayerAction;
import com.pandapulsestudios.pulsecore.PulseCore;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class PlayerMove {

    public static void PlayerMoveLoop(Player player){
        var storedLocation = (Location) UUIDDataAPI.Get(player.getUniqueId(), "StoredLocation", player.getLocation());
        HandleEvent(player, storedLocation, player.getLocation());
        UUIDDataAPI.Store(player.getUniqueId(), "StoredLocation", player.getLocation());
    }

    private static void HandleEvent(Player player, Location lastLocation, Location newLocation){
        var moveEventCancelled = false;
        var rotateEventCancelled = false;

        if(PulseCore.handlePlayerAction){
            var playerMoveState = PlayerAPI.CanPlayerAction(PlayerAction.PlayerMove, player);
            var playerRotateState = PlayerAPI.CanPlayerAction(PlayerAction.PlayerRotate, player);
            if(!moveEventCancelled){
                moveEventCancelled = !playerMoveState;
                lastLocation = (Location) UUIDDataAPI.Get(player.getUniqueId(), "MovementLoop", lastLocation);
            }
            if(!rotateEventCancelled){
                rotateEventCancelled = !playerRotateState;
                lastLocation = (Location) UUIDDataAPI.Get(player.getUniqueId(), "MovementLoop", lastLocation);
            }
        }

        var moveDistance = lastLocation.distance(newLocation);
        var angleDistance = lastLocation.toVector().angle(newLocation.toVector());

        for(var pulseCoreEvent : PulseCore.customCoreEvents.values()){
            if(EventAPI.CanDoEvent(player, newLocation, pulseCoreEvent)){
                var playerMoveState = pulseCoreEvent.PlayerMove(player, lastLocation, newLocation, moveDistance);
                if(!moveEventCancelled) moveEventCancelled = playerMoveState;
                var playerRotateState = pulseCoreEvent.PlayerRotate(player, lastLocation, newLocation, angleDistance);
                if(!rotateEventCancelled) rotateEventCancelled = playerRotateState;
            }
        }

        var playerWorld = player.getWorld();
        if(PulseCore.PlayerActionLock.containsKey(playerWorld)){
            var playerMoveState = PulseCore.PlayerActionLock.get(playerWorld).contains(PlayerAction.PlayerMove);
            var playerRotateState = PulseCore.PlayerActionLock.get(playerWorld).contains(PlayerAction.PlayerRotate);
            if(!moveEventCancelled) moveEventCancelled = !playerMoveState;
            if(!rotateEventCancelled) rotateEventCancelled = !playerRotateState;
        }

        for(var pulseLocation : LocationAPI.ReturnAllPulseLocations(newLocation, true)){
            var playerMoveState = pulseLocation.PlayerMove(player, lastLocation, newLocation);
            if(!moveEventCancelled) moveEventCancelled = playerMoveState;
            var playerRotateState = pulseLocation.PlayerRotate(player, lastLocation, newLocation);
            if(!rotateEventCancelled) rotateEventCancelled = playerRotateState;
        }

        var playerInventoryItems = InventoryAPI.ReturnALlItemsWithLocation(player.getInventory());
        for(var itemStack : playerInventoryItems.keySet()){
            if(itemStack == null || itemStack.getItemMeta() == null) continue;

            for(var nbtListener : PulseCore.customNBTListener.values()){
                if(!NBTAPI.DoesItemStackContainNBTTags(itemStack, nbtListener.BlockHasTags())) continue;
                var playerMoveState = nbtListener.PlayerMove(player, lastLocation, newLocation);
                if(!moveEventCancelled) moveEventCancelled = playerMoveState;
                var playerRotateState = nbtListener.PlayerRotate(player, lastLocation, newLocation);
                if(!rotateEventCancelled) rotateEventCancelled = playerRotateState;
            }

            for(var pulseEnchantment : EnchantmentAPI.ReturnAllCustomEnchantmentsFromItem(itemStack)){
                var playerMoveState = pulseEnchantment.PlayerMove(player, lastLocation, newLocation);
                if(!moveEventCancelled) moveEventCancelled = playerMoveState;
                var playerRotateState = pulseEnchantment.PlayerRotate(player, lastLocation, newLocation);
                if(!rotateEventCancelled) rotateEventCancelled = playerRotateState;
            }

            var pulseItemStack = ItemStackAPI.ReturnPulseItem(itemStack);
            if(pulseItemStack != null){
                var playerMoveState = pulseItemStack.PlayerMove(player, lastLocation, newLocation);
                if(!moveEventCancelled) moveEventCancelled = playerMoveState;
                var playerRotateState = pulseItemStack.PlayerRotate(player, lastLocation, newLocation);
                if(!rotateEventCancelled) rotateEventCancelled = playerRotateState;
            }
        }

        if(moveEventCancelled || rotateEventCancelled){
            var locationToTeleport = newLocation.clone();

            if(moveEventCancelled && lastLocation.distance(newLocation) > PulseCore.playerMoveRadius){
                locationToTeleport.setX(lastLocation.getX());
                locationToTeleport.setY(lastLocation.getY());
                locationToTeleport.setZ(lastLocation.getZ());
            }

            if(rotateEventCancelled && lastLocation.toVector().angle(newLocation.toVector()) > PulseCore.playerMoveRadius){
                locationToTeleport.setYaw(lastLocation.getYaw());
                locationToTeleport.setPitch(lastLocation.getPitch());
            }

            player.teleport(locationToTeleport);
        }
    }
}
