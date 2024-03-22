package com.pandapulsestudios.pulsecore.Location;

import com.pandapulsestudios.pulsecore.Player.PlayerAPI;
import com.pandapulsestudios.pulsecore.Player.PlayerAction;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public interface PulseLocation {
    Location storedLocation();
    default String locationName(){ return getClass().getSimpleName(); }
    default int distanceForEvents(){ return 5; }

    default void TeleportPlayers(Player... players){
        for(var player : players) {
            if(PlayerAPI.CanPlayerAction(PlayerAction.PlayerTeleport, player)) player.teleport(storedLocation());
        }
    }
    default void TeleportPlayers(Entity... entities){
        for(var entity : entities) entity.teleport(storedLocation());
    }
}
