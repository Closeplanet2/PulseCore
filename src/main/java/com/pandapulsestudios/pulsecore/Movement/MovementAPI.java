package com.pandapulsestudios.pulsecore.Movement;

import com.pandapulsestudios.pulsecore.Data.API.UUIDDataAPI;
import com.pandapulsestudios.pulsecore.Player.PlayerAPI;
import com.pandapulsestudios.pulsecore.Player.PlayerAction;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class MovementAPI {
    public static Location ReturnLocationLock(Player player){
        return (Location) UUIDDataAPI.Get(player.getUniqueId(), "MovementLoop", player.getLocation());
    }

    public static void LockPlayerLocation(Player player, boolean state, Location location){
        if(!state) UUIDDataAPI.Store(player.getUniqueId(), "MovementLoop", location);
        else UUIDDataAPI.Remove(player.getUniqueId(), "MovementLoop");
        PlayerAPI.TogglePlayerAction(PlayerAction.PlayerMove, state, player);
    }

    public static void LockPlayerRotation(Player player, boolean state, Location location){
        if(!state) UUIDDataAPI.Store(player.getUniqueId(), "MovementLoop", location);
        else UUIDDataAPI.Remove(player.getUniqueId(), "MovementLoop");
        PlayerAPI.TogglePlayerAction(PlayerAction.PlayerMove, state, player);
    }
}
