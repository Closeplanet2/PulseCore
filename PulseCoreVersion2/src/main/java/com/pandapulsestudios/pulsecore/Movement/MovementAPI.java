package com.pandapulsestudios.pulsecore.Movement;

import com.pandapulsestudios.pulsecore.Data.API.UUIDDataAPI;
import com.pandapulsestudios.pulsecore.Player.PlayerAPI;
import com.pandapulsestudios.pulsecore.Player.PlayerAction;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class MovementAPI {
    private static String movementKey = "<<MovementLoop>>";

    public static void LockEntityLocation(Entity entity, boolean state, Location location){
        if(location == null) location = entity.getLocation();
        if(state) UUIDDataAPI.Store(entity.getUniqueId(), movementKey, location);
        else UUIDDataAPI.Remove(entity.getUniqueId(), movementKey);
        if(entity instanceof Player player) PlayerAPI.TogglePlayerAction(PlayerAction.PlayerMove, state, player);
    }

    public static void TeleportEntityToLock(Entity entity){
        var storedLocation = ReturnEntityLockLocation(entity);
        if(storedLocation != null) entity.teleport(storedLocation);
    }

    public static Location ReturnEntityLockLocation(Entity entity){
        return (Location) UUIDDataAPI.Get(entity.getUniqueId(), movementKey, null);
    }
}
