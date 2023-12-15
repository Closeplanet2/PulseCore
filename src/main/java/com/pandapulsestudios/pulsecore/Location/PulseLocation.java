package com.pandapulsestudios.pulsecore.Location;

import com.pandapulsestudios.pulsecore.Chat.ChatAPI;
import com.pandapulsestudios.pulsecore.Chat.Enums.MessageType;
import com.pandapulsestudios.pulsecore.Player.PlayerAPI;
import com.pandapulsestudios.pulsecore.Player.PlayerAction;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;

public interface PulseLocation {
    String locationName();
    Location storedLocation();
    default int distanceForEvents(){ return 5; }
    default boolean isLocationForEvent(Location location, boolean useDistanceForEvent){
        return storedLocation().distance(location) <= (useDistanceForEvent ? distanceForEvents() : 1);
    }
    default void RegisteredLocation(){ ChatAPI.SendChat(String.format("&3Registered Location: %s", locationName()), MessageType.ConsoleMessageNormal, true); }
    default void TeleportPlayers(Player... players){
        for(var player : players) {
            if(PlayerAPI.CanDoAction(PlayerAction.PlayerTeleport, player)) player.teleport(storedLocation());
        }
    }
    default void TeleportPlayers(Entity... entities){
        for(var entity : entities) entity.teleport(storedLocation());
    }

    default boolean BlockBreakEvent(BlockBreakEvent event){ return false; }
}
