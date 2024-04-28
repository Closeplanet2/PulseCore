package com.pandapulsestudios.pulsecore._Common.Events.Custom;


import com.pandapulsestudios.pulsecore.EnchantmentAPI.API.EnchantmentAPI;
import com.pandapulsestudios.pulsecore.EventsAPI.API.EventAPI;
import com.pandapulsestudios.pulsecore.InventoryAPI.API.InventoryAPI;
import com.pandapulsestudios.pulsecore.ItemsAPI.API.ItemStackAPI;
import com.pandapulsestudios.pulsecore.LocationAPI.API.LocationAPI;
import com.pandapulsestudios.pulsecore.NBTAPI.API.NBTAPI;
import com.pandapulsestudios.pulsecore.PlayerAPI.API.PlayerActionAPI;
import com.pandapulsestudios.pulsecore.PlayerAPI.Enum.PlayerAction;
import com.pandapulsestudios.pulsecore.PulseCore;
import com.pandapulsestudios.pulsecore.StorageDataAPI.API.UUIDStorageAPI;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class PlayerMove {

    private static final double PLAYER_MOVE_RADIUS = 0.75;

    public static void PlayerMoveLoop(Player player){
        var storedLocation = (Location) UUIDStorageAPI.Get(player.getUniqueId(), "StoredLocation", player.getLocation()).getStorageData();
        var storedRotation = (Location) UUIDStorageAPI.Get(player.getUniqueId(), "RotationLock", storedLocation).getStorageData();
        storedLocation.setPitch(storedRotation.getPitch());
        storedRotation.setYaw(storedRotation.getYaw());
        HandleEvent(player, storedLocation, player.getLocation());
        UUIDStorageAPI.Store(player.getUniqueId(), "StoredLocation", player.getLocation());
    }

    private static void HandleEvent(Player player, Location lastLocation, Location newLocation){
        var moveEventCancelled = false;
        var rotateEventCancelled = false;

        var playerMoveState1 = PlayerActionAPI.CanPlayerAction(PlayerAction.PlayerMove, player.getUniqueId());
        var playerRotateState1 = PlayerActionAPI.CanPlayerAction(PlayerAction.PlayerRotate, player.getUniqueId());
        moveEventCancelled = !playerMoveState1;
        lastLocation = (Location) UUIDStorageAPI.Get(player.getUniqueId(), "MovementLoop", lastLocation).getStorageData();
        rotateEventCancelled = !playerRotateState1;
        lastLocation = (Location) UUIDStorageAPI.Get(player.getUniqueId(), "RotationLock", lastLocation).getStorageData();

        var moveDistance = lastLocation.getWorld().getUID().equals(newLocation.getWorld().getUID()) ? lastLocation.distance(newLocation) : Double.POSITIVE_INFINITY;
        var angleDistance = lastLocation.toVector().angle(newLocation.toVector());

        for(var pulseCoreEvent : PulseCore.PulseCoreEvents.values()){
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

            for(var nbtListener : PulseCore.PulseNBTListeners.values()){
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

        if(moveEventCancelled && moveDistance > PLAYER_MOVE_RADIUS){
            player.teleport(lastLocation);
        }

        if(rotateEventCancelled && angleDistance > PLAYER_MOVE_RADIUS){
            player.getLocation().setYaw(lastLocation.getYaw());
            player.getLocation().setPitch(lastLocation.getPitch());
        }
    }
}
