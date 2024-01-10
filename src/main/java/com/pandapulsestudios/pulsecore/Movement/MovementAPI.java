package com.pandapulsestudios.pulsecore.Movement;

import com.pandapulsestudios.pulsecore.Data.API.PlayerDataAPI;
import com.pandapulsestudios.pulsecore.Player.Enums.PlayerAction;
import com.pandapulsestudios.pulsecore.Player.PlayerAPI;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class MovementAPI {
    public static void LockPlayerLocation(Player player){ LockPlayerLocation(player, player.getLocation()); }
    public static void LockPlayerLocation(Player player, Location location){
        PlayerDataAPI.STORE(player.getUniqueId(), "MovementLoop", location);
        PlayerAPI.TogglePlayerAction(PlayerAction.PlayerMove, false, player);
    }

    public static void UnLockPlayerLocation(Player player){
        PlayerDataAPI.REMOVE(player.getUniqueId(), "MovementLoop");
        PlayerAPI.TogglePlayerAction(PlayerAction.PlayerMove, true, player);
    }

    public static void MovePlayerToLock(Player player){
        if(PlayerAPI.CanDoAction(PlayerAction.PlayerMove, player)) return;
        var locked_location = ReturnLockedLocation(player);
        if(locked_location == null) return;
        player.teleport(locked_location);
    }

    private static Location ReturnLockedLocation(Player player){
        var location = PlayerDataAPI.GET(player.getUniqueId(), "MovementLoop", null);
        if(location == null) return null;
        return (Location) location;
    }
}
