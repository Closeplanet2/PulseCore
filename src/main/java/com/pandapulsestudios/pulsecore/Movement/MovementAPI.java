package com.pandapulsestudios.pulsecore.Movement;

import com.pandapulsestudios.pulsecore.Player.PlayerAPI;
import com.pandapulsestudios.pulsecore.Player.ToggleActions;
import com.pandapulsestudios.pulsecore.StoredData.PlayerDataAPI;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class MovementAPI {
    public static void LOCK_PLAYER_LOCATION(Player player){ LOCK_PLAYER_LOCATION(player, player.getLocation()); }
    public static void LOCK_PLAYER_LOCATION(Player player, Location location){
        PlayerDataAPI.STORE(player.getUniqueId(), "MovementLoop", location);
        PlayerAPI.TOGGLE_STAT(player, ToggleActions.PlayerMoveEvent, false);
    }

    public static void UN_FREEZE_PLAYER_LOCATION(Player player){
        PlayerDataAPI.REMOVE(player.getUniqueId(), "MovementLoop");
        PlayerAPI.TOGGLE_STAT(player, ToggleActions.PlayerMoveEvent, true);
    }

    public static void MOVE_PLAYER_TO_LOCK(Player player){
        if(PlayerAPI.GET_TOGGLE_STAT(player, ToggleActions.PlayerMoveEvent)) return;
        var locked_location = RETURN_LOCKED_LOCATION(player);
        if(locked_location == null) return;
        player.teleport(locked_location);
    }

    private static Location RETURN_LOCKED_LOCATION(Player player){
        var location = PlayerDataAPI.GET(player.getUniqueId(), "MovementLoop");
        if(location == null) return null;
        return (Location) location;
    }
}
