package com.pandapulsestudios.pulsecore.MovementAPI.API;

import com.pandapulsestudios.pulsecore.PlayerAPI.API.PlayerActionAPI;
import com.pandapulsestudios.pulsecore.PlayerAPI.Enum.PlayerAction;
import com.pandapulsestudios.pulsecore.StorageDataAPI.API.UUIDStorageAPI;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class MovementAPI {
    public static Location ReturnLocationLock(Player player){
        return (Location) UUIDStorageAPI.Get(player.getUniqueId(), "MovementLoop", player.getLocation()).getStorageData();
    }

    public static void LockPlayerLocation(Player player, Location location){
        UUIDStorageAPI.Store(player.getUniqueId(), "MovementLoop", location);
        PlayerActionAPI.LockPlayerAction(PlayerAction.PlayerMove, player.getUniqueId());
    }

    public static void UnLockPlayerLocation(Player player){
        UUIDStorageAPI.Remove(player.getUniqueId(), "MovementLoop");
        PlayerActionAPI.UnLockPlayerAction(PlayerAction.PlayerMove, player.getUniqueId());
    }

    public static void LockPlayerRotation(Player player, Location location){
        UUIDStorageAPI.Store(player.getUniqueId(), "RotationLock", location);
        PlayerActionAPI.LockPlayerAction(PlayerAction.PlayerRotate, player.getUniqueId());
    }

    public static void UnLockPlayerRotation(Player player){
        UUIDStorageAPI.Remove(player.getUniqueId(), "RotationLock");
        PlayerActionAPI.UnLockPlayerAction(PlayerAction.PlayerRotate, player.getUniqueId());
    }
}
